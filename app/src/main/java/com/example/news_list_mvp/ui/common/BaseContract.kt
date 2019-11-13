package com.example.news_list_mvp.ui.common

class BaseContract{
    interface Presenter<in T>{
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }
    interface View{

    }
}