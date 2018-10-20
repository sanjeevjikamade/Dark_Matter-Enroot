package com.dark.matter.railapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dark.matter.railapp.common.GenericCallback_Error;
import com.dark.matter.railapp.common.GenericCallback_Success;
import com.dark.matter.railapp.common.OriginalResponse;
import com.dark.matter.railapp.model.GetTrainDetailsResponse;
import com.dark.matter.railapp.network.ApiServiceFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.dark.matter.railapp.common.Utilities.isInternetAvailable;

public class TrainsListAcitivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        getTrainDetails();
    }


    private boolean checktimings(String time, String endtime) {

        String pattern = "HH:mm::ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if(date1.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private void prepareTrainsData(List<GetTrainDetailsResponse.Trains> trains) {

        try {

            Collections.sort(trains, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    GetTrainDetailsResponse.Trains p1 = (GetTrainDetailsResponse.Trains) o1;
                    GetTrainDetailsResponse.Trains p2 = (GetTrainDetailsResponse.Trains) o2;
                    return p1.getArrivalTime().compareToIgnoreCase(p2.getArrivalTime());
                }
            });

            for(int i = 0; i < trains.size(); i++){
                GetTrainDetailsResponse.Trains obj = trains.get(i);
                //getting only
                if(obj.getTrainNo().substring(0,1).equalsIgnoreCase("9"))
                    movieList.add(new Movie(obj.getArrivalTime()+" "+obj.getSource(), obj.getSource()+" - "+obj.getDestination(), ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAdapter.notifyDataSetChanged();
    }

    public void getTrainDetails() {
        if (!isInternetAvailable(this)) {
            return;
        }
        //showProgress();
        OriginalResponse<GetTrainDetailsResponse> successRes =
                (boolean statusCode, GetTrainDetailsResponse response) -> {
                    //hideProgress();
                    if (!statusCode) {
                        return;
                    }
                   // Log.e("###101", "response" + response.getTrains().get(0).getTrainName());

                    prepareTrainsData(response.getTrains());


                };

        OriginalResponse<Throwable> errorRes =
                (boolean status, Throwable t) -> {
                    //hideProgress();
                    return;

                };
        String URL = "http://indianrailapi.com/api/v2/AllTrainOnStation/apikey/5ffc91bd8b5129dc42425ad156120264/StationCode/TNA";
        ApiServiceFactory.getApiService().getTrain(URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GenericCallback_Success<>(TrainsListAcitivity.this, true, successRes),
                        new GenericCallback_Error<>(TrainsListAcitivity.this, true, errorRes));
    }


}
