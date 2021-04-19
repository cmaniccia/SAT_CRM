package com.salientsys.salientandroidtest.data.network

import com.salientsys.salientandroidtest.data.entity.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    /** /users endpoint functions */
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): Response<User>

    // TODO: Return List of User's Posts
    @GET("users/{userId}/posts")
    suspend fun getPostsByUserId(@Path("userId") userId: Int): Response<List<Post>>

    @GET("posts/{postId}")
    suspend fun getPostById(@Path("postId") postId: Int): Response<Post>

    @DELETE("posts/{postId}")
    suspend fun deletePostById(@Path("postId") postId: Int): Response<Post>

    @POST("users")
    suspend fun addUser(@Body user: User)

    /** /todos endpoint functions */
    @GET("todos")
    suspend fun getTodos(): Response<Todo>

    /** /photos endpoint functions */
    @GET("photos")
    suspend fun getPhotos(): Response<Photo>

    /** /albums endpoint functions */
    @GET("albums")
    suspend fun getAlbums(): Response<Album>

    /** /comments endpoint functions */
    @GET("comments")
    suspend fun getComments(): Response<Comment>

    /** /posts endpoint functions */
    @GET("posts")
    suspend fun getPosts(): Response<Post>
}