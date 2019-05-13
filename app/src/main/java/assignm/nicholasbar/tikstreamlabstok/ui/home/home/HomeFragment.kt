package assignm.nicholasbar.tikstreamlabstok.ui.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsFragment
import assignm.nicholasbar.presentation.HomeBaseViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : TokLabsFragment() {

    val TAG = HomeFragment::class.java.simpleName

    private lateinit var viewModel: HomeBaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        main_pager.adapter = MainPagerAdapter(childFragmentManager)
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(HomeBaseViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        //Observe viewpager navigation requests
        viewModel.getViewPagerNavigation().observe(this,
                Observer<Int> {
                    it?.let {
                        handleViewPagerNavigationRequest(it)
                    }
                })
    }

    private fun handleViewPagerNavigationRequest(page: Int) {
        main_pager.currentItem = page
    }
}