package com.example.portal_daneshjoii.classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.portal_daneshjoii.MainActivity;
import com.example.portal_daneshjoii.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

public class Functions {

    Context context;
    FragmentActivity fragmentActivity;

    public Functions(Context context) {
        this.context = context;
    }

    public void phoneChange(Context context ,final String nationalcode , LayoutInflater inflater){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCustomTitle(inflater.inflate(R.layout.title_view_submitmobile , null));
        final View customLayout_mobile = inflater.inflate(R.layout.alert_changemobile_view,null);
        builder.setView(customLayout_mobile);
        final EditText newMob = (EditText) customLayout_mobile.findViewById(R.id.mobile_jadid_idnew);
        builder.setPositiveButton("ثبت ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mobileSubmitPost(nationalcode , newMob);
            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialogMobile = builder.create();
        dialogMobile.show();
    }
    private void mobileSubmitPost(String national , final EditText jadid_mobile) {
        final RequestQueue queue = Volley.newRequestQueue(context);
        String URL = "http://se7enf98.ddns.net/webservice/p/ChangeMobile.php";

        final ProgressDialog dialog ;
        dialog = new ProgressDialog(context);
        dialog.setMessage("لطفا صبر کنید ..");
        dialog.setCancelable(false);
        dialog.show();

        Hashtable<String , String> params = new Hashtable<>();
        params.put("NationalCode" , national);
        params.put("PhoneNumber" , jadid_mobile.getText().toString());

        JSONObject object = new JSONObject(params);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String result = response.getString("status");
                    if (result.equals("successful")){
                        Toast.makeText(context, jadid_mobile.getText() + " با موفقیت ثبت شد !", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "خطا", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        queue.add(request);
    }

    public void passwordChange(final Context context , LayoutInflater inflater , final String std_code){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCustomTitle(inflater.inflate(R.layout.title_view_changepassword , null));
        final View customView_ChangePass = inflater.inflate(R.layout.alert_changepassword_view,null);
        builder.setView(customView_ChangePass);
        final EditText nowpass = (EditText) customView_ChangePass.findViewById(R.id.nowpass_idnew);
        final EditText newpass = (EditText) customView_ChangePass.findViewById(R.id.newpass_idnew);
        final EditText acceptnewpass = (EditText) customView_ChangePass.findViewById(R.id.acceptnewpass_idnew);
        builder.setPositiveButton("تغییر رمز عبور", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (newpass.getText().toString().equals(acceptnewpass.getText().toString()) && nowpass.getText().length()>=0){
                    changePassPost(nowpass , newpass , std_code);

                }else {
                    Toast.makeText(context, "تکرار رمز عبور جدید را اشتباه وارد کردید !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialogPass = builder.create();
        dialogPass.show();
    }
    private void changePassPost(EditText nowpass, EditText newpass , String std_code) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String URL = "http://se7enf98.ddns.net/webservice/p/ChangeStudentPassword.php";

        final ProgressDialog dialog ;
        dialog = new ProgressDialog(context);
        dialog.setMessage("لطفا صبر کنید ..");
        dialog.setCancelable(false);
        dialog.show();

        Hashtable<String , String> params = new Hashtable<>();
        params.put("StudentCode" , std_code);
        params.put("OldPassword" , nowpass.getText().toString());
        params.put("NewPassword" , newpass.getText().toString());

        JSONObject object = new JSONObject(params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String result = response.getString("status");

                    if (result.equals("Success")){
                        Toast.makeText(context, "رمز عبور با موفقیت تغیرر یافت !", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "خطا !!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "رمز عبور فعلی را اشتباه وارد کردید", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        queue.add(request);
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_tab_id , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
