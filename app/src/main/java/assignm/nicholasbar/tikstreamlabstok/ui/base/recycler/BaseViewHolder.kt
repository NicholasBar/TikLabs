package assignm.nicholasbar.tikstreamlabstok.ui.base.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(item: Any?) {}
}