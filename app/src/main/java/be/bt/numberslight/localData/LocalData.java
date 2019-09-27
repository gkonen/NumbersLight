package be.bt.numberslight.localData;

import java.util.ArrayList;

import be.bt.numberslight.model.NumberDetailModel;
import be.bt.numberslight.model.NumberModel;

/**
 * I define here hardcoded data to test without requesting the server
 */
public class LocalData {

    private ArrayList<NumberModel> myList;

    public LocalData() {
        int size = 20;
        this.myList = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            myList.add(new NumberModel(String.valueOf(i), ""));
        }
    }

    public ArrayList<NumberModel> getMyList() {
        return myList;
    }

    public NumberDetailModel getMyNumber(String name) {
        return new NumberDetailModel(name, name, "");
    }


}
