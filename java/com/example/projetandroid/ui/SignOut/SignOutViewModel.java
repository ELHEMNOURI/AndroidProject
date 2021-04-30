package com.example.projetandroid.ui.SignOut;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignOutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SignOutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}