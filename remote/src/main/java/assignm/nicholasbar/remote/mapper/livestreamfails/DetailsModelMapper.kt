package assignm.nicholasbar.remote.mapper.livestreamfails

import assignm.nicholasbar.data.model.DetailsEntity
import assignm.nicholasbar.remote.mapper.ModelMapper
import assignm.nicholasbar.remote.model.livestreamfails.DetailsModel
import javax.inject.Inject

open class DetailsModelMapper @Inject constructor() : ModelMapper<DetailsModel, DetailsEntity> {
    override fun mapFromModel(model: DetailsModel): DetailsEntity {
        return DetailsEntity(model.title, model.streamer, model.game, model.points, model.nsfw, model.videoUrl, model.sourceUrl)
    }
}