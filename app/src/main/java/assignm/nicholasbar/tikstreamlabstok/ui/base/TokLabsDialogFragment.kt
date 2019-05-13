package assignm.nicholasbar.tikstreamlabstok.ui.base


import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject


/**
 * Base dialog fragment class which supports LifecycleOwner and Dagger injection.
 */
abstract class TokLabsDialogFragment : DaggerDialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

}