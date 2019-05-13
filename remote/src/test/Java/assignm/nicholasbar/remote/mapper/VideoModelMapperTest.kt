package assignm.nicholasbar.remote.mapper

import assignm.nicholasbar.remote.mapper.livestreamfails.VideoModelMapper
import assignm.nicholasbar.remote.test.factory.VideoFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
open class VideoModelMapperTest {

    private val mapper = VideoModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = VideoFactory.makeVideoModel()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.title, entity.title)
        assertEquals(model.game, entity.game)
        assertEquals(model.streamer, entity.streamer)
        assertEquals(model.points, entity.points)
        assertEquals(model.nsfw, entity.nsfw)
        assertEquals(model.thumbnailUrl, entity.thumbnailUrl)
        assertEquals(model.postId, entity.postId)
    }

}