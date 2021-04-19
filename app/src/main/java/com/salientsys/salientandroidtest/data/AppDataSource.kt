package com.salientsys.salientandroidtest.data

import com.salientsys.salientandroidtest.data.network.ApiService
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * UserDataSource provides OkHttp-Room suspend logic to ViewModels to allow
 * network calls to be processed seamlessly and provide {Result} status returns.
 */
@Singleton
class AppDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchUsers() = getResult { apiService.getUsers() }
    suspend fun fetchUser(id: Int) = getResult { apiService.getUserById(id) }
    // TODO: Fetch User's Posts by userId
    suspend fun fetchUsersPosts(userId: Int) = getResult { apiService.getPostsByUserId(userId) }
    suspend fun fetchPost(id: Int) = getResult { apiService.getPostById(id) }
    suspend fun deletePost(id: Int) = getResult { apiService.deletePostById(id) }

    private suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        return Result.error("Network call has failed for a following reason: $message")
    }
}
