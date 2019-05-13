package assignm.nicholasbar.tikstreamlabstok.mapper

import assignm.nicholasbar.domain.model.MainObject
import assignm.nicholasbar.tikstreamlabstok.model.MainView
import javax.inject.Inject

class MainViewMapper @Inject constructor(): ViewMapper<MainView, MainObject> {

    override fun mapToView(presentation: MainObject): MainView {
        return MainView(presentation.title, presentation.streamer, presentation.game, presentation.points, presentation.nsfw, presentation.thumbnailUrl, presentation.postId, presentation.videoUrl)
    }
}