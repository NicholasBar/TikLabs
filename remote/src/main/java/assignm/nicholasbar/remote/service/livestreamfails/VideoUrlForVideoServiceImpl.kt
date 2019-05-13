package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.remote.model.livestreamfails.VideoModel
import assignm.nicholasbar.remote.model.livestreamfails.MainModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

class VideoUrlForVideoServiceImpl(private val httpClient: OkHttpClient) : VideoUrlForVideoService {
    companion object {
        private const val ENDPOINT = "https://livestreamfails.com/post/%d"
    }

    override fun getVideoUrl(video: VideoModel?): Observable<MainModel> {
        return Observable.fromCallable {
            val request = Request.Builder().url(String.format(ENDPOINT, video?.postId)).get().build()
            val response = httpClient.newCall(request).execute()
            val responseUrl = response.networkResponse()?.request()?.url().toString()

            if (video?.postId == null || responseUrl.contains("post_not_found")) {
                throw InvalidPostIdException(video?.postId)
            }

            val doc = Jsoup.parse(response.body()?.string())
            doc?.let {

                val videoUrl = it.selectFirst("video > source").attr("src")

                MainModel(
                    video.title,
                    video.streamer,
                    video.game,
                    video.points,
                    video.nsfw,
                    video.thumbnailUrl,
                    video.postId,
                    videoUrl)
            }
        }
    }


    class InvalidPostIdException(postId: Long?) : Throwable("$postId is not a valid post id")
}