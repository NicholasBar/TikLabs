package assignm.nicholasbar.data.mapper.livestreamfails

import assignm.nicholasbar.data.mapper.EntityMapper
import assignm.nicholasbar.data.model.MainEntity
import assignm.nicholasbar.domain.model.MainObject
import javax.inject.Inject

open class MainEntityMapper @Inject constructor() : EntityMapper<MainEntity, MainObject> {
    override fun mapFromEntity(type: MainEntity): MainObject {
        return MainObject(type.title, type.streamer, type.game, type.points, type.nsfw, type.thumbnailUrl, type.postId, type.videoUrl)
    }

    override fun mapToEntity(type: MainObject): MainEntity {
        return MainEntity(type.title, type.streamer, type.game, type.points, type.nsfw, type.thumbnailUrl, type.postId, type.videoUrl)
    }
}