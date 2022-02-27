package com.kumparan.assesment.ui.fragment

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kumparan.assesment.R
import com.kumparan.assesment.data.model.PostFinal
import com.kumparan.assesment.databinding.FragmentListPostBinding
import com.kumparan.assesment.ui.navigator.ListPostNavigator
import com.kumparan.assesment.ui.viewmodel.ListPostViewModel
import com.kumparan.assesment.ui.adapter.ListPostAdapter
import com.kumparan.assesment.ui.base.BaseFragment
import com.kumparan.assesment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListPostFragment : BaseFragment<FragmentListPostBinding, ListPostViewModel>(true),
    ListPostNavigator {

    private val detailViewModel: ListPostViewModel by viewModels()
    private val mainNavController by lazy { requireActivity().findNavController(R.id.nav_host_main) }


    private lateinit var binding: FragmentListPostBinding
    private lateinit var listPostAdapter: ListPostAdapter

    override fun setLayout(): Int = R.layout.fragment_list_post

    override fun getViewModels(): ListPostViewModel = detailViewModel


    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        detailViewModel.navigator = this
    }

    override fun onReadyAction() {
        detailViewModel.getPosts()
    }

    override fun onObserveAction() {
        detailViewModel.postData.observe(this, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.skeletonListPost.showSkeleton()
                }
                Resource.Status.ERROR -> {
                    binding.skeletonListPost.showOriginal()
                }
                Resource.Status.SUCCESS -> {

                    if (!it.data.isNullOrEmpty()) {
                        listPostAdapter = ListPostAdapter(it.data) { data ->
                            detailViewModel.navigator?.goToDetailPost(data)
                        }

                        binding.rvPost.apply {
                            visibility = View.VISIBLE
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = listPostAdapter
                        }

                    }
                    binding.skeletonListPost.showOriginal()
                }
            }
        })
    }

    override fun goToDetailPost(
        data: PostFinal
    ) {
        val bundle =
            bundleOf(
                "data" to data
            )
        mainNavController.navigate(
            R.id.action_fragment_list_post_to_fragment_detail_post,
            bundle
        )
    }
}