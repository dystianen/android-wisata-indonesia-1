package com.example.android_wisata_indonesia_1.ui.detailwisata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android_wisata_indonesia_1.R

class DetailWisataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_wisata, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvLocation = view.findViewById<TextView>(R.id.tvLocation)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        // Ambil data dari argument
        arguments?.let {
            tvName.text = it.getString("name")
            tvLocation.text = it.getString("location")
            tvDescription.text = it.getString("description")
        }

        // Tombol kembali ke MainActivity (pop fragment)
        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}

