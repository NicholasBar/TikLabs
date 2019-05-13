package assignm.nicholasbar.data.store.livestreamfails

import assignm.nicholasbar.data.repository.livestreamfails.LiveStreamFailsDataStore
import javax.inject.Inject

open class LiveStreamFailsDataStoreFactory @Inject constructor(
    private val liveStreamFailsCacheDataStore: LiveStreamFailsCacheDataStore,
    private val liveStreamFailsRemoteDataStore: LiveStreamFailsRemoteDataStore) {

    open fun getDataStore(accountCached: Boolean,
                          cacheExpired: Boolean): LiveStreamFailsDataStore {
        return if (accountCached && !cacheExpired) {
            liveStreamFailsCacheDataStore
        } else {
            liveStreamFailsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): LiveStreamFailsDataStore {
        return liveStreamFailsCacheDataStore
    }

    fun getRemoteDataStore(): LiveStreamFailsDataStore {
        return liveStreamFailsRemoteDataStore
    }

}