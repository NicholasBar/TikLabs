package assignm.nicholasbar.remote

import assignm.nicholasbar.data.mapper.livestreamfails.GameEntityMapper
import assignm.nicholasbar.data.mapper.livestreamfails.VideoEntityMapper
import assignm.nicholasbar.data.model.VideoEntity
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import assignm.nicholasbar.remote.mapper.livestreamfails.*
import assignm.nicholasbar.remote.model.livestreamfails.VideoModel
import assignm.nicholasbar.remote.service.livestreamfails.*
import assignm.nicholasbar.remote.test.factory.VideoFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable

class LiveStreamFailsRemoteImplTest {
    private lateinit var videoModelMapper: VideoModelMapper
    private lateinit var videoService: VideoService
    private lateinit var gameModelMapper: GameModelMapper
    private lateinit var gameService: GameService
    private lateinit var streamerModelMapper: StreamerModelMapper
    private lateinit var streamerService: StreamerService
    private lateinit var detailModelMapper: DetailsModelMapper
    private lateinit var detailService: DetailsService
    private lateinit var videoUrlForVideoService: VideoUrlForVideoService
    private lateinit var mainModelMapper: MainModelMapper

    private lateinit var remoteImpl: LiveStreamFailsRemoteImpl

    @Before
    fun setUp() {
        videoModelMapper = mock()
        videoService = mock()
        gameModelMapper = mock()
        gameService = mock()
        streamerModelMapper = mock()
        streamerService = mock()
        detailModelMapper = mock()
        detailService = mock()
        videoUrlForVideoService = mock()
        mainModelMapper = mock()

        remoteImpl = LiveStreamFailsRemoteImpl(detailService,detailModelMapper,videoService,gameService,videoUrlForVideoService,videoModelMapper,mainModelMapper,gameModelMapper,streamerService,streamerModelMapper)
    }

    @Test
    fun getFailsCompletes() {
        stubGetFails(Observable.just(
                VideoFactory.makeVideoModelList(2)
        ))

        val testObserver = remoteImpl.getVideos(0, TimeFrame.ALL_TIME, Order.TOP, false, "", "").test()

        testObserver.assertComplete()
    }

    @Test
    fun getFailsReturnsData() {
        val models = VideoFactory.makeVideoModelList(2)
        stubGetFails(Observable.just(models))

        val entities = mutableListOf<VideoEntity>()
        models.forEach {
            entities.add(videoModelMapper.mapFromModel(it))
        }

        val testObserver = remoteImpl.getVideos(0, TimeFrame.ALL_TIME, Order.TOP, false, "", "").test()

        testObserver.assertValue(entities)
    }

    private fun stubGetFails(single: Observable<List<VideoModel>>) {
        whenever(videoService.getVideos(0, TimeFrame.ALL_TIME, Order.TOP, false, "", ""))
                .thenReturn(single)
    }
}