package com.example.android_wisata_indonesia_1.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_wisata_indonesia_1.R
import com.example.android_wisata_indonesia_1.model.Wisata
import com.example.android_wisata_indonesia_1.ui.addwisata.AddWisataActivity
import com.example.android_wisata_indonesia_1.ui.detailwisata.DetailWisataFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: WisataAdapter
    private lateinit var wisataList: List<Wisata>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fabAddWisata: FloatingActionButton = findViewById(R.id.fabAddWisata)

        wisataList = listOf(
            Wisata(1, "Bukit Pelangi", "Panorama indah saat matahari terbit", "Yogyakarta"),
            Wisata(2, "Danau Kristal", "Air jernih dan cocok untuk piknik keluarga", "Sumatera Utara"),
            Wisata(3, "Goa Seribu Cahaya", "Goa dengan pantulan cahaya alami", "Pacitan"),
            Wisata(4, "Hutan Cemara Abadi", "Trekking santai di antara pepohonan tinggi", "Malang"),
            Wisata(5, "Pantai Pasir Merah", "Pasir unik berwarna kemerahan", "NTT")
        )

        recyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = WisataAdapter(
            onItemClick = { wisata ->
                val fragment = DetailWisataFragment().apply {
                    arguments = Bundle().apply {
                        putInt("id", wisata.id)
                        putString("name", wisata.name)
                        putString("description", wisata.description)
                        putString("location", wisata.location)
                    }
                }

                recyclerView.visibility = View.GONE
                fabAddWisata.visibility = View.GONE

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            },
        )
        recyclerView.adapter = taskAdapter

        val spacingInDp = 2
        val spacingInPx = (spacingInDp * resources.displayMetrics.density).toInt()
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(spacingInPx))

        taskAdapter.submitList(wisataList)

        fabAddWisata.setOnClickListener {
            val intent = Intent(this, AddWisataActivity::class.java)
            addTaskLauncher.launch(intent)
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                recyclerView.visibility = View.VISIBLE
                fabAddWisata.visibility = View.VISIBLE
            }
        }
    }

    private val addTaskLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data ?: return@registerForActivityResult
            val name = data.getStringExtra("name") ?: return@registerForActivityResult
            val description = data.getStringExtra("description") ?: ""
            val location = data.getStringExtra("location") ?: return@registerForActivityResult

            val newTask = Wisata(
                id = wisataList.size + 1,
                name = name,
                description = description,
                location = location,
            )

            wisataList = wisataList + newTask
            taskAdapter.submitList(wisataList)
        }
    }
}
