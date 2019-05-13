package assignm.nicholasbar.data.model

data class DetailsEntity(
    val title: String,
    val streamer: String?,
    val game: String?,
    val points: Int,
    val nsfw: Boolean,
    val videoUrl: String,
    val sourceUrl: String?
)