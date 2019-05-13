package assignm.nicholasbar.tikstreamlabstok.ui.home

import androidx.lifecycle.ViewModel
import assignm.nicholasbar.presentation.HomeBaseViewModel
import assignm.nicholasbar.tikstreamlabstok.inject.ViewModelBuilder
import assignm.nicholasbar.tikstreamlabstok.inject.ViewModelKey
import assignm.nicholasbar.tikstreamlabstok.ui.home.home.HomeFragmentBuilder
import assignm.nicholasbar.presentation.HomeViewModel
import assignm.nicholasbar.tikstreamlabstok.ui.home.mainlist.MainListFragmentBuilder
import assignm.nicholasbar.tikstreamlabstok.ui.home.streamerdetail.StreamerDetailFragmentBuilder
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class HomeBuilder {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class,
            HomeFragmentBuilder::class,
            MainListFragmentBuilder::class,
            StreamerDetailFragmentBuilder::class
        ]
    )
    internal abstract fun homeActivity(): HomeActivity

    @Binds
    @IntoMap
    @ViewModelKey(HomeBaseViewModel::class)
    abstract fun bindHomeBaseViewModel(viewModel: HomeBaseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

}