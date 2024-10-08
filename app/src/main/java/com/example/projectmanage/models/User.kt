package com.example.projectmanage.models

import android.os.Parcel
import android.os.Parcelable

data class User (

    var id :String = "",
    val name :String = "",
    val email :String = "",
    val image :String = "",
    val mobile :Long = 0,
    val fcmToken :String = "",

):Parcelable {


    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int)=with(dest) {
       writeString(id)
        writeString(name)
        writeString(email)
        writeLong(mobile)
        writeString(fcmToken)

    }
}