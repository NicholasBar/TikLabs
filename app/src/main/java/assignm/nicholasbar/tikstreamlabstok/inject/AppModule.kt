package assignm.nicholasbar.tikstreamlabstok.inject

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsApplication
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {
    @Provides
    fun provideContext(application: TokLabsApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideAppPreferences(application: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences =
        RxSharedPreferences.create(sharedPreferences)

}