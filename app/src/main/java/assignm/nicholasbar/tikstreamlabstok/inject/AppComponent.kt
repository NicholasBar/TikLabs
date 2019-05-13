package assignm.nicholasbar.tikstreamlabstok.inject

import assignm.nicholasbar.tikstreamlabstok.inject.module.CacheModule
import assignm.nicholasbar.tikstreamlabstok.inject.module.DataModule
import assignm.nicholasbar.tikstreamlabstok.inject.module.NetworkModule
import assignm.nicholasbar.tikstreamlabstok.inject.module.RemoteModule
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsApplication
import assignm.nicholasbar.tikstreamlabstok.ui.camera.CameraBuilder
import assignm.nicholasbar.tikstreamlabstok.ui.hire.HireBuilder
import assignm.nicholasbar.tikstreamlabstok.ui.home.HomeBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    HomeBuilder::class,
    CameraBuilder::class,
    HireBuilder::class,
    DataModule::class,
    CacheModule::class,
    RemoteModule::class,
    NetworkModule::class
])
interface AppComponent : AndroidInjector<TokLabsApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TokLabsApplication>()
}