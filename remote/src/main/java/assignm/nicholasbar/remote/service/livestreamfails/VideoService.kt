package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import assignm.nicholasbar.remote.model.livestreamfails.VideoModel
import io.reactivex.Observable

interface VideoService {
    fun getVideos(page: Int, timeFrame: TimeFrame, order: Order, nsfw: Boolean, game: String, streamer: String): Observable<List<VideoModel>>
}