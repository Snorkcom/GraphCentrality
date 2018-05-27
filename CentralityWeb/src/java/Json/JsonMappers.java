/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import Other.GraphParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Morris
 */
public class JsonMappers {
        
    // Функция преобразования ArrayList<String[]> в JSON строку
    public static String toJson(ArrayList<String[]> rankList) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(rankList);
        } catch (IOException ex) {
            Logger.getLogger(GraphParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
}
