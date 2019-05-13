package assignm.nicholasbar.domain.livestreamfails

import assignm.nicholasbar.domain.executor.PostExecutionThread
import assignm.nicholasbar.domain.interactor.SingleUseCase
import assignm.nicholasbar.domain.model.DetailsObject
import assignm.nicholasbar.domain.repository.LiveStreamFailsRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetDetailsUseCase @Inject constructor(
    private val liveStreamFailsRepository: LiveStreamFailsRepository,
    postExecutionThread: PostExecutionThread
)
    : SingleUseCase<DetailsObject, GetDetailsUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseSingle(params: GetDetailsUseCase.Params?): Single<DetailsObject> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return liveStreamFailsRepository.getDetails(params.postId)
    }

    data class Params constructor(val postId: Long) {
        companion object {
            fun forId(postId: Long): Params {
                return Params(postId)
            }
        }
    }

}