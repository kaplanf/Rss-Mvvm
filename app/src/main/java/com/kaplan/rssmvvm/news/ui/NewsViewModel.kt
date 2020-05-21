package com.kaplan.rssmvvm.news.ui

import androidx.lifecycle.ViewModel
import com.kaplan.rssmvvm.news.data.NewsRepository
import javax.inject.Inject

class NewsViewModel @Inject constructor(repository: NewsRepository) : ViewModel() {

  val news= repository.news
}
