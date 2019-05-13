package assignm.nicholasbar.tikstreamlabstok.ui.base.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import assignm.nicholasbar.domain.model.MainObject
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.tikstreamlabstok.model.MainView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions



internal class ThumbNailViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder(inflater.inflate(R.layout.item_thumbnail, parent, false)){

    private val thumbNail = itemView.findViewById(R.id.img_thumb) as ImageView
    private val txtHeart = itemView.findViewById(R.id.txt_heart) as TextView

    override fun bind(item: Any?) {
        super.bind(item)
        val itemVal = (item as MainView)

        Glide.with(thumbNail)
                .load(itemVal.thumbnailUrl)
                .dontAnimate()
                .apply(RequestOptions().priority(Priority.HIGH))
                .into(thumbNail)

        txtHeart.text = item.points.toString()
    }


}