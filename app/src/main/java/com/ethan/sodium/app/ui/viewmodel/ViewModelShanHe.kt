package com.ethan.sodium.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ethan.sodium.app.data.repository.ShanHeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class ViewModelShanHe: ViewModel(), KoinComponent {

    val repository: ShanHeRepository by inject()

    suspend fun fetchPhoneNumberAttribution(phone: String) {
      val response=  repository.fetchPhoneNumberAttribution(phone)
    }



}