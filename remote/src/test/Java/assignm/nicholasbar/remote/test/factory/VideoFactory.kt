package assignm.nicholasbar.remote.test.factory

import assignm.nicholasbar.remote.model.livestreamfails.VideoModel

object VideoFactory {

    fun makeVideoModel(): VideoModel {
        return VideoModel(BaseFactory.randomUuid(), BaseFactory.randomUuid(), BaseFactory.randomUuid(), BaseFactory.randomInt(), BaseFactory.randomBoolean(), BaseFactory.randomUuid(), BaseFactory.randomLong())
    }

    fun makeVideoModelList(count: Int): List<VideoModel> {
        val entities = mutableListOf<VideoModel>()
        repeat(count) {
            entities.add(makeVideoModel())
        }
        return entities
    }
}