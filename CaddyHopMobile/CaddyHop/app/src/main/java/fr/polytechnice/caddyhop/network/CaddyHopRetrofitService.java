package fr.polytechnice.caddyhop.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.polytechnice.caddyhop.models.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import retrofit2.http.*;
import rx.Observable;

/**
 * Created by shafiq on 13/06/16.
 *
 * All API call using the CaddyHop service should be declared here.
 */

public interface CaddyHopRetrofitService {
    String ENDPOINT = "http://162.243.106.178:8080"; // remote

    @GET("/users/{user_id}")
    Observable<User> getUserInfo(@Path("user_id") long user_id);

    // Gestion d'authentification
    @GET("/register/{login}/{mdp}")
    Observable<Long> loginAuth(@Path("login") String login, @Path("mdp") String mdp);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static CaddyHopRetrofitService newCHService() {
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CaddyHopRetrofitService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(CaddyHopRetrofitService.class);
        }
    }
}
