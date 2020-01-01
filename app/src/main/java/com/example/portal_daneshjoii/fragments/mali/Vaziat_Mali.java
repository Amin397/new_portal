package com.example.portal_daneshjoii.fragments.mali;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.portal_daneshjoii.R;

public class Vaziat_Mali extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vaziat_mali , container , false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {

    }
}
