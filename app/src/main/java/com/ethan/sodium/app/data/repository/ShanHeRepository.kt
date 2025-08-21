package com.ethan.sodium.app.data.repository

import com.ethan.sodium.app.data.remote.ShanHeApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShanHeRepository : KoinComponent{

    val api : ShanHeApiService by inject()

    suspend fun fetchPhoneNumberAttribution(phone: String) = api.fetchPhoneNumberAttribution(phone)
}