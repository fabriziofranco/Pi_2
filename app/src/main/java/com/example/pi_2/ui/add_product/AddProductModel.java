package com.example.pi_2.ui.add_product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddProductModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddProductModel() { }

    public LiveData<String> getText() {
        return mText;
    }
}