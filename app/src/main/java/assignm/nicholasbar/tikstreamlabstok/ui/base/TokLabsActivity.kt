package assignm.nicholasbar.tikstreamlabstok.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModelProvider
import assignm.nicholasbar.tikstreamlabstok.settings.TokPreferences
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Base Activity class which supports LifecycleOwner and Dagger injection.
 */
abstract class TokLabsActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var prefs: TokPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var postponedTransition = false

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun postponeEnterTransition() {
        super.postponeEnterTransition()
        postponedTransition = true
    }

    override fun startPostponedEnterTransition() {
        postponedTransition = false
        super.startPostponedEnterTransition()
    }

    fun scheduleStartPostponedTransitions() {
        if (postponedTransition) {
            window.decorView.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    open fun handleIntent(intent: Intent) {}

    override fun finishAfterTransition() {
        val resultData = Intent()
        val result = onPopulateResultIntent(resultData)
        setResult(result, resultData)

        super.finishAfterTransition()
    }

    open fun onPopulateResultIntent(intent: Intent): Int {
        return Activity.RESULT_OK
    }

}