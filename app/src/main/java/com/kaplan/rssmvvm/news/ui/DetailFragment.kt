package com.kaplan.rssmvvm.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kaplan.rssmvvm.R
import com.kaplan.rssmvvm.binding.bindImageFromUrl
import com.kaplan.rssmvvm.databinding.FragmentDetailBinding
import com.kaplan.rssmvvm.di.Injectable
import com.kaplan.rssmvvm.news.data.Item
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var news: Item


  private val args: DetailFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
      inflater, R.layout.fragment_detail, container, false).apply {
      lifecycleOwner = this@DetailFragment
      url = args.url
      executePendingBindings()
    }

    return binding.root
  }
}