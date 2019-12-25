package com.example.portal_daneshjoii.activities;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class SplashScreean extends AppCompatActivity {

    private TextView txt_portal_name , txt_tnb , txt_forget_pass;
    private ProgressBar progressBar;
    private RelativeLayout after_animation_layout;
    private ImageView img_logo;
    private Button btn_login;
    private TextInputEditText stdCode , stdPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screean);
        initViews();

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                txt_portal_name.setVisibility(View.GONE);
                txt_tnb.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                img_logo.getLayoutParams().height = 250;
                img_logo.getLayoutParams().width = 300;
                startanimation();

            }
        }.start();

        btn_login.setOnClickListener(loginClickListener);
    }

    private Button.OnClickListener loginClickListener
            = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(SplashScreean.this , MainActivity.class));
            //LoginStudent();
        }
    };

    private void LoginStudent() {
        RequestQueue queue = Volley.newRequestQueue(SplashScreean.this);
        String URL = "http://se7enf98.ddns.net/webservice/p/StudentLogin.php";
        Hashtable<String , String> params = new Hashtable<>();
        params.put("StudentCode" , stdCode.getText().toString());
        params.put("Password" , stdPass.getText().toString());

        final ProgressDialog dialog ;
        dialog = new ProgressDialog(SplashScreean.this);
        dialog.setMessage("لطفا صبر کنید ..");
        dialog.setCancelable(false);
        dialog.show();

        JSONObject object = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String success = response.getString("error");
                    String fullname = response.getString("FullName");
                    String stdCode = response.getString("StudentCode");
                    String ProfilePic = response.getString("ProfilePic");
                    String nationalnumber = response.getString("NationalCode");

                    String preimg = "http://se7enf98.ddns.net/webservice/p/";
                    String posimg = preimg.concat(ProfilePic);

                    SharedPreferences.Editor editor = getSharedPreferences("login" , Context.MODE_PRIVATE).edit();
                    editor.putString("FullName" , fullname);
                    editor.putString("StudentCode" , stdCode);
                    editor.putString("ProfilePic" , posimg);
                    editor.putString("NationalCode" , nationalnumber);
                    editor.apply();

                    dialog.dismiss();
                    if (success.equals("0")){
                        startActivity(new Intent(SplashScreean.this , MainActivity.class));
                    }else {
                        Toast.makeText(SplashScreean.this, "شماره دانشجویی یا رمز عبور شما اشتباه است !", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(SplashScreean.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void startanimation() {
        ViewPropertyAnimator viewPropertyAnimator = img_logo.animate();
        viewPropertyAnimator.x(30f);
        viewPropertyAnimator.y(100f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                after_animation_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void initViews() {
        txt_portal_name = (TextView) findViewById(R.id.txt_portal_splash_id);
        txt_tnb = (TextView) findViewById(R.id.txt_tnb_id);
        progressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);
        after_animation_layout = (RelativeLayout) findViewById(R.id.afterAnimationView);
        img_logo = (ImageView) findViewById(R.id.img_logo_splash_id);
        btn_login = (Button) findViewById(R.id.btn_login_id);
        txt_forget_pass = (TextView) findViewById(R.id.btn_forget_pass_id);
        stdCode = (TextInputEditText) findViewById(R.id.student_code_id);
        stdPass = (TextInputEditText) findViewById(R.id.student_password_id);
    }
}
