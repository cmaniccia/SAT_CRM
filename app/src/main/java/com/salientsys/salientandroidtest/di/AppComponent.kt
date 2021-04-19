package com.salientsys.salientandroidtest.di

import android.content.Context
import com.salientsys.salientandroidtest.di.viewmodel.ViewModelModule
import com.salientsys.salientandroidtest.main.MainActivity
import com.salientsys.salientandroidtest.main.post.PostDetailsFragment
import com.salientsys.salientandroidtest.main.post.PostFragment
import com.salientsys.salientandroidtest.main.user.UserFragment
import com.salientsys.salientandroidtest.main.userdetails.UserDetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ApiModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: UserFragment)
    fun inject(fragment: UserDetailsFragment)
    fun inject(fragment: PostFragment)
    fun inject(fragment: PostDetailsFragment)
}

