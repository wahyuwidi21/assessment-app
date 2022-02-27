package com.kumparan.assesment.ui.fragment

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kumparan.assesment.R
import com.kumparan.assesment.databinding.FragmentDetailPostBinding
import com.kumparan.assesment.ui.adapter.CommentPostAdapter
import com.kumparan.assesment.ui.base.BaseFragment
import com.kumparan.assesment.ui.navigator.DetailPostNavigator
import com.kumparan.assesment.ui.viewmodel.DetailPostViewModel
import com.kumparan.assesment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostFragment : BaseFragment<FragmentDetailPostBinding, DetailPostViewModel>(true),
    DetailPostNavigator {

    private val detailViewModel: DetailPostViewModel by viewModels()
    private val args: DetailPostFragmentArgs by navArgs()
    private val mainNavController by lazy { requireActivity().findNavController(R.id.nav_host_main) }


    private lateinit var binding: FragmentDetailPostBinding
    private lateinit var commentPostAdapter: CommentPostAdapter

    override fun setLayout(): Int = R.layout.fragment_detail_post

    override fun getViewModels(): DetailPostViewModel = detailViewModel

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        detailViewModel.navigator = this
    }

    override fun onReadyAction() {
        detailViewModel.getComments(1)
        binding.postDetailTitle.text = args?.data?.title
        binding.postDetailUserName.text = args?.data?.userName
        binding.postDetailBody.text = args?.data?.body
        binding.postDetailUserName.setOnClickListener {
            detailViewModel.navigator?.goToUserDetail(args.data.postId)
        }
    }

    override fun onObserveAction() {
        detailViewModel.comments.observe(this, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.skeletonDetailPost.showSkeleton()
                }
                Resource.Status.ERROR -> {
                    binding.skeletonDetailPost.showOriginal()
                }
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        commentPostAdapter = CommentPostAdapter(it.data)

                        binding.rvComment.apply {
                            visibility = View.VISIBLE
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = commentPostAdapter
                        }
                    }
                    binding.skeletonDetailPost.showOriginal()
                }
            }
        })
    }

    override fun goToUserDetail(userId: Int) {
        val bundle =
            bundleOf(
                "userId" to userId
            )
        mainNavController.navigate(
            R.id.action_fragment_detail_post_to_fragment_detail_user,
            bundle
        )
    }
}