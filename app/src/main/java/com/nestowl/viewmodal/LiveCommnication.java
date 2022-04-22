package com.nestowl.viewmodal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nestowl.model.aichat;

public class LiveCommnication extends ViewModel {
    private MutableLiveData<aichat> text = new MediatorLiveData<>();



    public void setText(aichat aichat){
        text.setValue(aichat);
    }
    public LiveData<aichat> getText(){
        return text;
    }


}
