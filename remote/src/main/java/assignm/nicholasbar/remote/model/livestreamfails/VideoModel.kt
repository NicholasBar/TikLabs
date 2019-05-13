package assignm.nicholasbar.remote.model.livestreamfails

data class VideoModel(val title: String,
                      val streamer: String?,
                      val game: String?,
                      val points: Int,
                      val nsfw: Boolean,
                      val thumbnailUrl: String,
                      val postId: Long)