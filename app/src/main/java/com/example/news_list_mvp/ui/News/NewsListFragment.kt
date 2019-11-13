package com.example.news_list_mvp.ui.News

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.ui.news_detail_watcher.NewsDetailFragment
import com.example.news_list.utils.Constants
import com.example.news_list.utils.State
import com.example.news_list_mvp.R
import com.example.news_list_mvp.ui.common.BaseFragment
import com.example.news_list_mvp.utils.InfinityScrollListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class NewsListFragment : BaseFragment(), NewsContract.View, NewsListAdapter.onItemClickListener{
    var currentPage = 1
    var MAX_PAGE = 5;

    override fun itemClickListener(articlesItem: ArticlesItem) {
        if (articlesItem != null) {
            getRoot().pushFragment(NewsDetailFragment.newInstance(articlesItem.url), true)
        }

    }
    var adapter = NewsListAdapter(ArrayList(), { presenter.loadData(currentPage)}, this)
    @Inject lateinit var presenter: NewsContract.Presenter

    override fun showProgress(show: Boolean) {
        if (show) {
            adapter.setState(State.LOADING)
            progressBar.visibility = View.VISIBLE
        } else {
            adapter.setState(State.DONE)
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        adapter.setState(State.ERROR)
    }

    override fun loadDataSuccess(list: List<ArticlesItem>) {
        if (list!=null){
            var position = adapter.itemCount
            adapter.addNews(list)
            recyclerView.adapter = adapter
            recyclerView.scrollToPosition(position - Constants.LIST_SCROLLING)
            adapter.setState(State.DONE)
        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun initView() {
        initializeRecycler()
        presenter.loadData(currentPage)
    }
    private fun initializeRecycler(){
        val linearLayoutManager = LinearLayoutManager(getRoot())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.apply {
            layoutManager = linearLayoutManager
            addOnScrollListener(InfinityScrollListener({loadData()}, linearLayoutManager))
        }
    }
    fun loadData(){
        if (currentPage!=MAX_PAGE){
            currentPage++
            presenter.loadData(currentPage)
        }
    }
}