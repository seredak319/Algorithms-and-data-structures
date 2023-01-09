/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.edu.pw.ee;

/**
 *
 * @author seredak
 */
public class Node implements Comparable<Node> {

    private String parent;
    private String name;
    private int value;

    public Node(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Node(String parent, String name, int value){
        this.parent = parent;
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getParent(){
        if(parent != null){
            return parent;
        } else {
            return null;
        }
    }

    @Override
    public int compareTo(Node o) {
        if( value > o.value){
            return 1;
        } else if ( value < o.value){
            return -1;
        } else{
            return 0;
        }
    }

    @Override
    public String toString(){
        return parent+"_"+value+"_"+name;
    }
}
