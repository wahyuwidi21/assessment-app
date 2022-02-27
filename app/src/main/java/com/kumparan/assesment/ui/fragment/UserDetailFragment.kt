package com.kumparan.assesment.ui.fragment

import android.annotation.SuppressLint
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kumparan.assesment.R
import com.kumparan.assesment.databinding.FragmentDetailUserBinding
import com.kumparan.assesment.ui.adapter.AlbumAdapter
import com.kumparan.assesment.ui.base.BaseFragment
import com.kumparan.assesment.ui.navigator.UserDetailNavigator
import com.kumparan.assesment.ui.viewmodel.UserDetailViewModel
import com.kumparan.assesment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentDetailUserBinding, UserDetailViewModel>(true),
    UserDetailNavigator {

    private val userDetailViewModel: UserDetailViewModel by viewModels()
    private val args: UserDetailFragmentArgs by navArgs()
    private val mainNavController by lazy { requireActivity().findNavController(R.id.nav_host_main) }

    private lateinit var binding: FragmentDetailUserBinding
    private lateinit var albumAdapter: AlbumAdapter

    override fun setLayout(): Int = R.layout.fragment_detail_user

    override fun getViewModels(): UserDetailViewModel = userDetailViewModel

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        userDetailViewModel.navigator = this
    }

    override fun onReadyAction() {
        userDetailViewModel.getUser(args.userId)
        userDetailViewModel.getAlbumsWithPhotos(args.userId)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onObserveAction() {
        userDetailViewModel.user.observe(this, {
            when (it.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.ERROR -> {

                }
                Resource.Status.SUCCESS -> {
                    binding.userDetailNameValue.text = it.data?.name.toString()
                    binding.userDetailEmailValue.text = it.data?.email.toString()
                    binding.userDetailAddressValue.text =
                        "${it.data?.address?.street}, ${it.data?.address?.suite}, ${it.data?.address?.city}, ${it.data?.address?.zipcode}"
                    binding.userDetailCompanyValue.text = it.data?.company?.name
                }
            }
        })

        userDetailViewModel.albumWithPhoto.observe(this, { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> {
                    binding.loadingProgressAlbum.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    binding.loadingProgressAlbum.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {

                    if (resource.data != null) {
                        albumAdapter = AlbumAdapter(
                            resource.data
                        ) { photo ->
                            userDetailViewModel.navigator?.goToPhotoDetail(photo.url, photo.title)
                        }
                    }
                    binding.loadingProgressAlbum.visibility = View.GONE
                    binding.rvAlbum.apply {
                        visibility = View.VISIBLE
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = albumAdapter
                    }

                }
            }
        })
    }

    override fun goToPhotoDetail(url: String, photoTitle: String) {
        val bundle =
            bundleOf(
                "url" to url,
                "photoTitle" to photoTitle
            )
        mainNavController.navigate(
            R.id.action_fragment_detail_user_to_fragment_detail_photo,
            bundle
        )
    }
}