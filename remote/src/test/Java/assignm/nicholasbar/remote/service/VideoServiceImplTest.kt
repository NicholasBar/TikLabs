package assignm.nicholasbar.remote.service

import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import assignm.nicholasbar.remote.service.livestreamfails.VideoServiceImpl
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class VideoServiceImplTest {
    private lateinit var service: VideoServiceImpl

    @Before
    fun setUp() {
        service = Mockito.spy(VideoServiceImpl(OkHttpClient()))
    }

    @Test
    fun getFailsCompletes() {
        service.getVideos(0, TimeFrame.ALL_TIME, Order.TOP, false, "", "")
                .test()
                .assertComplete()
    }

    @Test
    fun getFailsReturnsData() {
        val observer = service.getVideos(0, TimeFrame.ALL_TIME, Order.TOP, false, "", "")
                .test()
                .assertComplete()

        val models = observer.values()[0]

        assertTrue(models.isNotEmpty(), "Model list is empty")
    }

    @Test
    fun getFailsPostModeStreamer() {
        val observer = service.getVideos(0, TimeFrame.ALL_TIME, Order.TOP, false, "", "sodapoppin")
                .test()
                .assertComplete()

        val models = observer.values()[0]

        assertTrue(models.isNotEmpty(), "Model list is empty")
    }

    @Test
    fun getFailsPostModeGame() {
        val observer = service.getVideos(0, TimeFrame.ALL_TIME, Order.TOP, false, "IRL", "")
                .test()
                .assertComplete()

        val models = observer.values()[0]

        assertTrue(models.isNotEmpty(), "Model list is empty")
    }

    @Test
    fun getFailsReturnsEmpty() {
        val observer = service.getVideos(Int.MAX_VALUE, TimeFrame.ALL_TIME, Order.TOP, false, "", "")
                .test()
                .assertComplete()

        val models = observer.values()[0]

        assertTrue(models.isEmpty(), "Model list is not empty")
    }

    @Test
    fun getPostModeReturnsStreamer() {
        val actual = service.getPostMode("a", "")

        assertEquals("streamer", actual)
    }

    @Test
    fun getPostModeReturnsGame() {
        val actual = service.getPostMode("", "b")

        assertEquals("game", actual)
    }

    @Test
    fun getPostModeReturnsStandard() {
        val actual = service.getPostMode("", "")

        assertEquals("standard", actual)
    }

    @Test
    fun getRequestUrlReturnsCorrectStandard() {
        val actual = service.getRequestUrl(0, TimeFrame.ALL_TIME, Order.TOP, false, "", "")

        val expected = HttpUrl.parse(VideoServiceImpl.ENDPOINT)!!.newBuilder()
                .addQueryParameter("loadPostMode", "standard")
                .addQueryParameter("loadPostNSFW", "0")
                .addQueryParameter("loadPostOrder", Order.TOP.value)
                .addQueryParameter("loadPostPage", "0")
                .addQueryParameter("loadPostTimeFrame", TimeFrame.ALL_TIME.value)
                .build()

        assertEquals(expected, actual)
    }

    @Test
    fun getRequestUrlReturnsCorrectStandardNsfw() {
        val actual = service.getRequestUrl(0, TimeFrame.ALL_TIME, Order.TOP, true, "", "")

        val expected = HttpUrl.parse(VideoServiceImpl.ENDPOINT)!!.newBuilder()
                .addQueryParameter("loadPostMode", "standard")
                .addQueryParameter("loadPostNSFW", "1")
                .addQueryParameter("loadPostOrder", Order.TOP.value)
                .addQueryParameter("loadPostPage", "0")
                .addQueryParameter("loadPostTimeFrame", TimeFrame.ALL_TIME.value)
                .build()

        assertEquals(expected, actual)
    }

    @Test
    fun getRequestUrlReturnsCorrectGame() {
        val actual = service.getRequestUrl(0, TimeFrame.ALL_TIME, Order.TOP, false, "game", "")

        val expected = HttpUrl.parse(VideoServiceImpl.ENDPOINT)!!.newBuilder()
                .addQueryParameter("loadPostMode", "game")
                .addQueryParameter("loadPostNSFW", "0")
                .addQueryParameter("loadPostOrder", Order.TOP.value)
                .addQueryParameter("loadPostPage", "0")
                .addQueryParameter("loadPostTimeFrame", TimeFrame.ALL_TIME.value)
                .addQueryParameter("loadPostModeGame", "game")
                .build()

        assertEquals(expected, actual)
    }

    @Test
    fun getRequestUrlReturnsCorrectStreamer() {
        val actual = service.getRequestUrl(0, TimeFrame.ALL_TIME, Order.TOP, false, "", "streamer")

        val expected = HttpUrl.parse(VideoServiceImpl.ENDPOINT)!!.newBuilder()
                .addQueryParameter("loadPostMode", "streamer")
                .addQueryParameter("loadPostNSFW", "0")
                .addQueryParameter("loadPostOrder", Order.TOP.value)
                .addQueryParameter("loadPostPage", "0")
                .addQueryParameter("loadPostTimeFrame", TimeFrame.ALL_TIME.value)
                .addQueryParameter("loadPostModeStreamer", "streamer")
                .build()

        assertEquals(expected, actual)
    }
}