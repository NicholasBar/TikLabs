package assignm.nicholasbar.tikstreamlabstok.ui.home.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import assignm.nicholasbar.tikstreamlabstok.ui.home.mainlist.MainListFragment
import assignm.nicholasbar.tikstreamlabstok.ui.home.streamerdetail.StreamerDetailFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MainListFragment()
            1 -> StreamerDetailFragment()
            else -> MainListFragment()
        }
    }
}