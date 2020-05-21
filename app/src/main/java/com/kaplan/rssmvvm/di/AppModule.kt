package com.kaplan.rssmvvm.di

import android.app.Application
import com.kaplan.rssmvvm.api.WebService
import com.kaplan.rssmvvm.data.AppDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

  @Singleton
  @Provides
  fun providePhotoService(okhttpClient: OkHttpClient,
                          converterFactory: GsonConverterFactory
  ) = provideService(okhttpClient, converterFactory, WebService::class.java)


  @Singleton
  @Provides
  fun provideDb(app: Application) = AppDatabase.getInstance(app)

  @Singleton
  @Provides
  fun provideNewsDao(db: AppDatabase) = db.newsDao()

  @CoroutineScropeIO
  @Provides
  fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

  @CoroutineScropeMain
  @Provides
  fun provideCoroutineScopeMain() = CoroutineScope(Dispatchers.Main)

  private fun createRetrofit(
    okhttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(WebService.ENDPOINT)
      .client(okhttpClient)
      .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
      .build()
  }

  private fun <T> provideService(okhttpClient: OkHttpClient,
                                 converterFactory: GsonConverterFactory, clazz: Class<T>): T {
    return createRetrofit(okhttpClient, converterFactory).create(clazz)
  }
}
