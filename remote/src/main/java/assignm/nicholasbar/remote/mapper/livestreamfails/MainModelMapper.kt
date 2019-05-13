package assignm.nicholasbar.remote.mapper.livestreamfails

import assignm.nicholasbar.data.model.MainEntity
import assignm.nicholasbar.remote.mapper.ModelMapper
import assignm.nicholasbar.remote.model.livestreamfails.MainModel
import javax.inject.Inject

open class MainModelMapper @Inject constructor() : ModelMapper<MainModel, MainEntity> {
    override fun mapFromModel(model: MainModel): MainEntity {
        return MainEntity(model.title, model.streamer, model.game, model.points, model.nsfw, model.thumbnailUrl, model.postId, model.videoUrl)
    }
}