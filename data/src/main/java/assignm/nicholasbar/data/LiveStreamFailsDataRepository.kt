package assignm.nicholasbar.data

import assignm.nicholasbar.data.mapper.livestreamfails.*
import assignm.nicholasbar.data.store.livestreamfails.LiveStreamFailsDataStoreFactory
import assignm.nicholasbar.domain.model.*
import assignm.nicholasbar.domain.repository.LiveStreamFailsRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LiveStreamFailsDataRepository @Inject constructor(
        private val factory: LiveStreamFailsDataStoreFactory,
        private val detailsEntityMapper: DetailsEntityMapper,
        private val videoEntityMapper: VideoEntityMapper,
        private val gameEntityMapper: GameEntityMapper,
        private val streamerEntityMapper: StreamerEntityMapper,
        private val mainEntityMapper: MainEntityMapper
) : LiveStreamFailsRepository {
    override fun getDetails(postId: Long?): Single<DetailsObject> {
        return factory.getRemoteDataStore().getDetails(postId)
            .map {
                detailsEntityMapper.mapFromEntity(it)
            }
    }

    override fun getVideos(
        page: Int,
        timeFrame: TimeFrame,
        order: Order,
        nsfw: Boolean,
        game: String,
        streamer: String
    ): Observable<List<VideoObject>> {
        return factory.getRemoteDataStore().getVideos(page, timeFrame, order, nsfw, game, streamer)
            .map { list ->
                list.map { listItem ->
                    videoEntityMapper.mapFromEntity(listItem)
                }
            }
    }

    override fun getGames(page: Int?): Single<List<GameObject>> {
        return factory.getRemoteDataStore().getGames(page)
            .map { list ->
                list.map { listItem ->
                    gameEntityMapper.mapFromEntity(listItem)
                }
            }
    }

    override fun getStreamers(page: Int?): Single<List<StreamerObject>> {
        return factory.getRemoteDataStore().getStreamers(page)
            .map { list ->
                list.map { listItem ->
                    streamerEntityMapper.mapFromEntity(listItem)
                }
            }
    }

    override fun getMainList(
        page: Int,
        timeFrame: TimeFrame,
        order: Order,
        nsfw: Boolean,
        game: String,
        streamer: String
    ): Single<List<MainObject>> {
        return factory.getRemoteDataStore().getMainList(page, timeFrame, order, nsfw, game, streamer)
            .map { list ->
                list.map { listItem ->
                    mainEntityMapper.mapFromEntity(listItem)
                }
            }
    }

}