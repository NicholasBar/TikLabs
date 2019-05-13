package assignm.nicholasbar.tikstreamlabstok.inject.module

import assignm.nicholasbar.data.LiveStreamFailsDataRepository
import assignm.nicholasbar.domain.repository.LiveStreamFailsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindLiveStreamFailsRepository(liveStreamFailsDataRepository: LiveStreamFailsDataRepository) : LiveStreamFailsRepository

}