package com.example.news_list_mvp.di.modules

import com.example.news_list_mvp.ui.main.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeNewsActivity() : NewsActivity

}