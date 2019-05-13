package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.remote.model.livestreamfails.StreamerModel
import io.reactivex.Single

interface StreamerService {
    fun getStreamers(page: Int? = 0): Single<List<StreamerModel>>
}