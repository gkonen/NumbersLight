package be.bt.numberslight.repository;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import be.bt.numberslight.model.NumberDetailModel;
import be.bt.numberslight.model.NumberModel;
import be.bt.numberslight.remoteData.NetworkClient;
import be.bt.numberslight.remoteData.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * I choose to write in Java all the logic for the requests of the server
 * <p>
 * This singleton keep all the request made for the project and the viewModel will use it
 */
public class Repository {

    private static Repository repository;
    private ServiceAPI api = NetworkClient.getAPI();

    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    /**
     * The request which retrieve from the server all the number
     *
     * @return ArrayList with all the number received
     */
    @SuppressWarnings("unchecked")
    public MutableLiveData<ArrayList<NumberModel>> getAllNumber() {

        final MutableLiveData myListofNumber = new MutableLiveData<ArrayList<NumberModel>>();
        api.getAllNumber().enqueue(new Callback<ArrayList<NumberModel>>() {

            @Override
            public void onResponse(Call<ArrayList<NumberModel>> call, Response<ArrayList<NumberModel>> response) {
                myListofNumber.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<NumberModel>> call, Throwable t) {
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
    @SuppressWarnings("unchecked")
    public MutableLiveData<NumberDetailModel> getDetailNumber(String name) {

        final MutableLiveData myNumber = new MutableLiveData<NumberDetailModel>();
        api.getDetailNumber(name).enqueue(new Callback<NumberDetailModel>() {
            @Override
            public void onResponse(Call<NumberDetailModel> call, Response<NumberDetailModel> response) {
                myNumber.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NumberDetailModel> call, Throwable t) {
                myNumber.setValue(null);
            }
        });
        return myNumber;

    }
}
