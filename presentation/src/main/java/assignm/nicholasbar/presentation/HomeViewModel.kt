package assignm.nicholasbar.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    /*private val confirmCodeUseCase: ConfirmCodeUseCase?,
    private val confirmEmailUseCase: ConfirmCodeUseCase?,
    private val resendCodeUseCase: ResendCodeUseCase?,
    private val mapperConfirmCodeResponse: ConfirmCodeResponseViewMapper,
    private val mapperConfirmCodeData: ConfirmCodeViewMapper*/
) : ViewModel() {

    /*private var confirmCode: ConfirmCodeView = ConfirmCodeView()
    private val liveDataConfirmCodeResponse: MutableLiveData<Resource<ConfirmCodeResponseView>> = MutableLiveData()

    fun getConfirmCodeResponse(): LiveData<Resource<ConfirmCodeResponseView>> {
        return liveDataConfirmCodeResponse
    }

    fun processConfirmCode() {
        liveDataConfirmCodeResponse.postValue(Resource(ResourceState.LOADING, null, null))
        confirmCodeUseCase?.execute(ConfirmCodeSubscriber(), ConfirmCodeUseCase.Params.forCode(mapperConfirmCodeData.mapToEntity(confirmCode)))
    }

    inner class ConfirmCodeSubscriber: DisposableObserver<ConfirmCodeResponseObject>() {
        override fun onNext(t: ConfirmCodeResponseObject) {
            liveDataConfirmCodeResponse.postValue(Resource(ResourceState.SUCCESS,
                    mapperConfirmCodeResponse.mapToView(t), null))
        }

        override fun onError(e: Throwable) {
            liveDataConfirmCodeResponse.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }

        override fun onComplete() { }

    }*/

}