package com.kaplan.rssmvvm.news.data

import com.kaplan.rssmvvm.api.BaseDataSource
import com.kaplan.rssmvvm.api.WebService
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val service: WebService) : BaseDataSource() {

  suspend fun fetchData() = getResult { service.getNews() }

}
