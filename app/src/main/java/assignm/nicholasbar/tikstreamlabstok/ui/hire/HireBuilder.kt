package assignm.nicholasbar.tikstreamlabstok.ui.hire

import assignm.nicholasbar.tikstreamlabstok.inject.ViewModelBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class HireBuilder {
    @ContributesAndroidInjector(
            modules = [
                ViewModelBuilder::class
            ]
    )
    internal abstract fun hireActivity(): HireActivity

}