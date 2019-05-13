package assignm.nicholasbar.data.model

data class VideoEntity(val title: String,
                       val streamer: String?,
                       val game: String?,
                       val points: Int,
                       val nsfw: Boolean,
                       val thumbnailUrl: String,
                       val postId: Long)