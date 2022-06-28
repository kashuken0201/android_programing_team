package com.clowns.foodapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.clowns.foodapp.model.fisebase.Category;
import com.clowns.foodapp.model.fisebase.Other;
import com.clowns.foodapp.model.view.ChoiceItem;
import com.clowns.foodapp.repository.CategoryRepository;
import com.clowns.foodapp.repository.OtherRepository;

import java.util.List;

public class ChoiceItemViewModel extends ViewModel {
    MutableLiveData<List<ChoiceItem>> choiceLiveDataList;
    OtherRepository otherRepository;

    public ChoiceItemViewModel() {
        otherRepository = new OtherRepository();
    }

    public MutableLiveData<List<ChoiceItem>> getLiveChoiceItemData() {
        choiceLiveDataList =  otherRepository.getChoiceItemList();
        return choiceLiveDataList;
    }
}
