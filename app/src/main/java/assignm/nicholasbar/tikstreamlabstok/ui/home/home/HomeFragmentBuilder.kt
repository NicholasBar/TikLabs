package assignm.nicholasbar.tikstreamlabstok.ui.home.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class HomeFragmentBuilder {
    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment
}