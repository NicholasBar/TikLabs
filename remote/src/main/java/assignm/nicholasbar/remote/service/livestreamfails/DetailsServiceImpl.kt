package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.remote.model.livestreamfails.DetailsModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

class DetailsServiceImpl(private val httpClient: OkHttpClient) : DetailsService {
    companion object {
        private const val ENDPOINT = "https://livestreamfails.com/post/%d"
    }

    override fun getDetails(postId: Long?): Single<DetailsModel> {
        return Single.fromCallable {
            val request = Request.Builder().url(String.format(ENDPOINT, postId)).get().build()
            val response = httpClient.newCall(request).execute()
            val responseUrl = response.networkResponse()?.request()?.url().toString()

            if (postId == null || responseUrl.contains("post_not_found")) {
                throw InvalidPostIdException(postId)
            }

            val doc = Jsoup.parse(response.body()?.string())
            doc?.let {
                val title = it.selectFirst("h4.post-title").text()

                val streamer = it.selectFirst("div.post-streamer-info")
                    ?.select("a[href]")?.get(0)?.text()
                val game = it.selectFirst("div.post-streamer-info")
                    ?.select("a[href]")?.get(1)?.text()

                val nsfw = it.selectFirst("div.post-stats-info > span.oi-warning") != null

                val pointsElement = it.selectFirst("small.text-muted > span.oi-arrow-circle-top").parent()

                val points = pointsElement
                    .ownText().replace(Regex("[^\\d.]"), "").toInt()

                val videoUrl = it.selectFirst("video > source").attr("src")

                val sourceUrl = it.selectFirst("div.post-stats-info > a").attr("href")

                DetailsModel(title, streamer, game, points, nsfw, videoUrl, sourceUrl)
            }
        }
    }

    class InvalidPostIdException(postId: Long?) : Throwable("$postId is not a valid post id")
}