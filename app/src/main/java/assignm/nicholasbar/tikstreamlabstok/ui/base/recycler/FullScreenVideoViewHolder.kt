package assignm.nicholasbar.tikstreamlabstok.ui.base.recycler

import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import assignm.nicholasbar.domain.model.MainObject
import assignm.nicholasbar.tikstreamlabstok.R
import assignm.nicholasbar.tikstreamlabstok.model.MainView
import assignm.nicholasbar.tikstreamlabstok.ui.base.TokLabsApplication
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroUtil
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy


internal class FullScreenVideoViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    BaseViewHolder(inflater.inflate(R.layout.item_full_video, parent, false)),
    ToroPlayer {

    private val player = itemView.findViewById(R.id.player) as PlayerView
    private val thumbNail = itemView.findViewById(R.id.thumbnail) as ImageView
    private val imgHeart = itemView.findViewById(R.id.img_heart) as ImageView
    private val txtHeart = itemView.findViewById(R.id.txt_heart) as TextView
    private val txtTitle = itemView.findViewById(R.id.txt_title) as TextView
    private val txtStreamer = itemView.findViewById(R.id.txt_streamer) as TextView
    private var helper: ExoPlayerViewHelper? = null
    private var videoUri: Uri? = null

    var listener: ToroPlayer.EventListener? = null

    override fun bind(item: Any?) {
        super.bind(item)
        thumbNail.visibility = VISIBLE
        player.visibility = INVISIBLE
        val videoUrl = (item as MainView).videoUrl
        videoUri = Uri.parse(videoUrl)

        Glide.with(thumbNail)
                .load(item.thumbnailUrl)
                .dontAnimate()
                .dontTransform()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .apply(RequestOptions().fitCenter().priority(Priority.HIGH))
                .into(thumbNail)

        txtHeart.text = item.points.toString()

        if(TextUtils.isEmpty(item.streamer)){
            txtStreamer.text = "@Unkown Streamer"
        }else{
            val streamer = "@" + item.streamer
            txtStreamer.text = streamer
        }

        if(TextUtils.isEmpty(item.title)){
            txtTitle.text = "No Title"
        }else{
            txtTitle.text = item.title
        }
    }

    override fun getPlayerView() = player

    override fun getCurrentPlaybackInfo() = helper?.latestPlaybackInfo ?: PlaybackInfo()

    override fun initialize(container: Container, playbackInfo: PlaybackInfo) {
        if (helper == null) helper = ExoPlayerViewHelper(this, videoUri!!, null, TokLabsApplication.config!!)
        if (listener == null) {
            listener = object : ToroPlayer.EventListener {
                override fun onFirstFrameRendered() {
                }

                override fun onBuffering() {
                }

                override fun onPlaying() {
                    thumbNail.visibility = GONE
                    player.visibility = VISIBLE
                }

                override fun onPaused() {
                }

                override fun onCompleted() {
                }

            }
            helper!!.addPlayerEventListener(listener!!)
        }
        helper!!.initialize(container, playbackInfo)
    }

    override fun play() {
        helper!!.play()
    }

    override fun pause() {
        helper!!.pause()
    }

    override fun isPlaying() = helper?.isPlaying ?: false

    override fun release() {
        if (listener != null) {
            helper?.removePlayerEventListener(listener)
            listener = null
        }
        helper?.release()
        helper = null
    }

    override fun wantsToPlay() = ToroUtil.visibleAreaOffset(this, itemView.parent) >= 0.65

    override fun getPlayerOrder() = adapterPosition

}