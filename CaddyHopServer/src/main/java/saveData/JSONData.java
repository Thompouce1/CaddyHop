package saveData;

import java.io.*;
import org.json.*;

/**
 * Created by Thomas on 09/06/2016.
 */

/**
 * Gerer le sauvegarde de donnees en format JSON. Le JSON est sauvegarde dans un format text.
 *
 */
public class JSONData{

    /**
     * Sauvegarder un objet en format JSON text.
     *
     * @param obj	Objet que l'on veut sauvegarder
     * @param file	Le fichier dans lequel on sauvegarde le JSON
     */
    public static void saveJSON (Object obj, File file) {
        System.out.printf("Saving as JSON file...");
        JSONObject jsonObj = new JSONObject();
        String jsonText="";
        String myString="";
        try{
            jsonObj.put("Current session : ", obj);
        } catch(JSONException e){
            e.getCause();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            try{
                JSONTokener tokener = new JSONTokener(jsonObj.toString()); //tokenize the ugly JSON string
                JSONObject finalResult = new JSONObject(tokener); // convert it to JSON object
                jsonText = (finalResult.toString(4)); // To string method prints it with specified indentation.
            }catch(JSONException e){}

            bw.write(jsonText);
            bw.close();

            System.out.println("done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}