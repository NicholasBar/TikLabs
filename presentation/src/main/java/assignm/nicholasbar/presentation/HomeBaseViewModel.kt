package assignm.nicholasbar.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import assignm.nicholasbar.domain.livestreamfails.GetVideosUseCase
import assignm.nicholasbar.domain.livestreamfails.GetMainListUseCase
import assignm.nicholasbar.domain.model.MainObject
import assignm.nicholasbar.domain.model.Order
import assignm.nicholasbar.domain.model.TimeFrame
import assignm.nicholasbar.presentation.state.Resource
import assignm.nicholasbar.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class HomeBaseViewModel @Inject constructor(
    private val getMainListUseCase: GetMainListUseCase?
) : ViewModel() {

    var page = 0
    var failParams: GetVideosUseCase.Params = GetVideosUseCase.Params(page,TimeFrame.ALL_TIME,
        Order.HOT,false,"","")
    var fetching = false
    var more = false

    private val liveDataFails : MutableLiveData<Resource<List<MainObject>>> = MutableLiveData()
    private val viewPagerNavigation : MutableLiveData<Int> = MutableLiveData()

    fun getMainList() : LiveData<Resource<List<MainObject>>> {
        return liveDataFails
    }

    fun getViewPagerNavigation() : LiveData<Int>{
        return viewPagerNavigation
    }

    fun navigateToPage(page: Int){
        viewPagerNavigation.value = page
    }

    fun firstFetchMainList() {
        more = false
        if(page == 0)fetchMainList()
    }

    fun fetchMoreMainList(){
        more = true
        fetchMainList()
    }

    fun fetchMainList() {
        if(fetching)return
        fetching = true
        page++
        failParams.page = page
        if(!more)liveDataFails.postValue(Resource(ResourceState.LOADING, null, null))
        getMainListUseCase?.execute(GetMainListSubscriber(),failParams)
    }

    override fun onCleared() {
        getMainListUseCase?.dispose()
        super.onCleared()
    }

    inner class GetMainListSubscriber: DisposableSingleObserver<List<MainObject>>() {
        override fun onSuccess(t: List<MainObject>) {
            fetching = false
            liveDataFails.postValue(Resource(ResourceState.SUCCESS,
                    t, null))
        }

        override fun onError(e: Throwable) {
            fetching = false
            liveDataFails.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }

    }
}