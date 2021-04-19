package com.salientsys.salientandroidtest.main.user

import androidx.lifecycle.ViewModel
import com.salientsys.salientandroidtest.data.AppRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    fun getUsers() = appRepository.observeUsers()
}
