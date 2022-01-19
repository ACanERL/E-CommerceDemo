package com.example.aceshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.aceshop.databinding.ActivityShopMainBinding;
import com.example.aceshop.fragment.CampaignFragment;
import com.example.aceshop.fragment.MainFragment;
import com.example.aceshop.fragment.UserFragment;

public class ShopMain extends AppCompatActivity {
    private ActivityShopMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_shop_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new MainFragment()).commit();

        binding.bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.mainFragment:
                    selectedFragment=new MainFragment();
                    break;
                case R.id.campaignFragment:
                    selectedFragment=new CampaignFragment();
                    break;
                case R.id.userFragment:
                    selectedFragment=new UserFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment).commit();
            return true;
        });
    }

}