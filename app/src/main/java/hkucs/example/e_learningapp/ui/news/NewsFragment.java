package hkucs.example.e_learningapp.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hkucs.example.e_learningapp.R;

public class NewsFragment extends Fragment {

    public static String NEW_ADDRESS_INFO = "hkucs.news.info";

    // Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<News> mNewsData;
    private NewsAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewsData = new ArrayList<>();
        mAdapter = new NewsAdapter(getContext(), mNewsData);
        mRecyclerView.setAdapter(mAdapter);

        // get the data
        new DownloadNews().execute();

        return root;
    }

    public class DownloadNews extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://www.hku.hk/press/")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String res = response.body().string();
                //Log.d("RESPONSE", res);
                //writeToFile(res);
                parseHtml(res);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void parseHtml(String html){

        Document doc = Jsoup.parse(html);

        // find press items
        Elements pressDivs = doc.select("div.press-item");

        Log.d("RESPONSE", pressDivs.size() + " <size>");
        mNewsData.clear();
        for(Element div: pressDivs){
            String date = div.select("span.date").text();
            String title = div.select("a[href]").text();
            String addr = "https://www.hku.hk" + div.select("a[href]").attr("href");
            mNewsData.add(new News(title, date, addr));
        }

    }
}