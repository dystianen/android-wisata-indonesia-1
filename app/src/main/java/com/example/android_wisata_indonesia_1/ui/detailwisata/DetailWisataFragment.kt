package com.example.android_wisata_indonesia_1.ui.detailwisata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_wisata_indonesia_1.databinding.FragmentDetailWisataBinding

class DetailWisataFragment : Fragment() {

    private var _binding: FragmentDetailWisataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailWisataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = DetailWisataFragmentArgs.fromBundle(requireArguments())
        val wisata = args.wisata

        binding.tvName.text = wisata.name
        binding.tvDescription.text = wisata.description
        binding.tvLocation.text = wisata.location
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
