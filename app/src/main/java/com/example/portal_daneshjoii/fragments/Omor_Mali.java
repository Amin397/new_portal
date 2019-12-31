package com.example.portal_daneshjoii.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.portal_daneshjoii.R;
import com.example.portal_daneshjoii.fragments.mali.Pardakht_Electromic;

public class Omor_Mali extends Fragment {

    private CardView payment_e , payment_error , vaziat_mali;
    private FrameLayout frm_con_mali;
    private Fragment fragmnet;
    private LinearLayout linear_menu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_omor__mali , container , false);

        initViews(view);

        payment_e.setOnClickListener(paymentEClickListener);

        return view;
    }

    private void initViews(View view) {
        payment_e = (CardView) view.findViewById(R.id.card_pardakht_e_id);
        payment_error = (CardView) view.findViewById(R.id.card_pardakht_error_id);
        vaziat_mali = (CardView) view.findViewById(R.id.card_vaziat_mali_id);
        frm_con_mali = (FrameLayout) view.findViewById(R.id.frame_container_mali_menu_id);
        linear_menu = (LinearLayout) view.findViewById(R.id.linear_menu_mali_id);
    }

    private CardView.OnClickListener paymentEClickListener
             = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            linear_menu.setVisibility(View.GONE);
            frm_con_mali.setVisibility(View.VISIBLE);
            fragmnet = new  Pardakht_Electromic();
            loadFragment(fragmnet);
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_mali_menu_id , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}