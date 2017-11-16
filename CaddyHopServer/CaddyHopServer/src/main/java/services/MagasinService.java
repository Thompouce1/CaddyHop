package services;

import database.MockDatabase;
import entities.Article;
import entities.Magasin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by user on 06/06/16.
 */

@Path("/magasin")
public class MagasinService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public long addMagasin(Magasin magasin) {
        MockDatabase.data.addMagasin(magasin);
        return magasin.getIde();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Magasin> getListMagasin() {
        return MockDatabase.data.getMagasinList();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteMagasin(Magasin magasin){
        MockDatabase.data.deleteMagasin(magasin);
        return "{\"change\":\"success\"}";
    }

    @POST
    @Path("/{idMag}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addArticle(@PathParam("idMag") long id, Article article) {
        MockDatabase.data.addArticleInMag(article,id);
        return article.getCodeBarre();
    }

    @GET
    @Path("/{idMag}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Article> getListArticle(@PathParam("idMag") long id) {
        return MockDatabase.data.getArticlesListInMag(id);
    }

    @PUT
    @Path("/{idMag}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String modifiedArticle(@PathParam("idMag") long id, Article article) {
        MockDatabase.data.modifiedArticleInMag(article,id);
        return "{\"change\":\"success\"}";
    }

    @DELETE
    @Path("/{idMag}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteArticle(@PathParam("idMag") long id, Article article){
        MockDatabase.data.deleteArticleInMag(article, id);
        return "{\"change\":\"success\"}";
    }
}