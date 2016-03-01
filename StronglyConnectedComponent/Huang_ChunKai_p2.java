import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Huang_ChunKai_p2
{
    public static void main(String[] args) throws IOException
    {
        int V = 1;
        FileReader fr2 = new FileReader("p3_input.txt");
        BufferedReader br2 = new BufferedReader(fr2);
        while (br2.ready())
        {
            br2.readLine();
            V++;
        }
        fr2.close();

        Scanner scan = new Scanner(System.in);
        Huang_ChunKai_p2 SCCL = new Huang_ChunKai_p2();

        List<Integer>[] g = new List[V];
        for (int i = 0; i < V; i++)
            g[i] = new ArrayList<Integer>();
        g[0].add(0);
        int E = 0, AddX, AddY;
        String[] NumArray;
        String EachLine, DataLine;
        FileReader fr = new FileReader("p3_input.txt");
        BufferedReader br = new BufferedReader(fr);
        while (br.ready()) {
            EachLine = br.readLine();
            NumArray = EachLine.split(":");
            AddX = Integer.parseInt(NumArray[0]);
            DataLine = NumArray[1];
            ClearArray(NumArray);
            NumArray = DataLine.split(",");
            for(int i = 0; i<NumArray.length; i++)
            {
                    AddY = Integer.parseInt(NumArray[i]);
                    E++;
                    g[AddX].add(AddY);
            }
        }
        fr.close();

        List<List<Integer>> Compone = SCCL.GetSCC(g);
        System.out.println(PrintResult(Compone));

        FileWriter fw = new FileWriter("p3_out.txt");
        fw.write(PrintResult(Compone));
        fw.flush();
        fw.close();

    }
    public static void ClearArray(String[] NumArray)
    {
           for(int i = 0; i < NumArray.length; i++)
                   NumArray[i] = "";
    }
    public void DFS(List<Integer>[] graph, int v, boolean[] visited, List<Integer> comp)
    {
        visited[v] = true;
        for (int i = 0; i < graph[v].size(); i++)
            if (!visited[graph[v].get(i)])
                DFS(graph, graph[v].get(i), visited, comp);
        comp.add(v);
    }
    public List<Integer> SetOrder(List<Integer>[] graph, boolean[] visited)
    {
        int V = graph.length;
        List<Integer> order = new ArrayList<Integer>();

        for (int i = 0; i < V; i++)
            if (!visited[i])
                DFS(graph, i, visited, order);
        return order;
    }
    public List<Integer>[] Trans(List<Integer>[] graph)
    {
        int V = graph.length;
        List<Integer>[] g = new List[V];
        for (int i = 0; i < V; i++)
            g[i] = new ArrayList<Integer>();
        for (int v = 0; v < V; v++)
            for (int i = 0; i < graph[v].size(); i++)
                g[graph[v].get(i)].add(v);
        return g;
    }
    public static String PrintResult(List<List<Integer>> graph)
    {
        String result = String.valueOf(graph);
        result = result.substring(1, result.length()-6);
        return result;
    }
    public List<List<Integer>> GetSCC(List<Integer>[] graph)
    {
        int V = graph.length;
        boolean[] visited = new boolean[V];
        List<Integer> order = SetOrder(graph, visited);
        List<Integer>[] ReverseG = Trans(graph);
        visited = new boolean[V];
        Collections.reverse(order);

        List<List<Integer>> ReSCC = new ArrayList<>();
        for (int i = 0; i < order.size(); i++)
        {
            int v = order.get(i);
            if (!visited[v])
            {
                List<Integer> comp = new ArrayList<>();
                DFS(ReverseG, v, visited, comp);
                ReSCC.add(comp);
            }
        }
        return ReSCC;
    }
}