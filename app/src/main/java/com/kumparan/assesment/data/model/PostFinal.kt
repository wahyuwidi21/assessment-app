package com.kumparan.assesment.data.model

import android.os.Parcel
import android.os.Parcelable

data class PostFinal(
    val postId: Int,
    val userName: String,
    val userId: Int,
    val companyName: String,
    val title: String,
    val body: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(postId)
        parcel.writeString(userName)
        parcel.writeInt(userId)
        parcel.writeString(companyName)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostFinal> {
        override fun createFromParcel(parcel: Parcel): PostFinal {
            return PostFinal(parcel)
        }

        override fun newArray(size: Int): Array<PostFinal?> {
            return arrayOfNulls(size)
        }
    }
}