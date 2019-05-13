package assignm.nicholasbar.remote.mapper.livestreamfails

import assignm.nicholasbar.data.model.VideoEntity
import assignm.nicholasbar.remote.mapper.ModelMapper
import assignm.nicholasbar.remote.model.livestreamfails.VideoModel
import javax.inject.Inject

open class VideoModelMapper @Inject constructor() : ModelMapper<VideoModel, VideoEntity> {
    override fun mapFromModel(model: VideoModel): VideoEntity {
        return VideoEntity(model.title, model.streamer, model.game, model.points, model.nsfw, model.thumbnailUrl, model.postId)
    }
}