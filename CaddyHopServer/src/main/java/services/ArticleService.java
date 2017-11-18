package services;

import database.MockDatabase;
import entities.Article;
import entities.Categorie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by user on 07/06/16.
 */
@Path("/article")
public class ArticleService {

    @GET
    @Path("/{categorie}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Article> getListArticleByCategorie(@PathParam("categorie") String cat) {
        Categorie cate = Categorie.valueOf(cat);
        return MockDatabase.data.selectArticleByCategorie(cate);
    }
}