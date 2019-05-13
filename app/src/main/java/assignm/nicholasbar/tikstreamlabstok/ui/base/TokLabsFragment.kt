package assignm.nicholasbar.tikstreamlabstok.ui.base

import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Base fragment class which supports LifecycleOwner and Dagger injection.
 */
abstract class TokLabsFragment : DaggerFragment() {
    private var postponedTransition = false
    private var checkCurrentDestination = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory



    override fun postponeEnterTransition() {
        super.postponeEnterTransition()
        postponedTransition = true
    }

    override fun onStart() {
        super.onStart()

        if (checkCurrentDestination && findNavController().currentDestination == null) {
            findNavController().navigate(findNavController().graph.startDestination)
        }

        checkCurrentDestination = false

        if (postponedTransition) {
            // If we're postponedTransition and haven't started a transition yet, we'll delay for a max of 5 seconds
            view?.postDelayed(::scheduleStartPostponedTransitions, 5000)
        }
    }

    override fun onStop() {
        super.onStop()
        checkCurrentDestination = true
    }

    override fun startPostponedEnterTransition() {
        postponedTransition = false
        super.startPostponedEnterTransition()
    }

    protected fun scheduleStartPostponedTransitions() {
        if (postponedTransition) {
            view?.doOnNextLayout {
                (it.parent as ViewGroup).doOnPreDraw {
                    startPostponedEnterTransition()
                }
            }
        }

        val activity = activity
        if (activity is TokLabsActivity) {
            activity.scheduleStartPostponedTransitions()
        }
    }
}