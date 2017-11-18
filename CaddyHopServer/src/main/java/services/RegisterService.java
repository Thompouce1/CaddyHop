package services;

import database.MockDatabase;
import entities.Utilisateur;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by user on 03/06/16.
 */
@Path("/register")
public class RegisterService {

    @GET
    @Path("/{login}/{mdp}")
    @Produces(MediaType.APPLICATION_JSON)
    public long authentification(@PathParam("login") String login, @PathParam("mdp") String mdp) {
        for(Utilisateur uti: MockDatabase.data.getUsersList()){
            if(login.equals(uti.getLogin())){
                if(mdp.equals(uti.getMdp())){
                    return uti.getId();
                }
            }
        }
        return -1;
    }
}
