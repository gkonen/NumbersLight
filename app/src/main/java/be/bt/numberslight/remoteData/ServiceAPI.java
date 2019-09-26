package be.bt.numberslight.remoteData;

import java.util.ArrayList;

import be.bt.numberslight.model.NumberDetailModel;
import be.bt.numberslight.model.NumberModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * I choose to write in Java all the logic for the requests of the server
 * <p>
 * The interface of the request which will be implemented by Retrofit
 */
public interface ServiceAPI {

    @GET("json.php")
    Call<ArrayList<NumberModel>> getAllNumber();

    @GET("json.php")
    Call<NumberDetailModel> getDetailNumber(@Query("name") String name);
}
