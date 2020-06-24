package hkucs.example.e_learningapp.ui.lecture;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.ResultListener;
import com.amplifyframework.datastore.generated.model.Course;
import com.amplifyframework.datastore.generated.model.Enrollment;
import com.amplifyframework.datastore.generated.model.Student;
import com.amplifyframework.datastore.generated.model.Video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hkucs.example.e_learningapp.R;

public class LectureFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private RecyclerView mRecyclerView;
    private Map<String, ArrayList<String>> courseToVideoUrl;
    private ArrayList<VideoItem> mVideoItemData;
    private VideoAdapter mAdapter;
    private Handler handler=null;
    Runnable udpUIRunnable = new Runnable() {
        @Override
        public void run() {
            updateSpinner();
        }
    };

    //LayoutInflater mInflater;
    // Spinner
    ArrayAdapter<String> adapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_lecture, container, false);

        mRecyclerView = root.findViewById(R.id.video_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        handler = new Handler();
        courseToVideoUrl = new HashMap<>();

        mVideoItemData = new ArrayList<>();
        mAdapter = new VideoAdapter(getContext(), mVideoItemData);
        mRecyclerView.setAdapter(mAdapter);

        // Inflate a view and add to video panel
        //mInflater = getLayoutInflater();

        ArrayList<String> courseCodes = new ArrayList<>();

        // query course code
        Amplify.API.query(Student.class, Student.NAME.eq(AWSMobileClient.getInstance().getUsername()) ,new ResultListener<GraphQLResponse<Iterable<Student>>>() {
            @Override
            public void onResult(GraphQLResponse<Iterable<Student>> iterableGraphQLResponse) {
                Student userAsStudent = iterableGraphQLResponse.getData().iterator().next();
                ArrayList<Course> courses = new ArrayList<>();
                for(Enrollment e: userAsStudent.getCourses())
                    courseCodes.add(e.getCourse().getName());
                new Thread(){
                    public void run(){
                        handler.post(udpUIRunnable);
                    }
                }.start();

                /*
                for(Course c: courses)
                    courseVideoCollection.put(c, c.getVideos()==null? new ArrayList<>(): new ArrayList<>(c.getVideos()));
                 */
                Log.i("AmplifyGetStarted", "Course size : " + courseCodes.size());
                Log.i("AmplifyGetStarted", "Video : " + userAsStudent.getCourses().get(0).getCourse().getVideos());
            }
            @Override
            public void onError(Throwable throwable) {
                Log.e("AmplifyGetStarted", throwable.toString());
            }
        });








        // populate the course spinner

        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, courseCodes);
        Spinner spinner = (Spinner) root.findViewById(R.id.course_list);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);





        // async download cover image
        new DownloadVideoTask().execute();

        return root;
    }

    private void updateSpinner(){
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private class DownloadVideoTask extends AsyncTask<Void, Void, Void>{
        //ToDo: fetch data from network
        String[] paths = new String[]{
                "https://yunmachinestorage.oss-cn-hongkong.aliyuncs.com/robotic-chess-game.mp4"
        };
        String[] titles = new String[]{
                "Robotic Arm"
        };

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mVideoItemData.clear();
            for(int i= 0; i < paths.length; i++){
                try {
                    Bitmap cover = CoverUtil.retriveVideoFrameFromVideo(paths[i]);
                    mVideoItemData.add(new VideoItem(titles[i], paths[i], cover));
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter.notifyDataSetChanged();
        }
    }

}