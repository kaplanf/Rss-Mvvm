package com.kaplan.rssmvvm.news.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaplan.rssmvvm.databinding.NewsListItemBinding
import com.kaplan.rssmvvm.news.data.Item

class NewsAdapter : ListAdapter<Item, NewsAdapter.ViewHolder>(DiffCallback()) {

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.apply {
      bind(createOnClickListener(item.link), item)
      itemView.tag = item
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(NewsListItemBinding.inflate(
      LayoutInflater.from(parent.context), parent, false))
  }

  private fun createOnClickListener(url: String): View.OnClickListener {
    return View.OnClickListener {
      val direction = NewsFragmentDirections.actionToDetailFragment(url)
      it.findNavController().navigate(direction)
    }
  }

  class ViewHolder(
    private val binding: NewsListItemBinding
  ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listener: View.OnClickListener, item: Item) {
      binding.apply {
        clickListener = listener
        newsItem = item
        item.enclosure?.let {
          url = it.url
        }
        executePendingBindings()
      }
    }
  }
}

private class DiffCallback : DiffUtil.ItemCallback<Item>() {

  override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
    return oldItem.guid == newItem.guid
  }

  override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
    return oldItem == newItem
  }
}