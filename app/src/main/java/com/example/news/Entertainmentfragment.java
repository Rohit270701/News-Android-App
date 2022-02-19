package com.example.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Entertainmentfragment extends Fragment {

    String api = "e23f8767dffe4010a21a89baad98cfb0";
    ArrayList<ModelClass> modelClasses;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewofsenter;
    private String category = "entertainment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.entertainmentfragment, null);


        recyclerViewofsenter = v.findViewById(R.id.recyclerviewentertainment);
        modelClasses = new ArrayList<>();
        recyclerViewofsenter.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClasses);
        recyclerViewofsenter.setAdapter(adapter);


        findNews();

        return v;

    }

    private void findNews() {
        ApiUtilities.getApiInerface().getCategoryNews(country, category, 20, api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    modelClasses.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });

    }
}
