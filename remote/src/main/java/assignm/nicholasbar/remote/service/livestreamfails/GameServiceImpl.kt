package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.remote.model.livestreamfails.GameModel
import assignm.nicholasbar.remote.util.InvalidEndpointException
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

open class GameServiceImpl(private val okHttpClient: OkHttpClient) : GameService {
    companion object {
        private const val ENDPOINT = "https://livestreamfails.com/load/loadGames.php"
    }

    override fun getGames(page: Int?): Single<List<GameModel>> {
        return Single.fromCallable {
            val models = mutableListOf<GameModel>()
            val uri = HttpUrl.parse(ENDPOINT)?.newBuilder()
                ?.addQueryParameter("loadGameOrder", "amount")
                ?.addQueryParameter("loadGamePage", page.toString())
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

                    models.add(GameModel(name, fails, imageUrl))
                }
            }

            models.toList()
        }
    }
}