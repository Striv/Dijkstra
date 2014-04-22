// Daniel Strivelli
// CMPSC 465 - Section 003
// Edge Class Implemenataion

public class Edge
{
    public Vertex destination;          // Target Node <Vertex>
    public double weight;               // Weight of Edge

    // Constructor
    public Edge(Vertex target, double myWeight) 
    { 
        destination = target; 
        weight = myWeight; 
    }
}