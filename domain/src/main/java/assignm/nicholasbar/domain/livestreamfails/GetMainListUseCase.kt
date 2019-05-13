package assignm.nicholasbar.domain.livestreamfails

import assignm.nicholasbar.domain.executor.PostExecutionThread
import assignm.nicholasbar.domain.interactor.SingleUseCase
import assignm.nicholasbar.domain.model.MainObject
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import assignm.nicholasbar.domain.repository.LiveStreamFailsRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetMainListUseCase @Inject constructor(
    private val liveStreamFailsRepository: LiveStreamFailsRepository,
    postExecutionThread: PostExecutionThread
)
    : SingleUseCase<List<MainObject>, GetVideosUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseSingle(params: GetVideosUseCase.Params?): Single<List<MainObject>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return liveStreamFailsRepository.getMainList(params.page, params.timeFrame, params.order,
            params.nsfw, params.game, params.streamer)
    }

    /**
     * Parameters for the get fails -request. To get the "front page", don't pass a [game] or [streamer]
     * parameter. Only pass [game] or [streamer], not both.
     */
    data class Params constructor(val page: Int = 0,
                                  val timeFrame: TimeFrame = TimeFrame.DAY,
                                  val order: Order = Order.HOT,
                                  val nsfw: Boolean = false,
                                  val game: String = "",
                                  val streamer: String = "") {
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