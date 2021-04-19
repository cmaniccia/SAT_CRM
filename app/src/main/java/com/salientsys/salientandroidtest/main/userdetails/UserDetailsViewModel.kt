package com.salientsys.salientandroidtest.main.userdetails

import androidx.lifecycle.ViewModel
import com.salientsys.salientandroidtest.data.AppRepository
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val appRepository: AppRepository) :
    ViewModel() {
    fun getUserById(userId: Int) = appRepository.observeUser(userId)
}
