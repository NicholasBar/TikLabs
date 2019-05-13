package assignm.nicholasbar.remote.util

import assignm.nicholasbar.remote.service.livestreamfails.*
import okhttp3.OkHttpClient

object ServiceFactory {
    fun makeFailService(okHttpClient: OkHttpClient): VideoService {
        return VideoServiceImpl(okHttpClient)
    }

    fun makeGameService(okHttpClient: OkHttpClient): GameService {
        return GameServiceImpl(okHttpClient)
    }

    fun makeStreamerService(okHttpClient: OkHttpClient): StreamerService {
        return StreamerServiceImpl(okHttpClient)
    }

    fun makeDetailsService(okHttpClient: OkHttpClient): DetailsService {
        return DetailsServiceImpl(okHttpClient)
    }

    fun makeVideoUrlForFailService(okHttpClient: OkHttpClient): VideoUrlForVideoService {
        return VideoUrlForVideoServiceImpl(okHttpClient)
    }
}