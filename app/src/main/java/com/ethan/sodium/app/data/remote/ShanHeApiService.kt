package com.ethan.sodium.app.data.remote

import com.ethan.sodium.app.data.model.PhoneAttrModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ShanHeApiService {

    @GET(value = "api/za/phone.php")
   suspend fun fetchPhoneNumberAttribution(@Path(value = "phone") phone: String): PhoneAttrModel

}