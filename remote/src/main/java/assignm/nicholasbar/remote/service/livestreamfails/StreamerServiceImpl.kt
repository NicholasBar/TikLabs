package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.remote.model.livestreamfails.StreamerModel
import assignm.nicholasbar.remote.util.InvalidEndpointException
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

open class StreamerServiceImpl(private val okHttpClient: OkHttpClient) : StreamerService {
    companion object {
        private const val ENDPOINT = "https://livestreamfails.com/load/loadStreamers.php"
    }

    override fun getStreamers(page: Int?): Single<List<StreamerModel>> {
        return Single.fromCallable {
            val models = mutableListOf<StreamerModel>()
            val uri = HttpUrl.parse(ENDPOINT)?.newBuilder()
                ?.addQueryParameter("loadStreamerOrder", "amount")
                ?.addQueryParameter("loadStreamerPage", page.toString())
                ?.build() ?: throw InvalidEndpointException()

            val request = Request.Builder().url(uri).get().build()
            val html = okHttpClient.newCall(request).execute().body()?.string()
            val doc = Jsoup.parse(html)

            doc.select("div.post-card")?.let {
                it.forEach { card ->
                    val name = card.selectFirst("p.card-text.title").text()
                    val fails = card.selectFirst("small.text-muted")
                        .text().replace(Regex("[^\\d.]"), "").toInt()
                    val imageUrl = card.selectFirst("img.card-img-top").attr("src")

                    models.add(StreamerModel(name, fails, imageUrl))
                }
            }

            models.toList()
        }
    }
}