// Daniel Strivelli
// CMPSC 465 - Section 003
// Dijkstra's Algorithm Implementation

import java.util.*;
import java.io.*;

// Dijkstra Class definition
// Algorithm to find shortest path in a weighted, directed graph.
public class Dijkstra
{
    // Vertex set which will hold inputted vertices
    private static ArrayList<Vertex> vSet = new ArrayList<Vertex>();

    // Method to run Dijkstra's Algorithm from source vertex
    public static void computePaths(Vertex source)
    {
        // Set source vertex distance to 0.
        // Add it to priority queue
        source.vertDistance = 0.0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        // Iterate until queue of vertices is empty rendering graph fully mapped
        while (!vertexQueue.isEmpty()) 
        {
            // Get minimum weight vertex
            Vertex u = vertexQueue.poll();

            // For every outgoing edge (in adjacency list)
            for (Edge e : u.adj)
            {
                // Check vertex weight against adjacent edge weight
                // and current vertex weight
                // update if necessary
                Vertex v = e.destination;
                double distToU = u.vertDistance + e.weight;
                if (distToU < v.vertDistance) 
                {
                    // Update min and add next node to queue
                    // set predecessor node
                    vertexQueue.remove(v);
                    v.vertDistance = distToU;
                    v.previous = u;
                    vertexQueue.add(v);
                }    
            }
        }
    }

    // Method to get shortest path to some node after algorithm has been run
    // Returns List of vertices that represent the path from source node to target
    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        // Iterate through predecessor nodes and add to shortest path
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        // Since we iterated back through predecessors
        // we reverse path away from source node
        Collections.reverse(path);
        return path;
    }

    // Method to add new vertex to vertex set
    // If vertex in set, return index
    // If vertex not in set, add it, return index
    public static int addVertex(Vertex toAdd)
    {
        for(int a = 0; a < vSet.size(); a++)
        {
            if(vSet.get(a).equals(toAdd))
                return a;
        }
        vSet.add(toAdd);
        return vSet.size()-1;
    }

    // Main Driver for Algorithm
    public static void main(String[] args)
    {
        Scanner in;
        Vertex source;
        String fileName;
        long m1 = System.currentTimeMillis();
        if(args.length == 0)
            fileName = "input.txt";
        else
            fileName = args[0];
        try // Use Try-Catch for FileReading errors
        {
            // Open file and read first int for source node
            in = new Scanner (new FileReader("input.txt"));
            source = new Vertex("" + in.nextInt());    
            in.nextLine(); // advance scanner to next line
            while(in.hasNextLine())
            {
                // Regex expression splits line at spaces and tabs
                String [] vals = in.nextLine().split("\\s+|\\t+");
                Vertex a = new Vertex("" + vals[0]);
                Vertex b = new Vertex("" + vals[1]);
                double weight = Integer.parseInt(vals[2]);
                // Vertices are added to vertex set
                int aDex = addVertex(a); 
                int bDex = addVertex(b);
                // New Edge is added to source adjacency list
                vSet.get(aDex).addEdge(vSet.get(bDex), weight);
            }
        }
        catch (Exception e)
        {
            return;
        }   

        // Compute paths from source node (first node in vSet)
        int sourceDex = addVertex(source);
        computePaths(vSet.get(sourceDex));

        // Print Output
        for (Vertex v : vSet)
        {
            // Check to verify that node has accessible path
            if(v.vertDistance == Double.POSITIVE_INFINITY)
                System.out.println("Node " + v + " cannot be reached by node " + vSet.get(sourceDex) + "!");
            else
            {
                System.out.println("From " + vSet.get(sourceDex) + " to " + v + ":");
                System.out.print("The shortest path is: ");
                List<Vertex> path = getShortestPathTo(v);
                for(int j = 0; j < path.size()-1; j++)
                    System.out.print(path.get(j) + " -> ");
                System.out.println(path.get(path.size() - 1) + ";");
                System.out.printf("The distance is: %.0f\n", v.vertDistance);
            }
        }
        long m2 = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (int)(m2 - m1));
    }
}