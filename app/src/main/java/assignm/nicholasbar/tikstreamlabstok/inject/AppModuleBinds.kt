package assignm.nicholasbar.tikstreamlabstok.inject

import android.app.Application
import assignm.nicholasbar.domain.executor.PostExecutionThread
import assignm.nicholasbar.tikstreamlabstok.UiThread
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModuleBinds {
    @Binds
    abstract fun provideApplication(bind: TokLabsApplication): Application

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}