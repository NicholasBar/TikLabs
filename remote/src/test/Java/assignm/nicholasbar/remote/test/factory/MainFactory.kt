package assignm.nicholasbar.remote.test.factory

import assignm.nicholasbar.remote.model.livestreamfails.MainModel
import assignm.nicholasbar.remote.test.factory.BaseFactory.randomBoolean
import assignm.nicholasbar.remote.test.factory.BaseFactory.randomInt
import assignm.nicholasbar.remote.test.factory.BaseFactory.randomLong
import assignm.nicholasbar.remote.test.factory.BaseFactory.randomUuid

object MainFactory {

    fun makeMainModel(): MainModel {
        return MainModel(randomUuid(), randomUuid(), randomUuid(), randomInt(), randomBoolean(), randomUuid(), randomLong(), randomUuid())
    }

    fun makeMainModelList(count: Int): List<MainModel> {
        val entities = mutableListOf<MainModel>()
        repeat(count) {
            entities.add(makeMainModel())
        }
        return entities
    }
}