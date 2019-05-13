package assignm.nicholasbar.remote.model.livestreamfails

data class DetailsModel(
    val title: String,
    val streamer: String?,
    val game: String?,
    val points: Int,
    val nsfw: Boolean,
    val videoUrl: String,
    val sourceUrl: String?
)