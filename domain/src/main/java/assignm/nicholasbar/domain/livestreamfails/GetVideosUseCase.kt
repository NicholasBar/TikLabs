package assignm.nicholasbar.domain.livestreamfails

import assignm.nicholasbar.domain.executor.PostExecutionThread
import assignm.nicholasbar.domain.interactor.ObservableUseCase
import assignm.nicholasbar.domain.model.VideoObject
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import assignm.nicholasbar.domain.repository.LiveStreamFailsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetVideosUseCase @Inject constructor(
    private val liveStreamFailsRepository: LiveStreamFailsRepository,
    postExecutionThread: PostExecutionThread
)
    : ObservableUseCase<List<VideoObject>, GetVideosUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: GetVideosUseCase.Params?): Observable<List<VideoObject>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return liveStreamFailsRepository.getVideos(params.page, params.timeFrame, params.order,
            params.nsfw, params.game, params.streamer)
    }

    /**
     * Parameters for the get videos -request. To get the "front page", don't pass a [game] or [streamer]
     * parameter. Only pass [game] or [streamer], not both.
     */
    data class Params constructor(var page: Int = 0,
                                  var timeFrame: TimeFrame = TimeFrame.DAY,
                                  var order: Order = Order.HOT,
                                  var nsfw: Boolean = false,
                                  var game: String = "",
                                  var streamer: String = "") {
        companion object {
            fun forPage(page: Int): Params {
                return Params(page)
            }
        }
        fun equalsIgnorePage(params: Params): Boolean {
            return params.timeFrame == timeFrame
                    && params.order == order
                    && params.nsfw == nsfw
                    && params.game == game
                    && params.streamer == streamer
        }
    }

}