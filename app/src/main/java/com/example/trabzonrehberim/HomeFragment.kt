package com.example.trabzonrehberim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabzonrehberim.databinding.FragmentHomeBinding
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONArray
import java.nio.charset.Charset


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var placeAdapter: PlaceAdapter
    private val itemList = mutableListOf<Place>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.placeRv.layoutManager = LinearLayoutManager(requireContext())
        placeAdapter = PlaceAdapter(itemList)
        binding.placeRv.adapter = placeAdapter

        val place = Place(
            1, "Example Place", "This is the description", "image_url", 0.0, 0.0,
            emptyList()
        )
        val fragment = HistoryFragment()
        val bundle = Bundle()
        bundle.putSerializable("place", place)
        fragment.arguments = bundle



        fetchJsonData()

    }

    private fun fetchJsonData() {
        itemList.clear()
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
            val itemObject = jsonArray.optJSONObject(i)
            if (itemObject != null) {
                val id = itemObject.optInt("id", 0)
                val name = itemObject.optString("name", "No Name")
                val description = itemObject.optString("description", "No Description")
                val imageUrl = itemObject.optString("image", "")
                val latitude = itemObject.optDouble("latitude", 0.0)
                val longitude = itemObject.optDouble("longitude", 0.0)
                // Fotoğraf URL'lerini liste olarak al
                val photoUrls = mutableListOf<String>()
                val photosArray = itemObject.optJSONArray("photoUrls")
                if (photosArray != null) {
                    for (j in 0 until photosArray.length()) {
                        photoUrls.add(photosArray.optString(j, ""))
                    }
                }

                val item = Place(id, name, description, imageUrl, latitude, longitude, photoUrls)
                itemList.add(item)
            } else {
                println("Null object found at index $i")
            }
        }
        placeAdapter.notifyDataSetChanged()
    }

}