package assignm.nicholasbar.tikstreamlabstok.ui.home.mainlist

import android.graphics.Typeface
import android.os.Bundle
import android.os.Parcelable
import android.util.TypedValue
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import assignm.nicholasbar.domain.model.MainObject
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.presentation.state.Resource
import assignm.nicholasbar.presentation.state.ResourceState
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsFragment
import assignm.nicholasbar.presentation.HomeBaseViewModel
import assignm.nicholasbar.tikstreamlabstok.mapper.MainViewMapper
import assignm.nicholasbar.tikstreamlabstok.model.MainView
import assignm.nicholasbar.tikstreamlabstok.ui.base.recycler.PrimitivePagedLoader
import im.ene.toro.CacheManager
import kotlinx.android.synthetic.main.fragment_main_list.*
import javax.inject.Inject


class MainListFragment : TokLabsFragment() {

    val TAG = MainListFragment::class.java.simpleName

    @Inject
    lateinit var mapper: MainViewMapper
    private lateinit var viewModel: HomeBaseViewModel
    private lateinit var adapter: MainListAdapter
    private var loaded = false
    private var superLikeCounter = 1

    private var listState: Parcelable? = null

    companion object {
        private const val RECYCLER_STATE = "recycler_state"
        private const val RECYCLER_CONTENTS = "recycler_contents"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nav_bottom.setupWithNavController(findNavController())
        filter_hot.setOnClickListener { filterButtonClicked(Order.HOT) }
        filter_top.setOnClickListener { filterButtonClicked(Order.TOP) }
        filter_new.setOnClickListener { filterButtonClicked(Order.NEW) }
        filter_random.setOnClickListener { filterButtonClicked(Order.RANDOM) }
        fab_like.setOnClickListener { fabLikeClicked() }
    }

    private fun setRippleCenter() {
        val centerX = fab_like.x + fab_like.width / 2
        val centerY = fab_like.y + fab_like.height / 2
        ripples.setCenter(centerX,centerY)
    }

    private fun fabLikeClicked() {
        fab_like.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        setRippleCenter()
        ripples.addRipple()
        ripples.addNumber(superLikeCounter)
        superLikeCounter++
        txt_likes.text = superLikeCounter.toString()
    }

    private fun filterButtonClicked(order: Order) {
        when (order) {
            Order.HOT -> {
                filter_hot.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                filter_hot.setTypeface(null, Typeface.BOLD)
                if (context != null) filter_hot.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            }
            Order.TOP -> {
                filter_top.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                filter_top.setTypeface(null, Typeface.BOLD)
                if (context != null) filter_top.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            }
            Order.NEW -> {
                filter_new.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                filter_new.setTypeface(null, Typeface.BOLD)
                if (context != null) filter_new.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            }
            Order.RANDOM -> {
                filter_random.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                filter_random.setTypeface(null, Typeface.BOLD)
                if (context != null) filter_random.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            }
        }
        resetPreviousFilter()
        viewModel.failParams.order = order
        viewModel.more = false
        viewModel.fetchMainList()
    }

    private fun resetPreviousFilter() {
        when (viewModel.failParams.order) {
            Order.HOT -> {
                filter_hot.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                filter_hot.setTypeface(null, Typeface.NORMAL)
                if (context != null) filter_hot.setTextColor(ContextCompat.getColor(context!!, R.color.silver))
            }
            Order.TOP -> {
                filter_top.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                filter_top.setTypeface(null, Typeface.NORMAL)
                if (context != null) filter_top.setTextColor(ContextCompat.getColor(context!!, R.color.silver))
            }
            Order.NEW -> {
                filter_new.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                filter_new.setTypeface(null, Typeface.NORMAL)
                if (context != null) filter_new.setTextColor(ContextCompat.getColor(context!!, R.color.silver))
            }
            Order.RANDOM -> {
                filter_random.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                filter_random.setTypeface(null, Typeface.NORMAL)
                if (context != null) filter_random.setTextColor(ContextCompat.getColor(context!!, R.color.silver))
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)
                .get(HomeBaseViewModel::class.java)

        initRecyclerView()

        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(RECYCLER_STATE)
            savedInstanceState.getParcelableArrayList<MainView>(RECYCLER_CONTENTS)?.let {
                adapter.updateElements(it.toList(),false)
            }
        }
    }

    private fun initRecyclerView() {
        adapter = MainListAdapter(context)
        recycler_main.setHasFixedSize(true)
        recycler_main.apply {
            adapter = this@MainListFragment.adapter
            layoutManager = LinearLayoutManager(context)
            cacheManager = CacheManager.DEFAULT
        }

        // We want to have a viewpager effect on our recyclerview to snap videos the center
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recycler_main)
        // Allow swipe to refresh behavior
        swipe_refresh.setOnRefreshListener{
            viewModel.more = false
            viewModel.fetchMainList()
        }
        // Pull more data when we are close to end of list
        recycler_main.addOnScrollListener(PrimitivePagedLoader {
            viewModel.fetchMoreMainList()
        })

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
        //Do initial pull
        viewModel.firstFetchMainList()
    }

    private fun handleFetchMainListState(resource: Resource<List<MainObject>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                loaded = true
                swipe_refresh.isRefreshing = false
                lottie_load.visibility = GONE
                recycler_main.visibility = VISIBLE
                adapter.updateElements(resource.data?.map { mapper.mapToView(it) },viewModel.more)
            }
            ResourceState.LOADING -> {
                loaded = false
                lottie_load.visibility = VISIBLE
                recycler_main.visibility = GONE
            }
            ResourceState.ERROR -> {
                loaded = true
                swipe_refresh.isRefreshing = false
                lottie_load.visibility = GONE
                recycler_main.visibility = GONE
                Toast.makeText(context, "Failed to fetch videos.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(RECYCLER_STATE, recycler_main.layoutManager?.onSaveInstanceState())
        outState.putParcelableArrayList(RECYCLER_CONTENTS, ArrayList(adapter.getElements()))
    }

    override fun onPause() {
        ripples.pause()
        super.onPause()
    }

    override fun onResume() {
        ripples.resume()
        super.onResume()
    }
}