package com.ksusha.crypto.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ksusha.crypto.adapter.CryptoAdapter
import com.ksusha.crypto.databinding.FragmentHomeBinding
import com.ksusha.crypto.utils.DataStatus
import com.ksusha.crypto.utils.initRecyclerView
import com.ksusha.crypto.utils.isVisible
import com.ksusha.crypto.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    @Inject lateinit var cryptoAdapter: CryptoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        openInfo()
    }

    private fun openInfo() {
        lifecycleScope.launch {
            binding.apply {
                homeViewModel.getCoinList("eur")
                homeViewModel.coinsList.observe(viewLifecycleOwner) {
                    when(it.status) {
                        DataStatus.Status.LOADING -> { pBarLoading.isVisible(true, rvCrypto) }
                        DataStatus.Status.SUCCESS -> {
                            pBarLoading.isVisible(false, rvCrypto)
                            cryptoAdapter.differ.submitList(it.data)
                            cryptoAdapter.setOnItemClickListener {
                                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                                    it.id.toString()
                                )
                                findNavController().navigate(direction)
                            }
                        }
                        DataStatus.Status.ERROR -> {
                            pBarLoading.isVisible(true, rvCrypto)
                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvCrypto.initRecyclerView(LinearLayoutManager(requireContext()), cryptoAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}