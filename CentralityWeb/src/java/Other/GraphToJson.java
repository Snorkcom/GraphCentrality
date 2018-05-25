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
import java.util.ArrayList;
import org.apache.jasper.tagplugins.jstl.core.ForEach;


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

        String[] formatVertString;
        ArrayList<String> formatEdgeString;
        
        try {

            file = new File("C:\\Users\\herob\\Desktop\\network.net");

            br = new BufferedReader(new FileReader(file));
            
            // парсинг числа узлов в строке (*Vertices 132)
            String[] splitted1 = br.readLine().split(" ");
            numberOfVertices = Integer.parseInt(splitted1[1]);             
            System.out.println("Graph has "+numberOfVertices+" vertices");
            
            formatVertString = new String[numberOfVertices];
            
            // Чтение вершин
            for(int i = 0; i<numberOfVertices;i++)
            {
                formatVertString[i] = br.readLine(); //.split(" ")
            }
            
            br.readLine(); // Пропустить строку "*Edges"
            
            String str;
            formatEdgeString = new ArrayList<String>();
            while((str = br.readLine())!=null)
            {
                formatEdgeString.add(str);
            }
            
            /*System.out.println("I) formatVertString вывод");
            for(int i=0; i<formatVertString.length;i++)
                System.out.println(formatVertString[i]);
            System.out.println("II) formatEdgeString вывод");
            for(int i=0; i<formatEdgeString.size();i++)
                System.out.println(formatEdgeString.get(i));
            */
            
            
        jsonGraph = "{'nodes':{";
        String[] blabla = new String[2];
        for(int i=0; i<formatVertString.length;i++)
        {
            blabla = formatVertString[i].split(" ", 3);
            jsonGraph += "'"+blabla[0]+":{'color':'red', 'shape':'rect', 'label':'"+blabla[1]+"', 'alpha':1, 'link':'http://www.temis.com/fr'},";         
        }
            
        jsonGraph += 
        // 'edges':{'temis':{'colombia':{'directed':'true', 'color':'#FFA500', 'name':'Headquarter', 'weight':4}, 'paris'    
            
            
            
            
           
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }

        //String[] strings = formatEdgeString.split("\n");
        
            
            
        //
        

    }
}
