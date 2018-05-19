package Algorithms;

import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.graph.Graph;
import java.util.*;

// Расчет Betweenness Centrality
public class CalculateBetweennessCentrality {

    HashMap<Integer, Double> mapVertexBetwCentr; // HashMap для вершин и их центральностей
    Map<Integer, Double> sortMap; // Сортированный HashMap
    int forNormalized;

    // Конструктор
    public CalculateBetweennessCentrality(Graph g) {
       
        mapVertexBetwCentr = new HashMap<Integer, Double>(g.getVertexCount());

        BetweennessCentrality ranker = new BetweennessCentrality(g); // Вычисление BetweennessCentrality

        // Заносим результаты в HashMap
        for (int i = 1; i < g.getVertexCount() + 1; i++)
            mapVertexBetwCentr.put(i, ranker.getVertexScore(i) / 2);
                
        sortMap = sortByValues(mapVertexBetwCentr);
        forNormalized = (g.getVertexCount() - 2) * (g.getVertexCount() - 1) / 2;
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
