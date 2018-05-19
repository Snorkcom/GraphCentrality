package Algorithms;

import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.algorithms.scoring.DegreeScorer;
import edu.uci.ics.jung.graph.Graph;
import java.util.*;

// Расчет Betweenness Centrality
public class Сentrality {

    Graph graph; // исследуемый граф
    HashMap<Integer, Double> mapVertexCentr; // HashMap для вершин и их центральностей
    Map<Integer, Double> sortMap; // Сортированный HashMap
    int vertexCount; // количество вершин в графе

    int forNormalized; // значение для нормализации значений

    // Конструктор (передаем граф)
    public Сentrality(Graph g) {
        if (g != null) {
            graph = g;
            vertexCount = g.getVertexCount();
            mapVertexCentr = new HashMap<Integer, Double>(vertexCount);
        } else{
            System.out.println("No Graph ERROR");
        }
    }

    // BetweennessCentrality
    public void CalculateBetweennessCentrality() {
        
        // Вычисление BetweennessCentrality
        BetweennessCentrality ranker = new BetweennessCentrality(graph);
        
        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++) {
            mapVertexCentr.put(i, ranker.getVertexScore(i) / 2);
            System.out.println(i + "  :  " + ranker.getVertexScore(i) / 2);
        }

        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = (graph.getVertexCount() - 2) * (graph.getVertexCount() - 1) / 2; // нормализованное значение

    }

    // ClosenessCentrality
    public void CalculateClosenessCentrality() {
        // Вычисление ClosenessCentrality
        ClosenessCentrality ranker = new ClosenessCentrality(graph);

        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++) {
            mapVertexCentr.put(i, ranker.getVertexScore(i) / 2);
        }

        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = (graph.getVertexCount() - 1); // нормализованное значение

    }

    // DegreeCentrality
    public void CalculateDegreeScorer() {
        // Вычисление DegreeCentrality
        DegreeScorer ranker = new DegreeScorer(graph);

        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++) {
            mapVertexCentr.put(i, (double) ranker.getVertexScore(i) / 2);
        }

        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = (graph.getVertexCount() - 1); // нормализованное значение

    }

    // Вернуть Map<Integer, String>
    public Map<Integer, Double> map() {
        return sortMap;
    }

    // Переопределение ToString()
    @Override
    public String toString() {

        Set set2 = sortMap.entrySet();
        Iterator iterator2 = set2.iterator();
        String out = "";
        int i = 1;
        while (iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator2.next();
            out += "Rank: " + i + " Vertex id: " + me2.getKey() + " Value: " + me2.getValue() + " NormValue: " + (Double) me2.getValue() / forNormalized + "\n";
            i++;
        }
        return out;
    }

    // Сортировка от наивысшего ранга
    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
