package assignm.nicholasbar.remote.service.livestreamfails

import assignm.nicholasbar.remote.model.livestreamfails.GameModel
import io.reactivex.Single

interface GameService {
    fun getGames(page: Int? = 0): Single<List<GameModel>>
}