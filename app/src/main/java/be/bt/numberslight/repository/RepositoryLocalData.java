package be.bt.numberslight.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import be.bt.numberslight.localData.LocalData;
import be.bt.numberslight.model.NumberDetailModel;
import be.bt.numberslight.model.NumberModel;

/**
 * I choose to write in Java all the logic for requesting data
 * <p>
 * This repository retrieve data from local source to deliver data to the ViewModel
 */
public class RepositoryLocalData implements Repository {

    private LocalData data = new LocalData();

    /**
     * Retrieve local data
     *
     * @return ArrayList with all data hardcoded
     */
    @Override
    @SuppressWarnings("unchecked")
    public MutableLiveData<ArrayList<NumberModel>> getAllNumber() {
        MutableLiveData myList = new MutableLiveData<ArrayList<NumberModel>>();
        myList.setValue(data.getMyList());
        return myList;
    }

    /**
     * Retrieve detail from a specific name
     *
     * @param name of the number I want the detail
     * @return NumberDetailModel which contains the information I want display
     */
    @Override
    @SuppressWarnings("unchecked")
    public MutableLiveData<NumberDetailModel> getDetailNumber(String name) {
        MutableLiveData myNumber = new MutableLiveData<NumberDetailModel>();
        myNumber.setValue(data.getMyNumber(name));
        return myNumber;
    }
}
