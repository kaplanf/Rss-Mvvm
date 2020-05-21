package com.kaplan.rssmvvm.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kaplan.rssmvvm.R
import com.kaplan.rssmvvm.data.Result
import com.kaplan.rssmvvm.databinding.FragmentNewsBinding
import com.kaplan.rssmvvm.di.Injectable
import com.kaplan.rssmvvm.di.injectViewModel
import com.kaplan.rssmvvm.ui.VerticalItemDecoration
import com.kaplan.rssmvvm.ui.hide
import com.kaplan.rssmvvm.ui.show
import javax.inject.Inject


class NewsFragment : Fragment(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var viewModel: NewsViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    viewModel = injectViewModel(viewModelFactory)

    val binding = FragmentNewsBinding.inflate(inflater, container, false)
    context ?: return binding.root

    val adapter = NewsAdapter()
    binding.recyclerView.addItemDecoration(
      VerticalItemDecoration(resources.getDimension(R.dimen.margin_normal).toInt(), true) )
    binding.recyclerView.adapter = adapter

    subscribeUi(binding, adapter)

    setHasOptionsMenu(true)
    return binding.root
  }

  private fun subscribeUi(binding: FragmentNewsBinding, adapter: NewsAdapter) {
    viewModel.news.observe(viewLifecycleOwner, Observer { result ->
      when (result.status) {
        Result.Status.SUCCESS -> {
          binding.progressBar.hide()
          result.data?.let {
            adapter.submitList(it)
          }
        }
        Result.Status.LOADING -> binding.progressBar.show()
        Result.Status.ERROR -> {
          binding.progressBar.hide()
          Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
        }
      }
    })
  }
}