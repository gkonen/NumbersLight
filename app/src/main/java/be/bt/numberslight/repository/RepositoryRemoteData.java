package be.bt.numberslight.repository;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import be.bt.numberslight.model.NumberDetailModel;
import be.bt.numberslight.model.NumberModel;
import be.bt.numberslight.remoteData.NetworkClient;
import be.bt.numberslight.remoteData.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * I choose to write in Java all the logic for requesting data
 *
 * This repository retrieve data from remote source to deliver data to the ViewModel
 */
public class RepositoryRemoteData implements Repository {


    private ServiceAPI api = NetworkClient.getAPI();

    /**
     * The request which retrieve from the server all the number
     *
     * @return ArrayList with all the number received
     */
    @Override
    @SuppressWarnings("unchecked")
    public MutableLiveData<ArrayList<NumberModel>> getAllNumber() {

        final MutableLiveData myListofNumber = new MutableLiveData<ArrayList<NumberModel>>();
        api.getAllNumber().enqueue(new Callback<ArrayList<NumberModel>>() {

            @Override
            public void onResponse(@NotNull Call<ArrayList<NumberModel>> call, @NotNull Response<ArrayList<NumberModel>> response) {
                myListofNumber.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<NumberModel>> call, @NotNull Throwable t) {
                myListofNumber.postValue(null);
                retry(call, this);
            }

            private void retry(final Call<ArrayList<NumberModel>> call, final Callback<ArrayList<NumberModel>> callback) {
                // I choose to repeat indefinitely the request in case of failure to ease the testing
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        call.clone().enqueue(callback);
                    }
                }, 5000); // I pick 5sec to test quickly the reconnection
            }


        });
        return myListofNumber;

    }

    /**
     * The request which retrieve from the server the detail of one number
     *
     * @param name of the number used inside the query to retrieve the data
     * @return NumberDetailModel which contains the data from the server
     */
    @Override
    @SuppressWarnings("unchecked")
    public MutableLiveData<NumberDetailModel> getDetailNumber(String name) {

        final MutableLiveData myNumber = new MutableLiveData<NumberDetailModel>();
        api.getDetailNumber(name).enqueue(new Callback<NumberDetailModel>() {
            @Override
            public void onResponse(@NotNull Call<NumberDetailModel> call, @NotNull Response<NumberDetailModel> response) {
                myNumber.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<NumberDetailModel> call, @NotNull Throwable t) {
                myNumber.setValue(null);
            }
        });
        return myNumber;

    }
}
