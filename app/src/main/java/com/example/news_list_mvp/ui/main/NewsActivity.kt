package com.example.news_list_mvp.ui.main

import android.os.Bundle
import com.example.news_list_mvp.R
import com.example.news_list_mvp.ui.News.NewsListFragment
import com.example.news_list_mvp.ui.common.BaseActivity

class NewsActivity : BaseActivity() {

    override fun containerResId(): Int {
        return R.id.activity_container
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pushFragment(NewsListFragment(), false)
    }
}
