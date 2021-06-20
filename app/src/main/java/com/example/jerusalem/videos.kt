package com.example.jerusalem

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


class videos : Fragment() {
    var videoURL =
        "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
    var playerView: PlayerView? = null
    var player: SimpleExoPlayer? = null

    private var playWhenReady = true
    private var currentWindow = 0
    private var playBackPosition: Long = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_videos, container, false)

        initVideo(videoURL)
    }
        fun initVideo(ur :String) {
            //player
            player = ExoPlayerFactory.newSimpleInstance(activity)
            playerView!!.player = player
            //media source
            var uri:Uri ?= null
            uri = Uri.parse(ur)
            val dataSourceFactory: DataSource.Factory =
                DefaultDataSourceFactory(activity, "exoplayer-codelab")
            val mediaSource: MediaSource =
                ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
            player!!.setPlayWhenReady(playWhenReady)
            player!!.seekTo(currentWindow, playBackPosition)
            player!!.prepare(mediaSource, false, false)
        }

        fun releaseVideo() {
            if (player != null) {
                playWhenReady = player!!.playWhenReady
                playBackPosition = player!!.currentPosition
                currentWindow = player!!.currentWindowIndex
                player!!.release()
                player = null
            }
        }
     /* override  fun onStart() {
            super.onStart()
            initVideo(videoURL)
        }*/

   /* override fun onResume() {
            super.onResume()
            if (player != null) {
                initVideo(videoURL)
            }
        }*/

    override fun onPause() {
            super.onPause()
            releaseVideo()
        }

    override fun onStop() {
            super.onStop()
            releaseVideo()

        }


}