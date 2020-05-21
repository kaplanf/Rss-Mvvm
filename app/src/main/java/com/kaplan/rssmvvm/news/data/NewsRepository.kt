package com.kaplan.rssmvvm.news.data;

import com.kaplan.rssmvvm.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val dao: NewsDao,
                                              private val remoteSource: NewsRemoteDataSource) {

  val news =
      resultLiveData(
          databaseQuery = { dao.getNews() },
          networkCall = { remoteSource.fetchData() },
          saveCallResult = { dao.insertAll(it.channel!!.items!!) })

}
