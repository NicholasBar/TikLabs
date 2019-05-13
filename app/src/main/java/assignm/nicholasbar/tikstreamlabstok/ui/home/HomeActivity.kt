package assignm.nicholasbar.tikstreamlabstok.ui.home

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsActivity
import android.view.LayoutInflater
import android.content.Context
import android.os.Handler
import assignm.nicholasbar.presentation.HomeBaseViewModel


class HomeActivity : TokLabsActivity() {

    val TAG = HomeActivity::class.java.simpleName

    private lateinit var viewModel: HomeBaseViewModel
    private var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HomeBaseViewModel::class.java)

    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment)
                        .currentDestination?.id == R.id.home_dest) {
            if(doubleBackPressed){
                // exit
                super.onBackPressed()
            }else{
                doubleBackPressed = true
                showExitWarning()
            }
            return
        }else{
            super.onBackPressed()
        }
    }

    private fun showExitWarning() {
        val toast = Toast(this)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER, 0, 0)

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.toast_min_black, null)
        toast.view = view
        toast.show()

        // Set time window for user to exit
        Handler().postDelayed({ doubleBackPressed = false }, 2000)
    }

}