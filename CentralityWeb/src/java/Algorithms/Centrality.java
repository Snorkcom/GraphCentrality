package Algorithms;


import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.algorithms.scoring.DegreeScorer;
import edu.uci.ics.jung.algorithms.scoring.EigenvectorCentrality;
import edu.uci.ics.jung.algorithms.scoring.HITS;
import edu.uci.ics.jung.algorithms.scoring.PageRank;
import edu.uci.ics.jung.graph.Graph;
import java.util.*;



// Расчет Betweenness Centrality

/**
 *
 * @author Morris
 */
public class Centrality {

    Graph graph; // исследуемый граф
    HashMap<Integer, Double> mapVertexCentr; // HashMap для вершин и их центральностей
    Map<Integer, Double> sortMap; // Сортированный HashMap
    int vertexCount; // количество вершин в графе    

    double forNormalized; // для нормализации значений

    // Конструктор (передаем граф)

    /**
     *
     * @param g
     */
    public Centrality(Graph g) {
        if (g != null) {
            graph = g;
            vertexCount = g.getVertexCount();
            mapVertexCentr = new HashMap<Integer, Double>(vertexCount);
        } else {
            System.out.println("No Graph ERROR");
        }
    }

    // BetweennessCentrality

    /**
     *
     */
    public void CalculateBetweennessCentrality() {

        // Вычисление BetweennessCentrality
        BetweennessCentrality ranker = new BetweennessCentrality(graph);

        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++) {
            mapVertexCentr.put(i, ranker.getVertexScore(i) / 2);
        }

        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = vertexCount * vertexCount; // нормализованное значение

    }

    // ClosenessCentrality

    /**
     *
     */
    public void CalculateClosenessCentrality() {
        // Вычисление ClosenessCentrality
        ClosenessCentrality ranker = new ClosenessCentrality(graph);

        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++) {
            mapVertexCentr.put(i, ranker.getVertexScore(i) / 2);
        }

        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = 1 / vertexCount; // нормализованное значение

    }

    // DegreeCentrality

    /**
     *
     */
    public void CalculateDegreeScorer() {
        // Вычисление DegreeCentrality
        DegreeScorer ranker = new DegreeScorer(graph);

        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++) {
            mapVertexCentr.put(i, (double) ranker.getVertexScore(i));
        }

        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = vertexCount - 1; // нормализованное значение

    }
    
    // EigenvectorCentrality

    /**
     *
     */
    public void CalculateEigenvectorCentrality() {
        
        // Вычисление EigenvectorCentrality
        EigenvectorCentrality ranker = new EigenvectorCentrality(graph);
        //ranker.setMaxIterations(10000);
        ranker.evaluate();
        
        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++) {
            mapVertexCentr.put(i, (double) ranker.getVertexScore(i));
        }

        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = 1; // нормализованное значение       
    }

    // PageRank

    /**
     *
     */
    public void CalculatePageRank() {
                
        // Вычисление PageRankCentrality
        PageRank ranker = new PageRank(graph, 0.15);
        ranker.setMaxIterations(1000);
        ranker.evaluate();
        
        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++)
            mapVertexCentr.put(i, (double) ranker.getVertexScore(i));

        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = 1; // нормализованное значение  
       
    } 
    
    // HITS    

    /**
     *
     */
    public void CalculateHITS() {
           
        // Вычисление HITSCentrality
        HITS ranker = new HITS(graph, 0.15);
        
        //ranker.setMaxIterations(1000);
        ranker.evaluate();        
        String abra ="";
        // Заносим результаты в HashMap
        for (int i = 1; i < vertexCount + 1; i++){
            abra = ranker.getVertexScore(i).toString().replace(',', '.');
            mapVertexCentr.put(i, Double.parseDouble(abra.substring(3, 9)));
        }
        sortMap = sortByValues(mapVertexCentr); // сортируем по убыванию
        forNormalized = 1; // нормализованное значение  
       
    }
    
    
    /*_____________________________________________________________________________________________*/
    

    // Возвращает ArrayList<String[]> с рангами вершин для json маппера

    /**
     *
     * @return
     */
    public ArrayList<String[]> getRanks() {
        ArrayList<String[]> ranksList = new ArrayList<String[]>();      
                
        Set set2 = sortMap.entrySet(); 
        Iterator iterator2 = set2.iterator(); 
        int i = 1;
        while (iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator2.next();
            ranksList.add(new String[]{i+"",me2.getKey()+"",me2.getValue()+""});            
            i++;
        }
        
        return ranksList;
    }

    // Сортировка HashMap'a от наивысшего ранга
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
