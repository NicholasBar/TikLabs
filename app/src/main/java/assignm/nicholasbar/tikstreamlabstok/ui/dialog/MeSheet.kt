package assignm.nicholasbar.tikstreamlabstok.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.tikstreamlabstok.util.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_me.*

class MeSheet : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_bottom_sheet_me, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(context != null){
            Glide.with(context!!)
                    .load(Constant.avatar)
                    .apply(RequestOptions.circleCropTransform())
                    .into(img_avatar)
        }

        btn_hire.setOnClickListener { findNavController().navigate(R.id.nav_me_to_hire) }
        super.onViewCreated(view, savedInstanceState)
    }
}