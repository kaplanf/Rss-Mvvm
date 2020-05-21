package com.kaplan.rssmvvm.api


import com.kaplan.rssmvvm.news.data.Channel
import com.kaplan.rssmvvm.news.data.Rss
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

  companion object {
    const val ENDPOINT = "http://www.mobilefeeds.performgroup.com/utilities/interviews/techtest/"
  }

  @GET("latestnews.xml")
  suspend fun getNews(): Response<Rss>
}