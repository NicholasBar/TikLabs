package assignm.nicholasbar.data.mapper.livestreamfails

import assignm.nicholasbar.data.mapper.EntityMapper
import assignm.nicholasbar.data.model.GameEntity
import assignm.nicholasbar.domain.model.GameObject
import javax.inject.Inject

open class GameEntityMapper @Inject constructor() : EntityMapper<GameEntity, GameObject> {
    override fun mapFromEntity(type: GameEntity): GameObject {
        return GameObject(type.name, type.fails, type.imageUrl)
    }

    override fun mapToEntity(type: GameObject): GameEntity {
        return GameEntity(type.name, type.fails, type.imageUrl)
    }
}