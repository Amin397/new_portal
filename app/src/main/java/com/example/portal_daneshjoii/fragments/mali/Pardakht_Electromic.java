package com.example.portal_daneshjoii.fragments.mali;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portal_daneshjoii.R;

public class Pardakht_Electromic extends Fragment {

    private EditText txt_amount;
    private Button btn_pay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pardakht_electronic , container , false);

        initViews(view);

        btn_pay.setOnClickListener(paymentClickListener);
        return view;
    }

    private void initViews(View view) {
        txt_amount = (EditText) view.findViewById(R.id.txt_amount_id);
        btn_pay = (Button) view.findViewById(R.id.btn_pay_id);
    }

    private Button.OnClickListener paymentClickListener
             = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity() , "Payment clicked !" , Toast.LENGTH_SHORT).show();
        }
    };
}
