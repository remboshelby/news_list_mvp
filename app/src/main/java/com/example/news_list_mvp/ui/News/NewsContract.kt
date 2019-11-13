package com.example.news_list_mvp.ui.News

import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list_mvp.ui.common.BaseContract

class NewsContract {
    interface View: BaseContract.View{
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<ArticlesItem>)
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun loadData(pageNum : Int)
    }
}