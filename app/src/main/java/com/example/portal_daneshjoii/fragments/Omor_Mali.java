package com.example.portal_daneshjoii.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.portal_daneshjoii.R;

public class Omor_Mali extends Fragment {

    private CardView payment_e , payment_error , vaziat_mali;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_omor__mali , container , false);

        payment_e = (CardView) view.findViewById(R.id.card_pardakht_e_id);
        payment_error = (CardView) view.findViewById(R.id.card_pardakht_error_id);
        vaziat_mali = (CardView) view.findViewById(R.id.card_vaziat_mali_id);

        payment_e.setOnClickListener(paymentEClickListener);

        return view;
    }

    private CardView.OnClickListener paymentEClickListener
             = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
