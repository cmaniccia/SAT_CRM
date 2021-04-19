package com.salientsys.salientandroidtest.main.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.salientsys.salientandroidtest.SalientTestApplication
import com.salientsys.salientandroidtest.data.Result
import com.salientsys.salientandroidtest.data.entity.User
import com.salientsys.salientandroidtest.databinding.FragmentUserBinding
import com.salientsys.salientandroidtest.di.viewmodel.injectViewModel
import timber.log.Timber
import javax.inject.Inject

class UserFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var userViewModel: UserViewModel

    lateinit var usersAdapter: UsersAdapter

    private var _binding: FragmentUserBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("View being created!")
        (requireActivity().application as SalientTestApplication).appComponent.inject(this)
        userViewModel = injectViewModel(viewModelFactory)
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersAdapter = UsersAdapter { user -> adapterOnClick(user) }
        binding.fragmentUserRecyclerView.adapter = usersAdapter
        binding.fragmentUserRecyclerView.layoutManager = LinearLayoutManager(this.context)
        subscribeUserList(binding)
        Timber.d("View created!")
    }


    private fun subscribeUserList(binding: FragmentUserBinding) {
        userViewModel.getUsers().observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.fragmentUserProgressBar.isVisible = false
                    Timber.d("SUCCESS")
                    usersAdapter.submitList(result.data as MutableList<User>)
                }
                Result.Status.LOADING -> binding.fragmentUserProgressBar.isVisible = true
                Result.Status.ERROR -> {
                    binding.fragmentUserProgressBar.isVisible = false
                }
            }
        })
    }

    private fun adapterOnClick(user: User) {
        Timber.d("User %d clicked!", user.id)
        // On click function
        val userId = user.id
        val action = UserFragmentDirections.actionUserFragmentToUserDetailsFragment(userId)
        view?.findNavController()?.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
