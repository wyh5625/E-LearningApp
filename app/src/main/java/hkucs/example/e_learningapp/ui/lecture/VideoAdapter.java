package hkucs.example.e_learningapp.ui.lecture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hkucs.example.e_learningapp.R;
import hkucs.example.videoplayersample.VideoPlayerActivity;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    // member
    ArrayList<VideoItem> mVideoItemData;
    Context mContext;

    VideoAdapter(Context context, ArrayList<VideoItem> videoItemData){
        mContext = context;
        mVideoItemData = videoItemData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.course_video_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoItem currentVideoItem = mVideoItemData.get(position);
        holder.bindTo(currentVideoItem);
    }

    @Override
    public int getItemCount() {
        return mVideoItemData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleText;
        private ImageView coverImg;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            titleText = itemView.findViewById(R.id.video_title);
            coverImg = itemView.findViewById(R.id.video_cover);

            itemView.setOnClickListener(this);
        }

        void bindTo(VideoItem currentVideoItem){
            // Populate the textviews with data.
            titleText.setText(currentVideoItem.getTitle());
            coverImg.setImageBitmap(currentVideoItem.getCover());
            //mInfoText.setText(currentNews.getInfo());
        }

        @Override
        public void onClick(View view) {
            VideoItem currentVideoItem = mVideoItemData.get(getAdapterPosition());
            Intent videoIntent = new Intent(mContext, VideoPlayerActivity.class);
            videoIntent.putExtra(VideoPlayerActivity.VIDEO_URI, currentVideoItem.getUri());
            mContext.startActivity(videoIntent);
        }
    }

}
