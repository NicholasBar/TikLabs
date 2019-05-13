package assignm.nicholasbar.tikstreamlabstok.ui.home.streamerdetail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class StreamerDetailFragmentBuilder {
    @ContributesAndroidInjector
    internal abstract fun streamerDetailFragment(): StreamerDetailFragment
}