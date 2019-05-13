package assignm.nicholasbar.domain.livestreamfails

import assignm.nicholasbar.domain.executor.PostExecutionThread
import assignm.nicholasbar.domain.interactor.SingleUseCase
import assignm.nicholasbar.domain.model.StreamerObject
import assignm.nicholasbar.domain.repository.LiveStreamFailsRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetStreamersUseCase @Inject constructor(
    private val liveStreamFailsRepository: LiveStreamFailsRepository,
    postExecutionThread: PostExecutionThread
)
    : SingleUseCase<List<StreamerObject>, GetStreamersUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseSingle(params: GetStreamersUseCase.Params?): Single<List<StreamerObject>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return liveStreamFailsRepository.getStreamers(params.page)
    }

    data class Params constructor(val page: Int) {
        companion object {
            fun forPage(page: Int): Params {
                return Params(page)
            }
        }
    }

}