package assignm.nicholasbar.tikstreamlabstok.ui.base

import android.app.Application
import assignm.nicholasbar.tikstreamlabstok.inject.DaggerAppComponent
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import im.ene.toro.exoplayer.Config
import im.ene.toro.exoplayer.ExoCreator
import im.ene.toro.exoplayer.MediaSourceBuilder
import im.ene.toro.exoplayer.ToroExo
import java.io.File

class TokLabsApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        initExoPlayerConfig()
    }

    companion object {
        var cacheFile = 2 * 1024 * 1024.toLong() // size of each cache file.
        var demoApp: TokLabsApplication? = null
        var config: Config? = null
        var exoCreator: ExoCreator? = null
    }

    private fun initExoPlayerConfig() {
        demoApp = this
        val cache = SimpleCache(
            File(filesDir.path + "/toro_cache"),
            LeastRecentlyUsedCacheEvictor(cacheFile)
        )
        config = Config.Builder()
            .setMediaSourceBuilder(MediaSourceBuilder.LOOPING)
            .setCache(cache)
            .build()
        exoCreator = ToroExo.with(this).getCreator(config)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level >= Application.TRIM_MEMORY_BACKGROUND) ToroExo.with(this).cleanUp()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}