package Repo;

import Parkeringsplass.Parkeringsplass;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class JSONRepo implements CRUD {


    private ObjectMapper obj =new ObjectMapper();
    private ArrayList<Parkeringsplass> parkeringsplasser = new ArrayList<>();


    public ArrayList<Parkeringsplass> LoadFile(String s){
        try{
           Parkeringsplass[] plasser = obj.readValue(new File(s), Parkeringsplass[].class);

            parkeringsplasser.addAll(Arrays.asList(plasser));

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parkeringsplasser;
    }

    public void WriteToJSON(String fileName, ArrayList<Parkeringsplass> parkeringsplass){
        try{
            File file = new File(fileName);
            obj.registerModule(new JavaTimeModule());
            obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            obj.writerWithDefaultPrettyPrinter().writeValue(file, parkeringsplass);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Parkeringsplass>getObservationsList(){
        return parkeringsplasser;
    }

  /*
    @Override
    public ArrayList<Observation> addObservation(int ID, String name, Animals animals, Location location, String discoveredTime, int amountFound, String pictureUrl, String comment) {
        ArrayList<Observation> observations = getObservationsList();

        observations.add(new Observation(ID, name, animals, location, discoveredTime, amountFound, pictureUrl, comment));

        return observations;
    }

    @Override
    public ArrayList<Observation>  deleteObservation (int ID) {
        ArrayList<Observation> observations = getObservationsList();
        for(int i =0; i < observations.size(); i++){
            if(observations.get(i).getID() == ID){
                observations.remove(i);
            }
        }

        return observations;
    }

    @Override
    public ArrayList<Observation> update(int ID, String name, Animals animals, Location location, String discoveredTime, int amountFound, String pictureUrl, String comment) {

        ArrayList<Observation> observations = getObservationsList();
        for(int i =0; i < observations.size(); i++){
            if(observations.get(i).getID() == ID){
                observations.remove(i);
            }
        }
        Observation observation = new Observation(ID, name, animals, location, discoveredTime, amountFound, pictureUrl, comment);
        observations.add(observation);
        return observations;
    }
*/

}
