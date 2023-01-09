package pl.edu.pw.ee;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class ReaderGraph {

    private int INIT_VALUE = 512;
    private LinkedList<Node>[] matrix = new LinkedList[INIT_VALUE];
    private int n;
    private HashMap<String,Integer> hash;


    public LinkedList<Node>[] readFile(String path){
        if(path == null){
            throw new NullPointerException("File path cannot be null.");
        }

        if( !new File(path).isFile() || !new File(path).canRead() ){
            throw new IllegalArgumentException("Given path is wrong or given file cannot be read.");
        }

        hash = new HashMap<>();
        String currentLine;

        try {
            int count = 1;
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((currentLine = bufferedReader.readLine()) != null) {
                if(!currentLine.matches("^[a-zA-Z0-9 ]*$")){
                    throw new IllegalArgumentException("File cannot be read properly, line [" + count + "] is wrong"
                    +"\n line is: [  " + currentLine + "  ].\n" +
                    "There should be only small and big letters");
                }
                String[] splitLine = currentLine.split(" ");
                if(splitLine.length != 3){
                    throw new IllegalArgumentException("File cannot be read properly, line [" + count + "] is wrong"
                    +"\n line is: [  " + currentLine + "  ].\n" +
                    "There should be three arguments");
                }
                if(splitLine[0].equals(splitLine[1])){
                    throw new IllegalArgumentException("Loop occurred in line [  " + count + "  ] " +
                    "\n line is: [  " + currentLine + "  ].\n" +
                    "This is illegal.");
                }

                if(!splitLine[2].matches("^[0-9]*$")){
                    throw new IllegalArgumentException();
                }


                    if(n == INIT_VALUE-2){
                        resize();
                    }

                    if(!hash.containsKey(splitLine[0])){
                        hash.put(splitLine[0],n);
                        LinkedList<Node> linkedList = new LinkedList<>();
                        linkedList.addFirst(new Node(splitLine[0],n));
                        linkedList.addLast(new Node(splitLine[0] ,splitLine[1], Integer.parseInt(splitLine[2])));
                        matrix[n++] = linkedList;
                    } else {
                        int index = hash.get(splitLine[0]);
                        matrix[index].add(new Node(matrix[index].getFirst().getName() ,splitLine[1], Integer.parseInt( splitLine[2])));
                    }

                    if(!hash.containsKey(splitLine[1])){
                        hash.put(splitLine[1],n);
                        LinkedList<Node> linkedList = new LinkedList<>();
                        linkedList.addFirst(new Node(splitLine[1], n));
                        linkedList.addLast(new Node(splitLine[1] ,splitLine[0], Integer.parseInt(splitLine[2])));
                        matrix[n++] = linkedList;
                    } else {
                        int index = hash.get(splitLine[1]);
                        matrix[index].add(new Node(matrix[index].getFirst().getName(),splitLine[0], Integer.parseInt( splitLine[2])));
                    }

            count++;
            }

            bufferedReader.close();
        }
        catch(IOException e) {
            System.out.println("Cannot read file because of improper data [  " + path + "  ]" );
        }

        return matrix;
    }

    public int getSize(){
        return n;
    }

    public HashMap<String,Integer> getHash(){
        return hash;
    }

    private void resize(){
        INIT_VALUE *= 2;
        matrix = Arrays.copyOf(matrix, INIT_VALUE);
    }



}
