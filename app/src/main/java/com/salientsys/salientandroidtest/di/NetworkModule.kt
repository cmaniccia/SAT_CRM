package com.salientsys.salientandroidtest.di

import com.salientsys.salientandroidtest.data.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NetworkModule {
    /** Provides annotation to single instance of OkHttpClient. */
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BaseClient

    /** Adds annotation to OkHttpClient Builder Params. */
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BuilderClient


    /** Provides ApiServer */
    @Singleton
    @Provides
    fun provideApiServer(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    /** Provides Singleton instance of Retrofit */
    @Singleton
    @Provides
    fun provideManagementServerRetrofit(
        builder: Retrofit.Builder,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        builder
            .addConverterFactory(gsonConverterFactory)
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()


    /** Provides Retrofit.Builder */
    @Provides
    fun provideRetrofitBuilder(
        @BuilderClient okHttpClient: OkHttpClient
    ): Retrofit.Builder = Retrofit.Builder().client(okHttpClient)

    /** Adds OkHttpClient Builder Params. */
    @Singleton
    @Provides
    @BuilderClient
    fun provideOkHttpClientBuilder(
        @BaseClient okHttpClient: OkHttpClient,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        okHttpClient.newBuilder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()

    /** Provides single instance of OkHttpClient. */
    @Singleton
    @Provides
    @BaseClient
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()
}
