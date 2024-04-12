package com.example.swanncodetest

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.swanncodetest.adapter.VideoAdapter
import com.example.swanncodetest.databinding.ActivityMainBinding
import com.example.swanncodetest.model.Manifest
import com.example.swanncodetest.model.VideoUrl
import com.example.swanncodetest.viewmodel.VideoActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val videoActivityViewModel: VideoActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeUi()
    }

    private fun subscribeUi() {
        videoActivityViewModel.videoResponse.observe(this) {
            when(it.status) {
                ConnectStatus.LOADING -> {

                }
                ConnectStatus.ERROR -> {

                }
                ConnectStatus.SUCCESS -> {
                    it.data?.let { data ->
                        val adapter = VideoAdapter(videoActivityViewModel)
                        binding.apply {
                            videoVp.adapter = adapter
                        }
                        adapter.submitList(null)
                       adapter.submitList(convertUrlToAList(data.manifest))
                        TabLayoutMediator(binding.videoTl, binding.videoVp) {tab, position -> }.attach()
                    }
                }
            }
        }

        videoActivityViewModel.showControl.observe(this) {
            if (it) {
                binding.videoTl.visibility = VISIBLE
            } else {
                binding.videoTl.visibility = GONE
            }
        }
    }

    private fun convertUrlToAList(manifest: Manifest): List<VideoUrl> {
        val urlList = arrayListOf<VideoUrl>()
        val url1 = manifest.url1
        val url2 = manifest.url2
        val url3 = manifest.url3
        urlList.apply {
            add(VideoUrl(url1))
            add(VideoUrl(url2))
            add(VideoUrl(url3))

        }
        return urlList
    }
}