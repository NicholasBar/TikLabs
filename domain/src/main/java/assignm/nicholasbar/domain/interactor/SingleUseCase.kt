package assignm.nicholasbar.domain.interactor

import assignm.nicholasbar.domain.executor.PostExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T,in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
        val single = this.buildUseCaseSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(observer))
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun clear() {
        disposables.clear()
    }

}