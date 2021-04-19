package com.salientsys.salientandroidtest.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.salientsys.salientandroidtest.SalientTestApplication
import com.salientsys.salientandroidtest.data.Result
import com.salientsys.salientandroidtest.data.entity.Post
import com.salientsys.salientandroidtest.databinding.FragmentPostBinding
import com.salientsys.salientandroidtest.di.viewmodel.injectViewModel
import com.salientsys.salientandroidtest.main.user.UserFragmentDirections
import com.salientsys.salientandroidtest.main.userdetails.UserDetailsFragmentArgs
import timber.log.Timber
import javax.inject.Inject

class PostFragment : Fragment() {

    private val args: UserDetailsFragmentArgs by navArgs()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var postViewModel: PostViewModel

    lateinit var postAdapter: PostAdapter

    private var _binding: FragmentPostBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("View being created!")
        (requireActivity().application as SalientTestApplication).appComponent.inject(this)
        postViewModel = injectViewModel(viewModelFactory)
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postAdapter = PostAdapter { post -> adapterOnClick(post) }
        binding.fragmentPostRecyclerView.adapter = postAdapter
        binding.fragmentPostRecyclerView.layoutManager = LinearLayoutManager(this.context)
        subscribePostList(binding)
        Timber.d("View created!")
    }


    private fun subscribePostList(binding: FragmentPostBinding) {
        postViewModel.getPosts(args.userId).observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.fragmentPostProgressBar.isVisible = false
                    Timber.d("SUCCESS")
                    postAdapter.submitList(result.data as MutableList<Post>)
                }
                Result.Status.LOADING -> binding.fragmentPostProgressBar.isVisible = true
                Result.Status.ERROR -> {
                    binding.fragmentPostProgressBar.isVisible = false
                }
            }
        })
    }

    private fun adapterOnClick(post: Post) {
        Timber.d("Post %d clicked!", post.id)
        // On click function
        //val postId = post.id
        //val action = PostFragmentDirections.actionPostFragmentToPostDetailFragment(postId)
        //view.findNavController().navigate(action)
        val postId = post.id
        val action = PostFragmentDirections.actionPostFragmentToPostDetailFragment(postId)
        view?.findNavController()?.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
