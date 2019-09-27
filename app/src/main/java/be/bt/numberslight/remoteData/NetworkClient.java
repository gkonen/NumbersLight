package be.bt.numberslight.remoteData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * I choose to write in Java all the logic for requesting data
 *
 * The singleton which will implement Retrofit so we have access the api everywhere
 *
 * As for this project, I use it only inside RepositoryRemoteData, I could implement ServiceAPI in the other Singleton
 * however I prefer keep generality.
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
