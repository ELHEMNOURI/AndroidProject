package com.example.projetandroid.ui.SignOut;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetandroid.R;

public class SignOutFragment extends Fragment {

    private SignOutViewModel signOutViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        signOutViewModel =
                new ViewModelProvider(this).get(SignOutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        signOutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}