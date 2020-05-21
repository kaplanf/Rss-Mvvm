package com.kaplan.rssmvvm.di



import com.kaplan.rssmvvm.news.ui.DetailFragment
import com.kaplan.rssmvvm.news.ui.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contributeNewsFragment(): NewsFragment

  @ContributesAndroidInjector
  abstract fun contributeDetailFragment(): DetailFragment
}
