package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class KruskalAlgorithm implements MinSpanningTree {
    private int INIT_VALUE = 512;
    private Node[] edges;
    private int count;
    private HashMap<String,Integer> used;

    @Override
    public String findMST(String pathToFile) {
        if(pathToFile == null){
            throw new NullPointerException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        ReaderGraph rg = new ReaderGraph();
        LinkedList<Node>[] matrix = rg.readFile(pathToFile);

        createEdges(matrix,rg.getSize());

        Arrays.sort(edges);

        HashMap<String,Integer> addedNodes = new HashMap<>();

        stringBuilder.append("|");
        int i =0;

        while(i <= rg.getSize()){
            String parent = edges[i].getParent();
            String child = edges[i].getName();
            int value = edges[i].getValue();

            if( !(addedNodes.containsKey(parent) && addedNodes.containsKey(child)) ){
                if(!addedNodes.containsKey(parent)){
                    addedNodes.put(parent,i);
                }
                if(!addedNodes.containsKey(child)){
                    addedNodes.put(child,i);
                }
                stringBuilder.append(parent).append("_").append(value).append("_").append(child).append("|");
            }
            i++;
        }


        return stringBuilder.toString();
    }


    private void createEdges(LinkedList<Node>[] matrix, int n){
        edges = new Node[INIT_VALUE];
        used = new HashMap<>();
        for(int i =0; i< n; i++){
            for(int j=1; j<matrix[i].size(); j++){
                Node currentNode = matrix[i].get(j);
                if(currentNode == null){
                    throw new NullPointerException("Sth went wrong!");
                }
                if(currentNode.getParent() != null){
                    String nameOfNodeParentChild = currentNode.getParent()+currentNode.getName();
                    String nameOfNodeChildParent = currentNode.getName()+currentNode.getParent();
                    if(!(used.containsKey(nameOfNodeParentChild) || used.containsKey(nameOfNodeChildParent))){
                        if(count == INIT_VALUE - 1){
                            resize();
                        }
                        edges[count++] = currentNode;
                        used.put(nameOfNodeChildParent,count);
                        used.put(nameOfNodeParentChild,count);
                    }
                }
            }
        }
        edges = Arrays.copyOf(edges, count);
    }

    private void resize(){
        INIT_VALUE *= 2;
        edges = Arrays.copyOf(edges, INIT_VALUE);
    }
}
