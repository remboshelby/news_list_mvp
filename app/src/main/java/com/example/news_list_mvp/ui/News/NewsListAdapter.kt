package com.example.news_list_mvp.ui.News

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.news_list.data.entities.ArticlesItem
import com.example.news_list.ui.list.viewHolders.ListFooterViewHolder
import com.example.news_list.ui.list.viewHolders.NewsViewHolder
import com.example.news_list.utils.State

class NewsListAdapter (private val list: List<ArticlesItem>,
                       private val retry: () -> Unit,
                       fragment: Fragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            (holder as NewsViewHolder).bind(getItem(position))
            holder.itemView.setOnClickListener {
                listener.itemClickListener(getItem(position));
            }
        }
        else (holder as ListFooterViewHolder).bind(state)
    }


    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2


    private var articlesList_ = ArrayList<ArticlesItem>()
    private var state = State.LOADING

    private val listener: NewsListAdapter.onItemClickListener

    init {
        this.articlesList_ = list as ArrayList<ArticlesItem>
        this.listener = fragment as NewsListAdapter.onItemClickListener
    }
    interface onItemClickListener {
        fun itemClickListener(articlesItem: ArticlesItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) NewsViewHolder.create(parent) else ListFooterViewHolder.create(
            retry,
            parent
        )
    }

    override fun getItemCount(): Int {
        return articlesList_.size+ if (hasFooter()) 1 else 0
    }
    fun addNews(articlesList: List<ArticlesItem>){
        var initPosition = articlesList_.size
        this.articlesList_.addAll(articlesList)
        notifyItemRangeChanged(initPosition, articlesList.size)

    }
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        holder.bind(getItem(position))
//        holder.itemView.setOnClickListener {
//            listener.itemClickListener(getItem(position));
//        }
//    }
    override fun getItemViewType(position: Int): Int {
        return if (position < getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    private fun hasFooter(): Boolean {
        return articlesList_.size != 0 && (state == State.LOADING || state == State.ERROR)
    }
    private fun getItem(position: Int): ArticlesItem {
        return list.get(position)
    }
    fun setState(state: State) {
        this.state = state
        notifyItemChanged(getItemCount())
    }
}