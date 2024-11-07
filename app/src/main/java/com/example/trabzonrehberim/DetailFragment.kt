package com.example.trabzonrehberim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.trabzonrehberim.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailFragmentArgs by navArgs()
        val place = args.Place

        binding.viewPager.adapter = ViewPagerAdapter(this,place)
        binding.viewPagerPhotos.adapter = PhotoPagerAdapter(place.photoUrls)

        binding.dotsIndicator.setViewPager2(binding.viewPagerPhotos)



        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Tarihçe"
                1 -> "Yapılabilecek Aktiviteler"
                2 -> "Konum"
                else -> null
            }
        }.attach()

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }


    }

}