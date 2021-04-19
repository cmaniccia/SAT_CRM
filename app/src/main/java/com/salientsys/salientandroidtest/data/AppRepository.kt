package com.salientsys.salientandroidtest.data

import com.salientsys.salientandroidtest.data.dao.PostDao
import com.salientsys.salientandroidtest.data.dao.UserDao
import com.salientsys.salientandroidtest.data.entity.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val userDao: UserDao,
    private val postDao: PostDao,
    private val dataSource: AppDataSource
) {

    fun observeUsers() = liveDataResult(
        databaseQuery = { userDao.getUsers() },
        networkCall = { dataSource.fetchUsers() },
        saveCallResult = { userDao.insertAll(it) }
    )

    fun observeUser(userId: Int) = liveDataResult(
        databaseQuery = { userDao.getUserById(userId) },
        networkCall = { dataSource.fetchUser(userId) },
        saveCallResult = { userDao.insert(it) }
    )

    fun observePost(postId: Int) = liveDataResult(
        databaseQuery = { postDao.getPostById(postId) },
        networkCall = { dataSource.fetchPost(postId) },
        saveCallResult = { postDao.insert(it) }
    )

    fun observePostDelete(id: Int) = liveDataResult(
        databaseQuery = { postDao.getPosts() },
        networkCall = { dataSource.deletePost(id) },
        saveCallResult = { /*postDao.insert(it)*/ }
    )

    fun observeUsersPosts(userId: Int) = liveDataResult(
        databaseQuery = { postDao.getPosts() },
        networkCall = { dataSource.fetchUsersPosts(userId) },
        saveCallResult = { postDao.insertAll(it) }
    )
    // TODO: Set LiveData for User's Posts


}
