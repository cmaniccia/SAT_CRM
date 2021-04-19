package com.salientsys.salientandroidtest.main.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.salientsys.salientandroidtest.SalientTestApplication
import com.salientsys.salientandroidtest.data.Result
import com.salientsys.salientandroidtest.databinding.FragmentUserDetailsBinding
import com.salientsys.salientandroidtest.di.viewmodel.injectViewModel
import timber.log.Timber
import javax.inject.Inject

class UserDetailsFragment : Fragment() {
    private val args: UserDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var userDetailsViewModel: UserDetailsViewModel

    private var _binding: FragmentUserDetailsBinding? = null

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
        userDetailsViewModel = injectViewModel(viewModelFactory)

        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("View created!")
        Timber.d("Got navigation args: %d", args.userId)
        subscribeUserDetails()

        _binding?.fragmentUserDetailsViewPostButton?.setOnClickListener{
            Timber.d("onclick!");
            //view.findNavController()
            //.navigate(R.id.action_userDetailsFragment_to_userPostsFragment)
            //val userId = user.id
            val action = UserDetailsFragmentDirections.actionUserDetailsFragmentToPostFragment(args.userId)
            view.findNavController().navigate(action)

        }

    }

    private fun subscribeUserDetails() {
        userDetailsViewModel.getUserById(args.userId).observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.fragmentUserDetailsProgressBar.isVisible = false
                    if (result.data != null) {
                        binding.fragmentUserDetailsName.text = result.data.name
                        binding.fragmentUserDetailsUsername.text = result.data.username
                        binding.fragmentUserDetailsEmail.text = result.data.email
                        binding.fragmentUserDetailsWebsite.text = result.data.website
                        binding.fragmentUserDetailsPhone.text = result.data.phone
                    } else {
                        binding.fragmentUserDetailsName.text = "Error Loading Data"
                        binding.fragmentUserDetailsUsername.text = ""
                        binding.fragmentUserDetailsEmail.text = ""
                        binding.fragmentUserDetailsWebsite.text = ""
                        binding.fragmentUserDetailsPhone.text = ""
                    }
                    Timber.d("SUCCESS")
                }
                Result.Status.LOADING -> binding.fragmentUserDetailsProgressBar.isVisible = true
                Result.Status.ERROR -> {
                    binding.fragmentUserDetailsName.text = "Error Loading Data"
                    binding.fragmentUserDetailsUsername.text = ""
                    binding.fragmentUserDetailsEmail.text = ""
                    binding.fragmentUserDetailsWebsite.text = ""
                    binding.fragmentUserDetailsPhone.text = ""
                    binding.fragmentUserDetailsProgressBar.isVisible = false
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
