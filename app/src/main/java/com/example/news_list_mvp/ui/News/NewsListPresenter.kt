package com.example.news_list_mvp.ui.News

import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list_mvp.data.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListPresenter @Inject constructor(
    newsRepository: NewsRepository) : NewsContract.Presenter{

    var newsRepository: NewsRepository;
    init {
        this.newsRepository = newsRepository
    }
    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: NewsContract.View

    override fun loadData(pageNum: Int) {
        var subscribe = newsRepository.getNews(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<ArticlesItem>? ->
                view.loadDataSuccess(list!!)
                view.showProgress(false)
            }, {
                error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })
        compositeDisposable.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    override fun attach(view: NewsContract.View) {
        this.view = view
    }


}
