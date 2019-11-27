package com.kepss.expenseentry.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.kepss.expenseentry.R;
import com.kepss.expenseentry.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding fragAddItem = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        try {
            HomeViewModel homeViewModel = new HomeViewModel();
            fragAddItem.setViewModel(homeViewModel );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragAddItem.getRoot();
    }
}