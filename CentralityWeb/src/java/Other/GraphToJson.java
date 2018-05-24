/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Morris
 */
public class GraphToJson {

    String jsonGraph = "";
    int numberOfVertices;
    File file = null;
    BufferedReader br;
    
    public GraphToJson() throws IOException {

        String formatString = "";
        
        try {

            file = new File("C:\\Users\\herob\\Desktop\\network.net");

            br = new BufferedReader(new FileReader(file));
            
            // парсинг числа узлов в строке (*Vertices 132)
            String[] splitted1 = br.readLine().split(" ");
            numberOfVertices = Integer.parseInt(splitted1[1]);             
            System.out.println("Graph has "+numberOfVertices+" vertices");
            
            // Чтение вершин
            for(int i = 0; i<numberOfVertices;i++)
            {
                formatString += br.readLine() +"\n";
            }
            System.out.println("________________________________________________");
            System.out.println(formatString);
           
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }

        //String[] splitted2 = formatString.split("\\*Edges");
        //System.out.println(splitted2.length);
        /*System.out.println(splitted2[0]);
        System.out.println("__________________________________________________");
        System.out.println(splitted2[1]);*/

    }
}
