package com.example.swanncodetest.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swanncodetest.databinding.SingleVideoPageBinding
import com.example.swanncodetest.model.VideoUrl
import com.example.swanncodetest.viewmodel.VideoActivityViewModel

/**
 * Created by chad on 12/4/2024
 */
class VideoAdapter(
    private val videoActivityViewModel: VideoActivityViewModel
): ListAdapter<VideoUrl, RecyclerView.ViewHolder>(VideoDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        SingleVideoPageBinding.inflate(LayoutInflater.from(parent.context), parent, false), videoActivityViewModel
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uri = getItem(position).url
        (holder as VideoAdapter.ViewHolder).bind(uri)
    }

    class ViewHolder(val binding: SingleVideoPageBinding, val videoActivityViewModel: VideoActivityViewModel) : RecyclerView.ViewHolder(binding.root) {
        fun bind(uriString: String) {
            binding.apply {
                val url = Uri.parse(uriString)
                this.singleVv.setVideoURI(url)
                this.singleVv.start()
                this.singleVv.setOnClickListener {
                    videoActivityViewModel.showControlPanel()
                }
                executePendingBindings()
            }
        }
    }
}

private class VideoDiffCallBack: DiffUtil.ItemCallback<VideoUrl>() {
    override fun areItemsTheSame(oldItem: VideoUrl, newItem: VideoUrl): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: VideoUrl, newItem: VideoUrl): Boolean {
        return oldItem == newItem
    }

}