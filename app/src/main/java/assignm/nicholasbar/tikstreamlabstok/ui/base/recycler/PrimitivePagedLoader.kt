package assignm.nicholasbar.tikstreamlabstok.ui.base.recycler

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PrimitivePagedLoader(private val listener: () -> Unit) : RecyclerView.OnScrollListener() {
    @Synchronized
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (recyclerView.layoutManager is LinearLayoutManager) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition()

            if ((visibleItemCount + firstVisibleItemPos) >= totalItemCount - MARGIN
                    && firstVisibleItemPos >= 0
                    && totalItemCount >= 20) {
                listener()
            }
        }
    }

    companion object {
        private const val MARGIN = 4
    }
}