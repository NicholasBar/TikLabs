package assignm.nicholasbar.tikstreamlabstok.inject.module

import assignm.nicholasbar.cache.LiveStreamFailsCacheImpl
import assignm.nicholasbar.data.repository.livestreamfails.LiveStreamFailsCache
import dagger.Binds
import dagger.Module

@Module
abstract class CacheModule {

    @Binds
    abstract fun bindLiveStreamFailsCache(liveStreamFailsCache: LiveStreamFailsCacheImpl): LiveStreamFailsCache

}