package fr.polytechnice.caddyhop.network;

import com.orhanobut.wasp.Callback;
import com.orhanobut.wasp.http.Body;
import com.orhanobut.wasp.http.DELETE;
import com.orhanobut.wasp.http.GET;
import com.orhanobut.wasp.http.POST;
import com.orhanobut.wasp.http.PUT;
import com.orhanobut.wasp.http.Path;

import java.util.List;

import fr.polytechnice.caddyhop.models.Article;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.models.User;
import rx.Observable;

/**
 * Created by shafiq on 01/06/16.
 *
 * New code should not use this service. All API call must be made using RetroFit service.
 *
 * @deprecated use {@link fr.polytechnice.caddyhop.network.CaddyHopRetrofitService} instead
 */
@Deprecated
public interface CaddyHopWaspService {

    String ENDPOINT = "http://162.243.106.178:8080"; // remote
    //String ENDPOINT = "http://10.0.2.2:8080"; // local

    @GET("/users")
    List<User> getUsers();

    @POST("/users")
    void createUser(@Body User user, Callback<Long> callback);

    @GET("/users/{user_id}")
    void getUserInfo(@Path("user_id") long user_id, Callback<User> callback);

    @GET("/users/{user_id}")
    Observable<User> getUserInfoRx(@Path("user_id") long user_id);

    @GET("/magasin/{magasin_id}")
    List<Article> getArticle(@Path("magasin_id") long magasin_id);

    // Gestion de la liste
    @GET("/users/{user_id}/lists/{list_id}")
    ItemList getList(@Path("user_id") long user_id, @Path("list_id") long list_id);

    @POST("/users/{user_id}/lists/{nom}")
    void createList(@Body Object o, @Path("user_id") long user_id, @Path("nom") String nom, Callback<Long> callback);

    @PUT("/users/{user_id}/lists/{list_id}")
    void updateList(@Body ItemList itemList, @Path("user_id") long user_id, @Path("list_id") long list_id, Callback<Object> status);

    @DELETE("/users/{user_id}/lists/{list_id}")
    void deleteList(@Path("user_id") long user_id, @Path("list_id") long list_id, Callback<Object> status);

    // Gestion d'authentification

    @GET("/register/{login}/{mdp}")
    void loginAuth(@Path("login") String login, @Path("mdp") String mdp, Callback<Long> callback);

}
