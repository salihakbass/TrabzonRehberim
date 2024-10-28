package com.example.trabzonrehberim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONArray
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: PlaceAdapter
    private val itemList = mutableListOf<Place>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.placeRv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemAdapter = PlaceAdapter(itemList)
        recyclerView.adapter = itemAdapter

        fetchJsonData()

    }
    private fun fetchJsonData() {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference.child("data/data.json")

        storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
            val jsonStr = String(bytes, Charset.defaultCharset())
            parseJsonData(jsonStr)
        }.addOnFailureListener { exception ->
            println("Dosya indirilemedi: ${exception.message}")
        }
    }
    private fun parseJsonData(jsonStr: String) {
        val jsonArray = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()) {
            val itemObject = jsonArray.getJSONObject(i)
            val name = itemObject.getString("name")
            val description = itemObject.getString("description")
            val imageUrl = itemObject.getString("image")

            val item = Place(name, description, imageUrl)
            itemList.add(item)
        }
        itemAdapter.notifyDataSetChanged()


    }

}
