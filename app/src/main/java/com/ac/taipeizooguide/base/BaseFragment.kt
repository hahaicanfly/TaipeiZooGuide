package com.ac.taipeizooguide.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ac.taipeizooguide.ui.viewmodel.ZooViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created on 2021/3/15.
 */
abstract class BaseFragment : Fragment() {

    val zooViewModel: ZooViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    abstract fun setupUI()

    abstract fun setupObserver()

}