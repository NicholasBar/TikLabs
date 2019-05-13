package assignm.nicholasbar.remote.mapper.livestreamfails

import assignm.nicholasbar.data.model.StreamerEntity
import assignm.nicholasbar.remote.mapper.ModelMapper
import assignm.nicholasbar.remote.model.livestreamfails.StreamerModel
import javax.inject.Inject

open class StreamerModelMapper @Inject constructor() : ModelMapper<StreamerModel, StreamerEntity> {
    override fun mapFromModel(model: StreamerModel): StreamerEntity {
        return StreamerEntity(model.name, model.fails, model.imageUrl)
    }
}