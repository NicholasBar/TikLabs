package assignm.nicholasbar.remote.mapper

import assignm.nicholasbar.remote.mapper.livestreamfails.MainModelMapper
import assignm.nicholasbar.remote.test.factory.MainFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
open class MainModelMapperTest {

    private val mapper = MainModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = MainFactory.makeMainModel()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.title, entity.title)
        assertEquals(model.game, entity.game)
        assertEquals(model.streamer, entity.streamer)
        assertEquals(model.points, entity.points)
        assertEquals(model.nsfw, entity.nsfw)
        assertEquals(model.thumbnailUrl, entity.thumbnailUrl)
        assertEquals(model.postId, entity.postId)
        assertEquals(model.videoUrl, entity.videoUrl)
    }

}