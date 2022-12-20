package pl.edu.pw.ee;

import java.util.Arrays;

class LongestCommonSubsequence {

    private String left;
    private String top;
    private int[][] map;
    private String[][] sign;
    private char[] chars; 

    public LongestCommonSubsequence(String leftSrc, String topSrc) {
        // TODO
        this.left = leftSrc;
        this.top = topSrc;
        map = new int[left.length()+1][top.length()+1];
        sign = new String[left.length()+1][top.length()+1];
        chars = new char[top.length()+1];
        init();
    }

    public String findLCS() {
        int n =0;
        
        StringBuilder sb = new StringBuilder();
        
        for(int i =1; i<=left.length(); i++){
            for(int j =1; j<=top.length(); j++){
                
                
                if(left.charAt(i-1) == top.charAt(j-1)){
                    map[i][j] = map[i-1][j-1] + 1;
                    sign[i][j] = "/";
                    
                } else {
                    if( map[i][j-1] >= map[i-1][j]){
                    map[i][j] = map[i][j-1];
                    sign[i][j] = "^";
                } else {
                    map[i][j] = map[i-1][j];
                    sign[i][j] = "<";
                }
                }
                
                
            }
        }
        int k = left.length()-1;
        int l = top.length()-1;
        int val = map[left.length()][top.length()];
        String t = sign[left.length()][top.length()];
            
        while( val != 0){
                if(sign[k][l] == "/"){
                    val = map[k][l];
                    sb.append(top.charAt(l-1));
                    k = k-1;
                    l = l-1;
                    continue;
                } else if ( sign[k][l] == "^" ){
                    val = map[k][l];
                    k= k-1;
                    continue;
                } else if ( sign[k][l] == "<" ) {
                    val = map[k][l];
                    l=l-1;
                    continue;
                }
        }
        
                    System.out.println("char is " + top.charAt(l));
                    System.out.println("val k: " + k + " l: " + l);
        // TODO
        return sb.toString();
    }

    public void display() {
        // TODO

    }

    private void init() {
        for (int i = 0; i < left.length(); i++) {
            map[i][0] = 0;
        }
        
        for (int i =0; i < top.length(); i++){
            map[0][i] = 0;
        }
    }

}
