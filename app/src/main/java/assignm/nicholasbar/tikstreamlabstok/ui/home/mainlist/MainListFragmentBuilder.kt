package assignm.nicholasbar.tikstreamlabstok.ui.home.mainlist

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainListFragmentBuilder {
    @ContributesAndroidInjector
    internal abstract fun mainListFragment(): MainListFragment
}