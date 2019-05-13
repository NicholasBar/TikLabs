package assignm.nicholasbar.data.mapper.livestreamfails

import assignm.nicholasbar.data.mapper.EntityMapper
import assignm.nicholasbar.data.model.StreamerEntity
import assignm.nicholasbar.domain.model.StreamerObject
import javax.inject.Inject

open class StreamerEntityMapper @Inject constructor() : EntityMapper<StreamerEntity, StreamerObject> {
    override fun mapFromEntity(type: StreamerEntity): StreamerObject {
        return StreamerObject(type.name, type.fails, type.imageUrl)
    }

    override fun mapToEntity(type: StreamerObject): StreamerEntity {
        return StreamerEntity(type.name, type.fails, type.imageUrl)
    }
}