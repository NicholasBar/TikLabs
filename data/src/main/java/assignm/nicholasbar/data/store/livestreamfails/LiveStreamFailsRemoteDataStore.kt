package assignm.nicholasbar.data.store.livestreamfails

import assignm.nicholasbar.data.model.*
import assignm.nicholasbar.data.repository.livestreamfails.LiveStreamFailsDataStore
import assignm.nicholasbar.data.repository.livestreamfails.LiveStreamFailsRemote
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

open class LiveStreamFailsRemoteDataStore @Inject constructor(
    private val liveStreamFailsRemote: LiveStreamFailsRemote
) : LiveStreamFailsDataStore {

    override fun getDetails(postId: Long?): Single<DetailsEntity> {
        return liveStreamFailsRemote.getDetails(postId)
    }

    override fun getVideos(
        page: Int,
        timeFrame: TimeFrame,
        order: Order,
        nsfw: Boolean,
        game: String,
        streamer: String
    ): Observable<List<VideoEntity>> {
        return liveStreamFailsRemote.getVideos(page, timeFrame, order, nsfw, game, streamer)
    }

    override fun getGames(page: Int?): Single<List<GameEntity>> {
        return liveStreamFailsRemote.getGames(page)
    }

    override fun getStreamers(page: Int?): Single<List<StreamerEntity>> {
        return liveStreamFailsRemote.getStreamers(page)
    }

    override fun getMainList(
        page: Int,
        timeFrame: TimeFrame,
        order: Order,
        nsfw: Boolean,
        game: String,
        streamer: String
    ): Single<List<MainEntity>>{
        return liveStreamFailsRemote.getMainList(page, timeFrame, order, nsfw, game, streamer)
    }
}