package assignm.nicholasbar.tikstreamlabstok.ui.hire

import android.os.Bundle
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsActivity
import assignm.nicholasbar.tikstreamlabstok.util.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_hire.*

class HireActivity : TokLabsActivity() {

    val TAG = HireActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hire)
        Glide.with(this)
                .load(Constant.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(img_avatar)
    }


}