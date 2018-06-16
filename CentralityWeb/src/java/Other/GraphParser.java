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


/**
 *
 * @author Morris
 */
public class GraphParser {

    int numberOfVertices; // количество вершин графа(в .net файле на 1 строке: "*Vertices 1419")
    File file = null; // для открытия файла
    
    ArrayList<String[]> formatVertString = null; // Лист вершин графа <String массива>: [1] номер узла, [2] название, [1] др. информация
    ArrayList<String[]> formatEdgeString = null; // Лист ребер графа <String массива>: [1] исход узел, [2] конечн узел, [1] др. информация

    // Конструктор открывает сохраненный файл и парсит его на formatVertString и formatEdgeString 

    /**
     *
     * @throws IOException
     */
    public GraphParser() throws IOException {

        String[] splitStrings; // массив разделенных строк
        BufferedReader br = null; // для чтения файла

        try {

            file = new File("network.net");

            br = new BufferedReader(new FileReader(file));

            // парсинг числа узлов в строке (*Vertices 132)
            String[] splitted1 = br.readLine().split(" ");
            numberOfVertices = Integer.parseInt(splitted1[1]); // получение количества вершин
            System.out.println("Graph has " + numberOfVertices + " vertices");

            // Часть I: Парсинг вершин
            // Пример строки для парсинга: 9 "Obesity" 47.382732 -109.748215 0.0
            formatVertString = new ArrayList<String[]>();

            for (int i = 0; i < numberOfVertices; i++) {
                splitStrings = br.readLine().split("\"", 3); // разделения по ковычкам "                 
                splitStrings[0] = splitStrings[0].replace(" ", ""); // убирает пробел в 1 элементе
                
                if(splitStrings[2].length() > 1)
                splitStrings[2] = splitStrings[2].substring(1); // убирает начальный пробел
                
                formatVertString.add(splitStrings);                
            }

            // Часть II: Парсинг ребер
            // Пример строки для парсинга: 2 4 1.0
            br.readLine(); // Пропустить строку "*Edges"
            formatEdgeString = new ArrayList<String[]>();

            String str;
            while ((str = br.readLine()) != null) {
                splitStrings = str.split(" ", 3); // разделения по пробелам                               
                formatEdgeString.add(splitStrings);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }

    }

    // Возвращает ArrayList<String[]> вершин

    /**
     *
     * @return
     */
    public ArrayList<String[]> VerticesJson() {
        return formatVertString;
    }

    // Возвращает ArrayList<String[]> ребер

    /**
     *
     * @return
     */
    public ArrayList<String[]> EdgesJson() {
        return formatEdgeString;
    }

}
