package assignm.nicholasbar.tikstreamlabstok.ui.home.streamerdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import assignm.nicholasbar.domain.model.MainObject
import assignm.nicholasbar.tikstreamlabstok.model.MainView
import assignm.nicholasbar.tikstreamlabstok.ui.base.recycler.BaseViewHolder
import assignm.nicholasbar.tikstreamlabstok.ui.base.recycler.ThumbNailViewHolder
import java.util.ArrayList

class DetailListAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var elements = ArrayList<MainView>()

    fun updateElements(newElements: List<MainView>?) {
        val oldLength = this.elements.size
        this.elements.clear()
        if(newElements != null)this.elements.addAll(newElements)
        super.notifyItemRangeRemoved(0, oldLength)
        super.notifyItemRangeInserted(0, this.elements.size)
    }

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (inflater === null || inflater!!.context !== parent.context) {
            inflater = LayoutInflater.from(parent.context)
        }
        return ThumbNailViewHolder(inflater!!, parent)
    }

    override fun getItemCount() = this.elements.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(elements[position])
    }




}