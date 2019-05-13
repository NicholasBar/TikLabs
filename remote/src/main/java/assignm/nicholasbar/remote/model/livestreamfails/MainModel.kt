package assignm.nicholasbar.remote.model.livestreamfails

data class MainModel(val title: String,
                     val streamer: String?,
                     val game: String?,
                     val points: Int,
                     val nsfw: Boolean,
                     val thumbnailUrl: String,
                     val postId: Long,
                     val videoUrl: String)