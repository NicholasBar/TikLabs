package assignm.nicholasbar.tikstreamlabstok.model

import android.os.Parcel
import android.os.Parcelable

data class MainView(val title: String,
                      val streamer: String?,
                      val game: String?,
                      val points: Int,
                      val nsfw: Boolean,
                      val thumbnailUrl: String,
                      val postId: Long,
                      val videoUrl: String): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(streamer)
        parcel.writeString(game)
        parcel.writeInt(points)
        parcel.writeByte(if (nsfw) 1 else 0)
        parcel.writeString(thumbnailUrl)
        parcel.writeLong(postId)
        parcel.writeString(videoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainView> {
        override fun createFromParcel(parcel: Parcel): MainView {
            return MainView(parcel)
        }

        override fun newArray(size: Int): Array<MainView?> {
            return arrayOfNulls(size)
        }
    }
}