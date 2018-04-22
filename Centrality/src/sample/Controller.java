package sample;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.io.PajekNetReader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.commons.collections15.Factory;
import sample.Parts.CreateGraphFromPajek;
import sample.Parts.OpenFile;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Controller {
    public Button button_Check;
    public Label label_Center;




    public void button_Check_Press(ActionEvent actionEvent) {

        // Открытие файла
        OpenFile openFile = new OpenFile();
        // Вывод текста в label_Center
        label_Center.setText(openFile.getTextFromFile());


        // Создание графа из файла Pajek
        Graph<Integer, Integer> g = new CreateGraphFromPajek().LoadPajek(openFile.getPath());
        System.out.println("The graph g = " + g.toString());




        // Map<Integer, Integer[]> hashMapGraph = new HashMap<Integer, Integer[]>(50);
    }
}
