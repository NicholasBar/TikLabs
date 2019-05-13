package assignm.nicholasbar.tikstreamlabstok.ui.home.mainlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import assignm.nicholasbar.tikstreamlabstok.model.MainView
import assignm.nicholasbar.tikstreamlabstok.ui.base.recycler.BaseViewHolder
import assignm.nicholasbar.tikstreamlabstok.ui.base.recycler.FullScreenVideoViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.util.ArrayList

class MainListAdapter(private val context: Context?) : RecyclerView.Adapter<BaseViewHolder>() {

    private var elements = ArrayList<MainView>()

    fun updateElements(newElements: List<MainView>?, more: Boolean) {
        val oldLength = this.elements.size
        if(!more)this.elements.clear()
        if(newElements != null)this.elements.addAll(newElements)
        super.notifyItemRangeRemoved(0, oldLength)
        super.notifyItemRangeInserted(0, this.elements.size)
    }

    fun getElements(): ArrayList<MainView> {
        return elements
    }

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (inflater === null || inflater!!.context !== parent.context) {
            inflater = LayoutInflater.from(parent.context)
        }
        return FullScreenVideoViewHolder(inflater!!, parent)
    }

    override fun getItemCount() = this.elements.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(elements[position])
        preFetchNextThumbNail(position + 1)
    }

    /**
     * Prefetch next thumbnail in list if exists which should suffice do to single snap behavior
     */
    private fun preFetchNextThumbNail(index: Int) {
        if (context == null) return
        try {
            Glide.with(context)
                    .load(elements[index].thumbnailUrl)
                    .dontTransform()
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .apply(RequestOptions().priority(Priority.LOW))
                    .preload()

        } catch (e: IndexOutOfBoundsException) {
            // Element doesn't exist preload nothing
        }

    }


}