package com.example.trabzonrehberim

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trabzonrehberim.databinding.FragmentLocationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentLocationBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var place: Place
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentLocationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Turistik mekan bilgilerini al
        place = arguments?.getSerializable("place") as Place

        // Harita fragmentini ayarlayın
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.buttonNavigate.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${place.latitude},${place.longitude}?q=${place.latitude},${place.longitude}(${place.name})")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps") // Haritalar uygulamasında açılmasını sağlar
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                // Haritalar uygulaması yüklü değilse bir hata mesajı gösterebilirsiniz
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        val location = LatLng(place.latitude, place.longitude)
        googleMap.addMarker(MarkerOptions().position(location).title(place.name))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

}