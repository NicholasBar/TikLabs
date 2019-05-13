package assignm.nicholasbar.data.mapper.livestreamfails

import assignm.nicholasbar.data.mapper.EntityMapper
import assignm.nicholasbar.data.model.VideoEntity
import assignm.nicholasbar.domain.model.VideoObject
import javax.inject.Inject

open class VideoEntityMapper @Inject constructor() : EntityMapper<VideoEntity, VideoObject> {
    override fun mapFromEntity(type: VideoEntity): VideoObject {
        return VideoObject(type.title, type.streamer, type.game, type.points, type.nsfw, type.thumbnailUrl, type.postId)
    }

    override fun mapToEntity(type: VideoObject): VideoEntity {
        return VideoEntity(type.title, type.streamer, type.game, type.points, type.nsfw, type.thumbnailUrl, type.postId)
    }
}