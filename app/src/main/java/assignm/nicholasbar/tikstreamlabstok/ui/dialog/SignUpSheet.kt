package assignm.nicholasbar.tikstreamlabstok.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import assignm.nicholasbar.tikstreamlabstok.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SignUpSheet : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_bottom_sheet_signup, container, false)
}