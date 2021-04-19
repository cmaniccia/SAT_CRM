package com.salientsys.salientandroidtest.main.post

import androidx.lifecycle.ViewModel
import com.salientsys.salientandroidtest.data.AppRepository
import javax.inject.Inject

class PostDetailsViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    fun getPostById(id : Int) = appRepository.observePost(id)
    fun deletePostById(id : Int) = appRepository.observePostDelete(id)
}
