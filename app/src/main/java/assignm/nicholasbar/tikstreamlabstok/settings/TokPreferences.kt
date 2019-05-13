package assignm.nicholasbar.tikstreamlabstok.settings

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokPreferences @Inject constructor(
    val sharedPreferences: SharedPreferences
)