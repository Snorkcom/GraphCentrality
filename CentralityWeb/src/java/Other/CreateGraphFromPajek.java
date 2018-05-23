package Other;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.io.PajekNetReader;
import org.apache.commons.collections15.Factory;

import java.io.IOException;

public class CreateGraphFromPajek {

    // фабрика для создания узлов
    Factory<Integer> vertexFactory = new Factory<Integer>() {
        int i=1;
        public Integer create() {return  new Integer(i++);}
    };
    // фабрика для создания ребер
    Factory<Integer> edgeFactory = new Factory<Integer>() {
        int i=1;
        public Integer create() {return  new Integer(i++);}
    };

    // Граф
    Graph<Integer, Integer> graph = new SparseMultigraph<Integer, Integer>();
    // PajekReader
    PajekNetReader pajekNetReader = new PajekNetReader(vertexFactory, edgeFactory);

    // Загрузка графа из файла Pajek
    public Graph LoadPajek(String fileAddress) {
        try {            
            pajekNetReader.load(fileAddress, graph);
        } catch (IOException e) {
            System.out.println("CreateGraphFromPajek.LoadPajek() error");
            e.printStackTrace();
        }

        return graph;
    }

}
