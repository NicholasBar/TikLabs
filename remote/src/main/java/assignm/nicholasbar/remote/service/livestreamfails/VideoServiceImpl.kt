package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import assignm.nicholasbar.remote.model.livestreamfails.VideoModel
import assignm.nicholasbar.remote.util.InvalidEndpointException
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

open class VideoServiceImpl(private val okHttpClient: OkHttpClient) : VideoService {
    companion object {
        internal const val ENDPOINT = "https://livestreamfails.com/load/loadPosts.php"
    }

    override fun getVideos(page: Int, timeFrame: TimeFrame, order: Order,
                           nsfw: Boolean, game: String, streamer: String)
            : Observable<List<VideoModel>> {
        return Observable.fromCallable {
            val models = mutableListOf<VideoModel>()

            val url = getRequestUrl(page, timeFrame, order, nsfw, game, streamer)
                ?: throw InvalidEndpointException()

            val request = Request.Builder().url(url).get().build()
            val html = okHttpClient.newCall(request).execute().body()?.string()
            val doc = Jsoup.parse(html)

            doc.select("div.post-card")?.let {
                it.forEach { card ->
                    val title = card.selectFirst("p.title").text()

                    val postId = card.selectFirst("a[href]").attr("href")
                        .split('/', ignoreCase = true).last().toLong()

                    val thumbnailUrl = card.selectFirst("img.card-img-top").attr("src")

                    val streamerName = card.selectFirst("div.stream-info > small.text-muted")
                        ?.select("a[href]")?.get(0)?.text()
                    val gameName = card.selectFirst("div.stream-info > small.text-muted")
                        ?.select("a[href]")?.get(1)?.text()

                    val isNsfw = card.selectFirst("span.oi-warning") != null

                    val pointsElement = card.selectFirst("small.text-muted > span.oi-arrow-circle-top").parent()

                    val points = pointsElement
                        .ownText().replace(Regex("[^\\d.]"), "").toInt()

                    models.add(VideoModel(title, streamerName, gameName, points, isNsfw, thumbnailUrl, postId))
                }
            }

            models.toList()
        }
    }

    internal fun getRequestUrl(page: Int, timeFrame: TimeFrame, order: Order,
                               nsfw: Boolean, game: String, streamer: String): HttpUrl? {
        val postMode = getPostMode(streamer, game)
        val baseUrl = HttpUrl.parse(ENDPOINT)

        var urlBuilder = baseUrl?.newBuilder()
            ?.addQueryParameter("loadPostMode", postMode)
            ?.addQueryParameter("loadPostNSFW", (if (nsfw) 1 else 0).toString())
            ?.addQueryParameter("loadPostOrder", order.value)
            ?.addQueryParameter("loadPostPage", page.toString())
            ?.addQueryParameter("loadPostTimeFrame", timeFrame.value)

        if (postMode == "streamer") {
            urlBuilder = urlBuilder?.addQueryParameter("loadPostModeStreamer", streamer)
        }
        if (postMode == "game") {
            urlBuilder = urlBuilder?.addQueryParameter("loadPostModeGame", game)
        }

        return urlBuilder?.build()
    }

    internal fun getPostMode(streamer: String, game: String): String {
        if (!streamer.isEmpty()) return "streamer"
        if (!game.isEmpty()) return "game"
        return "standard"
    }
}