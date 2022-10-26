// Both the Graph class and the dijkstras shortest path algorithm is an altered version
// of the implementation found here: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/.

#include <list>
#include <queue>
#include <fstream>
#include <iostream>

#define INF 100000000000

typedef std::pair<int, int> intPair;

// Directed graph
class Graph {
    int vertices;
    std::list<std::pair<int, int> >* adjacents;

public:
    explicit Graph(int V);
    void addEdge(int from, int to, int weight);
    void dijkstra(int source);
};

Graph::Graph(int V) {
    this->vertices = V;
    adjacents = new std::list<intPair>[V];
}

// Adds edge to graph.
// Uncommenting the top line lets you use this graph as if it was undirected.
void Graph::addEdge(int from, int to, int weight)
{
    // adjacents[to].emplace_back(from, weight);
    adjacents[from].emplace_back(to, weight);
}

// Prints the shortest paths from source to all other vertices
void Graph::dijkstra(int source)
{
    std::priority_queue<intPair, std::vector<intPair>, std::greater<> >
            priorityQueue;

    // Create a vector for distances and initialize all distances as infinite (INF)
    std::vector<int> distance(vertices, INF);

    // Insert source itself in priorityQueue queue and initialize its distance as 0.
    priorityQueue.push(std::make_pair(0, source));
    distance[source] = 0;

    while (!priorityQueue.empty()) {
        int destinationVertice = priorityQueue.top().second;
        priorityQueue.pop();

        std::list<std::pair<int, int> >::iterator i;
        for (i = adjacents[destinationVertice].begin(); i != adjacents[destinationVertice].end(); ++i) {
            // Get vertex label and weight of current
            // adjacent of destinationVertice.
            int currentVertice = (*i).first;
            int weight = (*i).second;

            // If there is shorted path to currentVertice through destinationVertice.
            if (distance[currentVertice] > distance[destinationVertice] + weight) {
                // Updating distance of currentVertice
                distance[currentVertice] = distance[destinationVertice] + weight;
                priorityQueue.push(std::make_pair(distance[currentVertice], currentVertice));
            }
        }
    }

    // Print shortest distances stored in distance[]
    printf("From Vertice   To Vertice     Distance\n");
    for (int i = 0; i < vertices; ++i)
        if (distance[i] > 1000000000) {
            printf("    %d                          No path.\n", i);
        } else
        printf("    %d              %d              %d\n", source, i, distance[i]);
}

Graph importGraph(const std::string& name) {
    std::string line;
    std::ifstream myFile (name);

    int vertices, edges;

    if (myFile.is_open()) {

        // Adds the first line (n of vertices and edges).
        myFile >> vertices >> edges;

        // Creates the graph with amount of vertices.
        Graph graph(vertices);

        while (getline(myFile, line)) {
            int from, to, weight;

            // Adds all edges with their destination and weight.
            while (myFile >> from >> to >> weight)
            {
                graph.addEdge(from, to, weight);
            }
        }
        myFile.close();

        return graph;
    } else {
        throw (-1);
    }
}

int main()
{
    try {
        auto graph = importGraph("/Users/kristoffersvedal/Documents/algorithms/dijkstra/vg1");
        graph.dijkstra(1);
    } catch (int) {
        std::cout << "Could not read file." << std::endl;
    }

    return 0;
}