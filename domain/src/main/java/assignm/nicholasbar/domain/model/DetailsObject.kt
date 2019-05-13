package assignm.nicholasbar.domain.model

data class DetailsObject(val title: String,
                   val streamer: String?,
                   val game: String?,
                   val points: Int,
                   val nsfw: Boolean,
                   val videoUrl: String,
                   val sourceUrl: String?)