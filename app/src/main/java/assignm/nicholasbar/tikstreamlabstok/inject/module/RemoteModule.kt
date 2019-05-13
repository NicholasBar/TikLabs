package assignm.nicholasbar.tikstreamlabstok.inject.module

import assignm.nicholasbar.data.repository.livestreamfails.LiveStreamFailsRemote
import assignm.nicholasbar.remote.LiveStreamFailsRemoteImpl
import assignm.nicholasbar.remote.service.livestreamfails.*
import assignm.nicholasbar.remote.util.ServiceFactory
import assignm.nicholasbar.tikstreamlabstok.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
            return logging
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @Provides
        @JvmStatic
        fun provideFailService(httpClient: OkHttpClient): VideoService {
            return ServiceFactory.makeFailService(httpClient)
        }

        @Provides
        @JvmStatic
        fun provideDetailsService(httpClient: OkHttpClient): DetailsService {
            return ServiceFactory.makeDetailsService(httpClient)
        }

        @Provides
        @JvmStatic
        fun provideStreamerService(httpClient: OkHttpClient): StreamerService {
            return ServiceFactory.makeStreamerService(httpClient)
        }

        @Provides
        @JvmStatic
        fun provideGameService(httpClient: OkHttpClient): GameService {
            return ServiceFactory.makeGameService(httpClient)
        }
        @Provides
        @JvmStatic
        fun provideVIdeoUrlService(httpClient: OkHttpClient): VideoUrlForVideoService {
            return ServiceFactory.makeVideoUrlForFailService(httpClient)
        }

    }

    @Binds
    abstract fun bindLiveStreamFatilsRemote(liveStreamFailsRemoteImpl: LiveStreamFailsRemoteImpl): LiveStreamFailsRemote

}