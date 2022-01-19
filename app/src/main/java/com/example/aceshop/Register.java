package com.example.aceshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aceshop.databinding.RegisterLayoutBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends BottomSheetDialogFragment {
    Button complate;
    EditText nameCreate,emailCreate,passwordCreate,corfimPassword;
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register_layout,container,false);
        mAuth = FirebaseAuth.getInstance();

        nameCreate=view.findViewById(R.id.nametxt);
        passwordCreate=view.findViewById(R.id.passwordtxt);
        emailCreate=view.findViewById(R.id.emailtxt);
        complate=view.findViewById(R.id.registerbtn);
        corfimPassword=view.findViewById(R.id.confirmpasstxt);

        complate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAcc();
            }
        });
        return view;
    }

    private  void createAcc(){
        String email=emailCreate.getText().toString();
        String password=passwordCreate.getText().toString();
        String confirmPass=corfimPassword.getText().toString();

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            emailCreate.setError("Required*");
            passwordCreate.setError("Required*");
            Toast.makeText(getContext(),"Email and Password Empty",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPass)){
            Toast.makeText(getContext(),"Password does not match.",Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getContext(),"User registered complate.",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(),ShopMain.class));
                    }else{
                        Toast.makeText(getContext(),"Registered Error.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
