package com.example.news_list_mvp.di.component

import com.example.news_list_mvp.NewsApplication
import com.example.news_list_mvp.di.modules.ActivityModule
import com.example.news_list_mvp.di.modules.AppModule
import com.example.news_list_mvp.di.modules.FragmentModule
import com.example.news_list_mvp.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AppModule::class,
        NetworkModule::class
    )
)
interface ApplicationComponent {
    fun inject(app: NewsApplication)
}