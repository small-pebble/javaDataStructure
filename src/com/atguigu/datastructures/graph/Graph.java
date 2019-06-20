package com.atguigu.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Auther:smallPebble
 * @Date:2019/6/19
 * @Description:com.atguigu.datastructures.graph
 * @version:1.0
 **/
public class Graph {

    public static void main(String[] args) {
        //测试一把图是否创建ok
        int n = 5;  //结点的个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        //String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex : Vertexs) {
            graph.addVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
        graph.addEdges(0, 1, 1); // A-B
        graph.addEdges(0, 2, 1); //
        graph.addEdges(1, 2, 1); //
        graph.addEdges(1, 3, 1); //
        graph.addEdges(1, 4, 1); //

        //更新边的关系
        /*graph.addEdges(0, 1, 1);
        graph.addEdges(0, 2, 1);
        graph.addEdges(1, 3, 1);
        graph.addEdges(1, 4, 1);
        graph.addEdges(3, 7, 1);
        graph.addEdges(4, 7, 1);
        graph.addEdges(2, 5, 1);
        graph.addEdges(2, 6, 1);
        graph.addEdges(5, 6, 1);*/

        //graph.showGraph();
        //graph.dfs();
        graph.bfs();


    }

    private int[][] edges;//存储边的二维矩阵
    private ArrayList<String> vertexList;
    private int numberOfEdges;
    private boolean[] visited;

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numberOfEdges = 0;
        visited = new boolean[n];
    }

    //广度优先遍历
    private void bfs(boolean[] visited, int i) {
        int u;  //表示队列头节点对应的下标
        int w;  //邻接点w
        //队列，记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(vertexList.get(i) + "->");
        visited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出头节点下标
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while(w!=-1){
                if(!visited[w]){
                    System.out.print(vertexList.get(w)+"->");
                    visited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);
            }
        }
    }
    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!visited[i]) {
                bfs(visited, i);
            }
        }
    }


    //深度优先遍历
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!visited[i]) {
                dfs(visited, i);
            }
        }
    }

    private void dfs(boolean[] visited, int i) {
        System.out.print(vertexList.get(i) + "->");
        visited[i] = true;
        //获得i的邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            //i有邻接节点w，并且邻接节点w没有被访问
            if (!visited[w]) {
                dfs(visited, w);
            } else {
                w = getNextNeighbor(i, w);
            }
        }
    }

    //得到v的第一个邻接点的下标w
    public int getFirstNeighbor(int v) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //v1为当前节点，根据前一个邻接节(v2)点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void showGraph() {
        for (int[] e : edges) {
            System.out.println(Arrays.toString(e));
        }
    }

    //得到边的数目
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    //得到节点的数目
    public int getNumberOfVertex() {
        return vertexList.size();
    }

    //返回下标i对应的节点
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //添加节点
    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    public void addEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }

}
