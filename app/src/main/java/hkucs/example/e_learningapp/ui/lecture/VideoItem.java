package hkucs.example.e_learningapp.ui.lecture;

import android.graphics.Bitmap;

public class VideoItem {
    private String title;
    private String uri;
    private Bitmap cover;

    VideoItem(String title, String uri, Bitmap bitmap){
        this.title = title;
        this.uri = uri;
        this.cover = bitmap;
    }

    String getTitle() {
        return title;
    }

    String getUri(){ return uri; }

    Bitmap getCover(){ return cover; }

}
