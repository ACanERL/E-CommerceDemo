package com.example.aceshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aceshop.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    String email,password;
    int progress=0;
    Handler handler;
    EditText emailtx,passwordtx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
       binding.progressBar.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();
        email=binding.emailog.getText().toString();
        password=binding.passwordlog.getText().toString();


        binding.createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register bottomSheet=new Register();
                bottomSheet.show(getSupportFragmentManager(),"sheet");
            }
        });
      binding.loginbtn.setOnClickListener(view -> {
          email=binding.emailog.getText().toString();
          password=binding.passwordlog.getText().toString();
          internetCheck();
          if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
              Toast.makeText(LoginActivity.this,"Email and password are empty.",Toast.LENGTH_SHORT).show();
          }
          else{
              mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          binding.progressBar.setVisibility(View.VISIBLE);
                          setProgressValue();
                          Toast.makeText(LoginActivity.this,"Login Successful.",Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(LoginActivity.this,ShopMain.class));
                          finish();
                      }else{
                          Toast.makeText(LoginActivity.this,"Login Error.",Toast.LENGTH_SHORT).show();
                      }
                  }
              });
          }
      });
    }
    private void setProgressValue(){
        binding.progressBar.setProgress(progress);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < 100){
                    progress +=10;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.progressBar.setProgress(progress);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void internetCheck(){
        boolean connected=false;
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }else{
            connected=false;
            Toast.makeText(LoginActivity.this,
                    "Internet connection problem",Toast.LENGTH_SHORT).show();
        }
    }
}