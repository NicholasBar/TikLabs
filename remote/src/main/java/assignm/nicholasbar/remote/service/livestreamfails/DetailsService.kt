package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.remote.model.livestreamfails.DetailsModel
import io.reactivex.Single

interface DetailsService {
    fun getDetails(postId: Long?): Single<DetailsModel>
}