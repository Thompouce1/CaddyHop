package services;

import database.MockDatabase;
import entities.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by user on 31/05/16.
 */
@Path("/users")
public class UserService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public long addUser(Utilisateur user) {
        MockDatabase.data.addUser(user);
        return user.getId();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Utilisateur> getListUser() {
        return MockDatabase.data.getUsersList();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(Utilisateur user){
        MockDatabase.data.deleteUser(user);
        return "{\"change\":\"success\"}";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur getUser(@PathParam("id") long id) {
        return MockDatabase.data.getUser(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String modifiedUser(@PathParam("id") long id, Utilisateur uti) {
        MockDatabase.data.modifiedUser(uti, id);
        return "{\"change\":\"success\"}";
    }

    @GET
    @Path("/{id}/fidelite")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Fidelite> getFidelite(@PathParam("id") long id) {
        return MockDatabase.data.getUser(id).getListCarteFidel();
    }

    @POST
    @Path("/{id}/fidelite/{nomMag}/{codeFidel}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addCarteFidel(@PathParam("id") long id, @PathParam("nomMag") String nomMag, @PathParam("codeFidel") String codeFidel) {
        Fidelite fi = new Fidelite(nomMag,codeFidel);
        getUser(id).getListCarteFidel().add(fi);
        return "{\"change\":\"success\"}";
    }

    @DELETE
    @Path("/{id}/fidelite/{codeFidel}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delCarteFidel(@PathParam("id") long id, @PathParam("codeFidel") String codeFidel) {
        for (Fidelite fi: getUser(id).getListCarteFidel()) {
            if(fi.getCodeFidel().equals(codeFidel)){
                getUser(id).getListCarteFidel().remove(fi);
            }
        }
        return "{\"change\":\"success\"}";
    }

    @GET
    @Path("/{id}/resultat/{listId}/{latitude}/{longitude}/{distMax}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Resultat> getResultat(@PathParam("id") long id, @PathParam("listId") long listId, @PathParam("latitude") double latitude, @PathParam("longitude") double longitude, @PathParam("distMax") int distMax) {
        return MockDatabase.data.resultat(id,listId,latitude,longitude,distMax);
    }

    @GET
    @Path("/{id}/lists")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ItemList> getListsList(@PathParam("id") long id) {
        return getUser(id).getItemListList();
    }


    @POST
    @Path("/{id}/lists/{nom}")
    @Consumes(MediaType.APPLICATION_JSON)
    public long addList(@PathParam("id") long id, @PathParam("nom") String nom) {
        Utilisateur user = getUser(id);
        long listeId = user.creerListe(nom);
        return listeId;
    }

    @GET
    @Path("/{id}/lists/{listId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemList getList(@PathParam("id") long id, @PathParam("listId") long listId) {
        return getUser(id).getList(listId);
    }

    @PUT
    @Path("/{id}/lists/{listId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String setList(@PathParam("id") long id, @PathParam("listId") long listId, ItemList item) {
        getUser(id).setList(listId,item);
        return "{\"change\":\"success\"}";
    }

    @DELETE
    @Path("/{id}/lists/{listId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delList(@PathParam("id") long id, @PathParam("listId") long listId) {
        getUser(id).delList(listId);
        return "{\"change\":\"success\"}";
    }

    @POST
    @Path("/{id}/lists/{listId}/items")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addItemInList(@PathParam("id") long id, @PathParam("listId") long listId, Article art) {
        getUser(id).getList(listId).addArticle(art);
        return "{\"change\":\"success\"}";
    }

    @PUT
    @Path("/{id}/lists/{listId}/items")
    @Consumes(MediaType.APPLICATION_JSON)
    public String modifiedItemInList(@PathParam("id") long id, @PathParam("listId") long listId, Article art) {
        getUser(id).getList(listId).modifiedArticle(art);
        return "{\"change\":\"success\"}";
    }

    @DELETE
    @Path("/{id}/lists/{listId}/items")
    @Consumes(MediaType.APPLICATION_JSON)
    public String delItemInList(@PathParam("id") long id, @PathParam("listId") long listId, Article art) {
        getUser(id).getList(listId).delArticle(art);
        return "{\"change\":\"success\"}";
    }
}
