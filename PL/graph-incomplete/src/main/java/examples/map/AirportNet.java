package examples.map;

import graph.*;
import graph.map.MapGraph;

import java.util.*;

/**
 *
 * @author DEI-ESINF
 *
 */
public class AirportNet {

    private static class Route {
        public final int passengers;
        public final double miles;

        public Route(int passengers, double miles) {
            this.passengers = passengers;
            this.miles = miles;
        }
    }

    final private Graph<String, Route> airports;

    public AirportNet(){
        airports = new MapGraph<>(true);
    }

    public void addAirport(String a){
        airports.addVertex(a);
    }

    public void addRoute(String a1, String a2, double miles, Integer passengers){
        airports.addEdge(a1,a2,new Route(passengers,miles));
    }

    public int keyAirport(String airport){
        return airports.key(airport);
    }

    public String airportbyKey(int key){
        ArrayList<String> vertices = airports.vertices();
        if(key<0|| key>= vertices.size()){
            return null;
        }
        return vertices.get(key);
    }

    public Integer trafficAirports(String airp1, String airp2){
        Integer npassager =0;
        Edge<String,Route> edge1 = airports.edge(airp1,airp2);
        Edge<String,Route> edge2 = airports.edge(airp2,airp1);
        if(edge1!=null){
            npassager=edge1.getWeight().passengers;
        }
        if(edge2!=null){
            npassager+=edge2.getWeight().passengers;
        }
        return npassager;
    }

    public Double milesAirports(String airp1, String airp2){
        Edge<String,Route> edge1 = airports.edge(airp1,airp2);
        if(edge1!=null){
            return null;
        }
        return edge1.getWeight().miles;
    }

    public Map<String,Integer> nroutesAirport(){
        Map<String,Integer> m = new HashMap<>();
        for (String airport: airports.vertices()){
            //int grau = airports.outDegree(airport)+airport.inDegree(airport);
        }

        return m;
    }

    public List<ArrayList<String>> airpMaxMiles( ){

        List<ArrayList<String>> l = new LinkedList<>();
        Double max = Double.MIN_VALUE;
        for(Edge<String,Route> edge : airports.edges()){
            if(max<=edge.getWeight().miles){

            }


        }


        return l;
    }

    public Boolean connectAirports(String airport1, String airport2){
        if(!airports.validVertex(airport1) || !airports.validVertex(airport2)){
            return false;
        }

        LinkedList<String> qairps = Algorithms.DepthFirstSearch(airports,airport1);
        return qairps.contains(airport2);
    }

    @Override
    public String toString() {
        return airports.toString();
    }
}