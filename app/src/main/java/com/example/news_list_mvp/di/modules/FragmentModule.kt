package com.example.news_list_mvp.di.modules

import com.example.news_list_mvp.ui.News.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule{
    @ContributesAndroidInjector
    abstract fun contributeMainFragment() : NewsListFragment
}