package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.remote.model.livestreamfails.VideoModel
import assignm.nicholasbar.remote.model.livestreamfails.MainModel
import io.reactivex.Observable

interface VideoUrlForVideoService {
    fun getVideoUrl(video: VideoModel?): Observable<MainModel>
}