package assignm.nicholasbar.data.mapper.livestreamfails

import assignm.nicholasbar.data.mapper.EntityMapper
import assignm.nicholasbar.data.model.DetailsEntity
import assignm.nicholasbar.domain.model.DetailsObject
import javax.inject.Inject

open class DetailsEntityMapper @Inject constructor() : EntityMapper<DetailsEntity, DetailsObject> {
    override fun mapFromEntity(type: DetailsEntity): DetailsObject {
        return DetailsObject(type.title, type.streamer, type.game, type.points, type.nsfw, type.videoUrl, type.sourceUrl)
    }

    override fun mapToEntity(type: DetailsObject): DetailsEntity {
        return DetailsEntity(type.title, type.streamer, type.game, type.points, type.nsfw, type.videoUrl, type.sourceUrl)
    }
}