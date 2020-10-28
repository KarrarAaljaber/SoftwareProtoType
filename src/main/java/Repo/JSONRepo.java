package Repo;

import KontoInformasjon.Konto;
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
import Parkeringsplass.Bestilling;

public class JSONRepo implements CRUD {


    private ObjectMapper obj =new ObjectMapper();
    private ArrayList<Parkeringsplass> parkeringsplasser = new ArrayList<>();
    private ArrayList<Konto> kontoer = new ArrayList<>();
    private ArrayList<Bestilling> bestillinger = new ArrayList<>();


    public ArrayList<Konto> LoadFileBestillinger(String s){
        try{
            Bestilling[] bes = obj.readValue(new File(s), Bestilling[].class);

            bestillinger.addAll(Arrays.asList(bes));

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kontoer;
    }
    public ArrayList<Konto> LoadFileKonto(String s){
        try{
            Konto[] kontos = obj.readValue(new File(s), Konto[].class);

            kontoer.addAll(Arrays.asList(kontos));

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kontoer;
    }
    public void WriteToJSONKonto(String fileName, ArrayList<Konto> konto){
        try{
            File file = new File(fileName);
            obj.registerModule(new JavaTimeModule());
            obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            obj.writerWithDefaultPrettyPrinter().writeValue(file, konto);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteToJSONBestilling(String fileName, ArrayList<Bestilling> bestillinger){
        try{
            File file = new File(fileName);
            obj.registerModule(new JavaTimeModule());
            obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            obj.writerWithDefaultPrettyPrinter().writeValue(file, bestillinger);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public ArrayList<Konto> getKontoer(){return kontoer;}
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
