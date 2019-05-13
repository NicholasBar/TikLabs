package assignm.nicholasbar.domain.executor

import io.reactivex.Scheduler

/**
 * Abstraction interface so domain layer does not need knowledge of Android Framework.
 * In this case will just use RxJava and not RxAndroid
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}
