package com.salientsys.salientandroidtest.main.post

import androidx.lifecycle.ViewModel
import com.salientsys.salientandroidtest.data.AppRepository
import javax.inject.Inject

class PostViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    fun getPosts(userId : Int) = appRepository.observeUsersPosts(userId)

}
