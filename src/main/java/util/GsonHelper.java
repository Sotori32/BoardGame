package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Class that helps with writhing to json and extracting entities from it.
 */
@Slf4j
public class GsonHelper {

    /**
     * Appends a {@link GameResult} to the Json file.
     * @param gameResult the {@link GameResult} that has to be appended
     */
    public static  void appendToGson(GameResult gameResult){
        try{
            ArrayList<GameResult> gameResultList = deserializeGson();
            gameResultList.add(gameResult);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("data.json");
            gson.toJson(gameResultList, writer);
            writer.close();
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * Extracts the {@link GameResult}s from the json file.
     * @return an Arraylist containing the {@link GameResult}
     */
    public static ArrayList<GameResult> deserializeGson(){
        Gson gson = new Gson();

        try{
            FileReader reader = new FileReader("data.json");
            Type founderListType = new TypeToken<ArrayList<GameResult>>(){}.getType();
            ArrayList<GameResult> gameResultList = gson.fromJson(reader, founderListType);
            return gameResultList;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }
}
