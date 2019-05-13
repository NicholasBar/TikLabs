package assignm.nicholasbar.domain.repository

import assignm.nicholasbar.domain.model.*
import io.reactivex.Observable
import io.reactivex.Single

interface LiveStreamFailsRepository {

    fun getDetails(postId: Long? = 0): Single<DetailsObject>

    fun getVideos(
        page: Int,
        timeFrame: TimeFrame,
        order: Order,
        nsfw: Boolean,
        game: String,
        streamer: String
    ): Observable<List<VideoObject>>

    fun getGames(page: Int? = 0): Single<List<GameObject>>

    fun getStreamers(page: Int? = 0): Single<List<StreamerObject>>

    fun getMainList(
        page: Int,
        timeFrame: TimeFrame,
        order: Order,
        nsfw: Boolean,
        game: String,
        streamer: String
    ): Single<List<MainObject>>

}