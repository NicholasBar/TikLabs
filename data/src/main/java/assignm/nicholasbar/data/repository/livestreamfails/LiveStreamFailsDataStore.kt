package assignm.nicholasbar.data.repository.livestreamfails

import assignm.nicholasbar.data.model.*
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import io.reactivex.Observable
import io.reactivex.Single

interface LiveStreamFailsDataStore {

    fun getDetails(postId: Long?): Single<DetailsEntity>

    fun getVideos(page: Int, timeFrame: TimeFrame, order: Order,
                  nsfw: Boolean, game: String, streamer: String): Observable<List<VideoEntity>>

    fun getGames(page: Int?): Single<List<GameEntity>>

    fun getStreamers(page: Int?): Single<List<StreamerEntity>>

    fun getMainList(
        page: Int,
        timeFrame: TimeFrame,
        order: Order,
        nsfw: Boolean,
        game: String,
        streamer: String
    ): Single<List<MainEntity>>


}