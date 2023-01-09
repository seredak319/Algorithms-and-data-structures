package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PrimAlgorithm implements MinSpanningTree {

    private LinkedList<Node> result = new LinkedList<>();

    public String findMST(String pathToFile) {
        if(pathToFile == null){
            throw new NullPointerException();
        }

        ReaderGraph rg = new ReaderGraph();
        LinkedList<Node>[] matrix = rg.readFile(pathToFile);
        boolean[] visited = new boolean[rg.getSize()];
        HashMap<String,Integer> hash = rg.getHash();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(matrix[0].get(0));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        while(!pq.isEmpty()){
            Node head = pq.poll();
            Node currentNode;
            int index = hash.get(head.getName());

            for(int i =1; i< matrix[index].size(); i++){
                currentNode = matrix[index].get(i);
                if(!visited[hash.get(currentNode.getName())]){
                    pq.add(matrix[index].get(i));
                }
            }

            if( head.getParent() != null ){
                if(!visited[hash.get(head.getName())]){
                    stringBuilder.append(head.getParent()+"_"+head.getValue()+"_"+head.getName()+"|");
                }

            }
            visited[index] = true;
        }
        return stringBuilder.toString();
    }

}
