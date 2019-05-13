package assignm.nicholasbar.tikstreamlabstok.ui.home.streamerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import assignm.nicholasbar.domain.model.MainObject
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.presentation.state.Resource
import assignm.nicholasbar.presentation.state.ResourceState
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsFragment
import assignm.nicholasbar.presentation.HomeBaseViewModel
import assignm.nicholasbar.tikstreamlabstok.mapper.MainViewMapper
import assignm.nicholasbar.tikstreamlabstok.util.Constant
import assignm.nicholasbar.tikstreamlabstok.util.Constant.PAGE_HOME
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import im.ene.toro.CacheManager
import kotlinx.android.synthetic.main.fragment_streamer_detail.*
import javax.inject.Inject

class StreamerDetailFragment: TokLabsFragment() {

    val TAG = StreamerDetailFragment::class.java.simpleName

    private lateinit var viewModel: HomeBaseViewModel
    @Inject
    lateinit var mapper: MainViewMapper
    private val adapter = DetailListAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_streamer_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(context != null){
            Glide.with(context!!)
                    .load(Constant.avatar)
                    .apply(RequestOptions.circleCropTransform())
                    .into(img_avatar)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)
                .get(HomeBaseViewModel::class.java)
        initRecyclerView()

        img_back.setOnClickListener{btnBackClicked()}
    }

    private fun btnBackClicked() {
        viewModel.navigateToPage(PAGE_HOME)
    }

    private fun initRecyclerView() {
        recycler_detail.isNestedScrollingEnabled = false
        recycler_detail.apply {
            adapter = this@StreamerDetailFragment.adapter
            layoutManager = GridLayoutManager(context,3)
            cacheManager = CacheManager.DEFAULT
        }
    }

    override fun onStart() {
        super.onStart()
        //Observe responses
        viewModel.getMainList().observe(this,
                Observer<Resource<List<MainObject>>> {
                    it?.let {
                        handleFetchMainListState(it)
                    }
                })
    }

    private fun handleFetchMainListState(resource: Resource<List<MainObject>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                lottie_load.visibility = View.GONE
                recycler_detail.visibility = View.VISIBLE
                adapter.updateElements(resource.data?.map { mapper.mapToView(it) })
            }
            ResourceState.LOADING -> {
                lottie_load.visibility = View.VISIBLE
                recycler_detail.visibility = View.GONE
            }
            ResourceState.ERROR -> {
                lottie_load.visibility = View.GONE
                recycler_detail.visibility = View.GONE
                Toast.makeText(context,"Failed to fetch videos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}