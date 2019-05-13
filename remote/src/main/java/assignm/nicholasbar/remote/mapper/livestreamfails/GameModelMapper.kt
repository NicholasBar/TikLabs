package assignm.nicholasbar.remote.mapper.livestreamfails

import assignm.nicholasbar.data.model.GameEntity
import assignm.nicholasbar.remote.mapper.ModelMapper
import assignm.nicholasbar.remote.model.livestreamfails.GameModel
import javax.inject.Inject

open class GameModelMapper @Inject constructor() : ModelMapper<GameModel, GameEntity> {
    override fun mapFromModel(model: GameModel): GameEntity {
        return GameEntity(model.name, model.fails, model.imageUrl)
    }
}