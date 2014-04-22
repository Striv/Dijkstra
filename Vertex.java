// Daniel Strivelli
// CMPSC 465 - Section 003
// Vertex Class Implementation

import java.util.*;

// Vertex class
public class Vertex implements Comparable<Vertex>
{
    public final String name;                               // Name of Vertex
    public ArrayList<Edge> adj = new ArrayList<Edge>();     // Adjacency list to hold adjacent edges
    public double vertDistance = Double.POSITIVE_INFINITY;  // Node <Vertex> weight
    public Vertex previous;                                 // Predecessor Node for S.P

    // Constructor
    public Vertex(String argName) 
    { 
        name = argName;
    }

    // String representation for output
    public String toString() 
    {
        return name;
    }

    // Comparison of two Vertices for PriorityQueue
    public int compareTo(Vertex other)
    {
        return Double.compare(vertDistance, other.vertDistance);
    }

    // Comparison for adding Vertices to Vertex Set
    public boolean equals (Vertex other)
    {
        if(other.name.equals(name))
            return true;
        return false;
    }

    // Method to easily add Edge to Vertex adjacency list
    public void addEdge(Vertex target, double weight)
    {
        adj.add(new Edge(target, weight));
    }

}