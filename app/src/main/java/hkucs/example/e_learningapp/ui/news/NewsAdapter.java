package hkucs.example.e_learningapp.ui.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hkucs.example.e_learningapp.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    // Member variables.
    private ArrayList<News> mNewsData;
    private Context mContext;

    NewsAdapter(Context context, ArrayList<News> newsData){
        this.mContext = context;
        this.mNewsData = newsData;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        // Get current sport.
        News currentNews = mNewsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentNews);
    }

    @Override
    public int getItemCount() {
        return mNewsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mDateText;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.newsTitle);
            mDateText = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(this);
        }

        void bindTo(News currentNews){
            // Populate the textviews with data.
            mTitleText.setText(currentNews.getTitle());
            mDateText.setText(currentNews.getDate());
        }

        @Override
        public void onClick(View view) {
            News currentNews = mNewsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra(NewsFragment.NEW_ADDRESS_INFO, currentNews.getAddr());
            mContext.startActivity(detailIntent);
        }
    }
}
