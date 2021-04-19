package com.salientsys.salientandroidtest.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.salientsys.salientandroidtest.SalientTestApplication
import com.salientsys.salientandroidtest.data.Result
import com.salientsys.salientandroidtest.databinding.FragmentPostDetailsBinding
import com.salientsys.salientandroidtest.di.viewmodel.injectViewModel
import timber.log.Timber
import javax.inject.Inject

class PostDetailsFragment : Fragment() {
    private val args: PostDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var postDetailsViewModel: PostDetailsViewModel

    private var _binding: FragmentPostDetailsBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("View being created!")
        (requireActivity().application as SalientTestApplication).appComponent.inject(this)
        postDetailsViewModel = injectViewModel(viewModelFactory)

        _binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("View created!")
        Timber.d("Got navigation args: %d", args.postId)
        subscribePostDetails()

        _binding?.fragmentPostDetailsDeletePostButton?.setOnClickListener{
            Timber.d("onclick!");


            postDetailsViewModel.deletePostById(args.postId).observe(viewLifecycleOwner) { result ->
                when (result.status) {
                    Result.Status.SUCCESS -> {
                        binding.fragmentPostDetailsProgressBar.isVisible = false
                        if (result.data != null) {
                            binding.fragmentPostDetailsTitle.text = "Deleted"
                            binding.fragmentPostDetailsBody.text = ""
                        } else {
                            binding.fragmentPostDetailsTitle.text = "Error Loading Data"
                            binding.fragmentPostDetailsBody.text = ""
                        }
                        Timber.d("SUCCESS")
                    }
                    Result.Status.LOADING -> binding.fragmentPostDetailsProgressBar.isVisible = true
                    Result.Status.ERROR -> {
                        binding.fragmentPostDetailsTitle.text = "Error Loading Data"
                        binding.fragmentPostDetailsBody.text = ""
                        binding.fragmentPostDetailsProgressBar.isVisible = false
                    }
                }
            }



        }

    }

    private fun subscribePostDetails() {
        postDetailsViewModel.getPostById(args.postId).observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.fragmentPostDetailsProgressBar.isVisible = false
                    if (result.data != null) {
                        binding.fragmentPostDetailsTitle.text = result.data.title
                        binding.fragmentPostDetailsBody.text = result.data.body
                    } else {
                        binding.fragmentPostDetailsTitle.text = "Error Loading Data"
                        binding.fragmentPostDetailsBody.text = ""
                    }
                    Timber.d("SUCCESS")
                }
                Result.Status.LOADING -> binding.fragmentPostDetailsProgressBar.isVisible = true
                Result.Status.ERROR -> {
                    binding.fragmentPostDetailsTitle.text = "Error Loading Data"
                    binding.fragmentPostDetailsBody.text = ""
                    binding.fragmentPostDetailsProgressBar.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
