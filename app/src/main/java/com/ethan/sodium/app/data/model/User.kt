package com.ethan.sodium.app.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonQualifier
import kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
@Parcelize
data class User(
    val id: String,
    val name: String
) : Parcelable