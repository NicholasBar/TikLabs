package assignm.nicholasbar.data.store.livestreamfails

import assignm.nicholasbar.data.model.*
import assignm.nicholasbar.data.repository.livestreamfails.LiveStreamFailsCache
import assignm.nicholasbar.data.repository.livestreamfails.LiveStreamFailsDataStore
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

open class LiveStreamFailsCacheDataStore @Inject constructor(
    private val liveStreamFailsCache: LiveStreamFailsCache
) : LiveStreamFailsDataStore {

    override fun getMainList(page: Int, timeFrame: TimeFrame, order: Order, nsfw: Boolean, game: String, streamer: String): Single<List<MainEntity>> {
        throw UnsupportedOperationException("getMainList request isn't supported here...")
    }

    override fun getDetails(postId: Long?): Single<DetailsEntity> {
        throw UnsupportedOperationException("getDetails request isn't supported here...")
    }

    override fun getVideos(
        page: Int,
        timeFrame: TimeFrame,
        order: Order,
        nsfw: Boolean,
        game: String,
        streamer: String
    ): Observable<List<VideoEntity>> {
        throw UnsupportedOperationException("getVideos request isn't supported here...")
    }

    override fun getGames(page: Int?): Single<List<GameEntity>> {
        throw UnsupportedOperationException("getGames request isn't supported here...")
    }

    override fun getStreamers(page: Int?): Single<List<StreamerEntity>> {
        throw UnsupportedOperationException("getStreamers request isn't supported here...")
    }

}