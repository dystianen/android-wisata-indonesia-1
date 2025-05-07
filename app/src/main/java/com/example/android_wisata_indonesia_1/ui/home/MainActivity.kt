package com.example.android_wisata_indonesia_1.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
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

        wisataList = listOf(
            Wisata(1, "Pantai Dewata", "Cantik", "Bali"),
            Wisata(2, "Taman Monas", "Cantik", "Jakarta"),
            Wisata(3, "Pangalengan", "Cantik", "Bandung"),
        )

        recyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = WisataAdapter(
            onItemClick = { wisata ->
                // Navigasi ke DetailWisataFragment
                val fragment = DetailWisataFragment().apply {
                    arguments = Bundle().apply {
                        putInt("id", wisata.id)
                        putString("name", wisata.name)
                        putString("description", wisata.description)
                        putString("location", wisata.location)
                    }
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment) // Pastikan ada frame layout dengan id fragmentContainer
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
            },
        )
        recyclerView.adapter = taskAdapter

        val spacingInDp = 2
        val spacingInPx = (spacingInDp * resources.displayMetrics.density).toInt()
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(spacingInPx))

        taskAdapter.submitList(wisataList)

        val fabAddWisata: FloatingActionButton = findViewById(R.id.fabAddWisata)
        fabAddWisata.setOnClickListener {
            val intent = Intent(this, AddWisataActivity::class.java)
            addTaskLauncher.launch(intent)
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
