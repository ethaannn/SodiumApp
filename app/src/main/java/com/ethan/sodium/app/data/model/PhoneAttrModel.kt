package com.ethan.sodium.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonQualifier

    @JsonClass(generateAdapter = true)
    data class PhoneAttrModel(
        @Json(name = "code")
        var code: String? = "",
        @Json(name = "duan")
        var duan: String? = "",
        @Json(name = "ji_xiong")
        var jiXiong: String? = "",
        @Json(name = "ji_xiong_explain")
        var jiXiongExplain: String? = "",
        @Json(name = "local")
        var local: String? = "",
        @Json(name = "sim")
        var sim: String? = "",
        @Json(name = "tel",)
        var tel: String? = "",
        @Json(name = "type")
        var type: String? = "",
        @Json(name = "yys")
        var yys: String? = ""
    )


