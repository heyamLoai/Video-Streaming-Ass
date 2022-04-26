package com.h.abassira.videostreaminglecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.h.abassira.videostreaminglecture.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String videoLink ="https://firebasestorage.googleapis.com/v0/b/assignment-1-63561.appspot.com/o/WhatsApp%20Video%202022-04-26%20at%203.23.24%20PM.mp4?alt=media&token=0bee1e4d-0d56-4657-9cf3-b4179eecbfd9";
    ActivityMainBinding binding;
    PlayerView pv;

    SimpleExoPlayer player ;
    boolean playWhenReady = true;
    long curentPostion = 0 ;
    int curentWindow = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pv = binding.playerView;

    }

    private void  initPlayer(){

        player = new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);
        MediaItem item = MediaItem.fromUri(videoLink);
        player.setMediaItem(item);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(curentWindow , curentPostion);
        player.prepare();


    }



    private  void  relesePlayer(){
        if(player != null){
            playWhenReady = player.getPlayWhenReady();
            curentWindow = player.getCurrentWindowIndex();
            curentPostion = player.getCurrentPosition();
            player = null ;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(player != null){
            initPlayer();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        relesePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        relesePlayer();
    }
}