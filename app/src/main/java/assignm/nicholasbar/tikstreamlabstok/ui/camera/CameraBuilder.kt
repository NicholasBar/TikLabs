package assignm.nicholasbar.tikstreamlabstok.ui.camera

import androidx.lifecycle.ViewModel
import assignm.nicholasbar.tikstreamlabstok.inject.ViewModelBuilder
import assignm.nicholasbar.tikstreamlabstok.inject.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class CameraBuilder {
    @ContributesAndroidInjector(
            modules = [
                ViewModelBuilder::class
            ]
    )
    internal abstract fun cameraActivity(): CameraActivity

    @Binds
    @IntoMap
    @ViewModelKey(CameraViewModel::class)
    abstract fun bindCameraViewModel(viewModel: CameraViewModel): ViewModel


}