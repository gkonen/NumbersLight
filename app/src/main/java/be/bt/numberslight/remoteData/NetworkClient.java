package be.bt.numberslight.remoteData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * I choose to write in Java all the logic for requesting data
 *
 * The singleton permit to implement once the retrofit interface.
 *
 */
public class NetworkClient {

    private static final String BASE_URL = " http://dev.tapptic.com/test/";
    private static ServiceAPI api;
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private NetworkClient() {
        api = retrofit.create(ServiceAPI.class);
    }

    public static ServiceAPI getAPI() {
        if (api == null) {
            new NetworkClient();
        }
        return api;
    }

}
