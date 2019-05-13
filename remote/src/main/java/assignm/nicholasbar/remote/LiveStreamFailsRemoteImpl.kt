package assignm.nicholasbar.remote

import assignm.nicholasbar.data.model.*
import assignm.nicholasbar.data.repository.livestreamfails.LiveStreamFailsRemote
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import assignm.nicholasbar.remote.mapper.livestreamfails.*
import assignm.nicholasbar.remote.service.livestreamfails.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.internal.operators.observable.ObservableFromIterable
import javax.inject.Inject

class LiveStreamFailsRemoteImpl @Inject constructor(
        private val detailsService: DetailsService,
        private val detailsModelMapper: DetailsModelMapper,
        private val videoService: VideoService,
        private val gameService: GameService,
        private val videoUrlForVideoService: VideoUrlForVideoService,
        private val videoModelMapper: VideoModelMapper,
        private val mainModelMapper: MainModelMapper,
        private val gameModelMapper: GameModelMapper,
        private val streamerService: StreamerService,
        private val streamerModelMapper: StreamerModelMapper
) : LiveStreamFailsRemote {
    override fun getDetails(postId: Long?): Single<DetailsEntity> {
        return detailsService.getDetails(postId)
                .map {
                    detailsModelMapper.mapFromModel(it)
                }
    }

    override fun getVideos(
            page: Int,
            timeFrame: TimeFrame,
            order: Order,
            nsfw: Boolean,
            game: String,
            streamer: String
    ): Observable<List<VideoEntity>> {
        return videoService.getVideos(page, timeFrame, order, nsfw, game, streamer)
                .map {
                    it.map { listItem ->
                        videoModelMapper.mapFromModel(listItem)
                    }
                }
    }

    override fun getGames(page: Int?): Single<List<GameEntity>> {
        return gameService.getGames(page)
                .map {
                    it.map { listItem ->
                        gameModelMapper.mapFromModel(listItem)
                    }
                }
    }

    override fun getStreamers(page: Int?): Single<List<StreamerEntity>> {
        return streamerService.getStreamers(page)
                .map {
                    it.map { listItem ->
                        streamerModelMapper.mapFromModel(listItem)
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
    ): Single<List<MainEntity>> {
        return videoService.getVideos(page, timeFrame, order, nsfw, game, streamer)
                .flatMap { videoList -> ObservableFromIterable(videoList) }
                .flatMap { video -> videoUrlForVideoService.getVideoUrl(video) }
                .map { mainModelMapper.mapFromModel(it) }
                .toList()
    }

}