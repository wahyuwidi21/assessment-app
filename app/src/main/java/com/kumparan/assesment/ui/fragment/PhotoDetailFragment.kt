package com.kumparan.assesment.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.kumparan.assesment.R
import com.kumparan.assesment.databinding.FragmentDetailPhotoBinding
import com.kumparan.assesment.ui.adapter.AlbumAdapter
import com.kumparan.assesment.ui.base.BaseFragment
import com.kumparan.assesment.ui.navigator.PhotoDetailNavigator
import com.kumparan.assesment.ui.viewmodel.PhotoDetailViewModel
import com.kumparan.assesment.ui.viewmodel.UserDetailViewModel
import com.kumparan.assesment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.graphics.PointF
import android.widget.ImageView


@AndroidEntryPoint
class PhotoDetailFragment : BaseFragment<FragmentDetailPhotoBinding, PhotoDetailViewModel>(false),
    PhotoDetailNavigator {

    private val photoDetailViewModel: PhotoDetailViewModel by viewModels()
    private val args: PhotoDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailPhotoBinding

    override fun setLayout(): Int = R.layout.fragment_detail_photo

    override fun getViewModels(): PhotoDetailViewModel = photoDetailViewModel

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        binding.lifecycleOwner = this
        photoDetailViewModel.navigator = this

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onReadyAction() {

        binding.txtTitle.text = args.photoTitle

        val image = GlideUrl(
            args.url, LazyHeaders.Builder()
                .addHeader("User-Agent", "5")
                .build()
        )

        Glide.with(requireContext())
            .load(image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imgFull)

        binding.btnClose.setOnClickListener {
            this.activity?.onBackPressed()
        }
    }

}




