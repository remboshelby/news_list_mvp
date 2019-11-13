package com.example.news_list_mvp

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.news_list.utils.Constants
import com.example.news_list_mvp.di.component.DaggerApplicationComponent
import com.example.news_list_mvp.di.modules.AppModule
import com.example.news_list_mvp.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class NewsApplication: Application(), HasActivityInjector, HasSupportFragmentInjector{

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(Constants.URL))
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}