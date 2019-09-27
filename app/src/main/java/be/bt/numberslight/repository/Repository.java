package be.bt.numberslight.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import be.bt.numberslight.model.NumberDetailModel;
import be.bt.numberslight.model.NumberModel;

/**
 * I choose to write in Java all the logic for requesting data
 * <p>
 * This Interface define what the different Repository must implement
 */
public interface Repository {

    MutableLiveData<ArrayList<NumberModel>> getAllNumber();

    MutableLiveData<NumberDetailModel> getDetailNumber(String name);

}
