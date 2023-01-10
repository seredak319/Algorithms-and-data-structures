package pl.edu.pw.ee.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.util.regex.Pattern;
import pl.edu.pw.ee.exception.ReadingGraphFromFileException;


public class GraphReader {

    public class ref{
        String name;
        public int a;
        public int b;
        public ref(String name ,int a, int b){
            this.name = name;
            this.a =a;
            this.b =b;
        }
    }

    public class con{
        public String a;
        public String b;
        public con(String a, String b){
            this.a =a;
            this.b =b;
        }
    }

    private final String lineRegex = "^\\S+ \\S+ \\\\[-]d+";
    private final Pattern pattern = Pattern.compile(lineRegex);
    private HashMap<String,Integer> hash;
    private HashMap<String,Integer> nodes = new HashMap<>();

    private con[] conctions = new con[512];
    private ref[] weights = new ref[512];
    int c;
    int r;
    private final String fileToPath;
    private final List<Edge> edges;

    public GraphReader(String fileToPath) {
        validateData(fileToPath);

        this.fileToPath = fileToPath;
        this.edges = new ArrayList<>();

        readAndSort();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private void validateData(String fileToPath) {
        if (fileToPath == null) {
            throw new IllegalArgumentException("File to path arg cannot be null!");
        }
    }

    private void readAndSort() {
        readGraphFromFile();
        sortEdgesByPriority();
    }

    private void readGraphFromFile() {
        hash = new HashMap<>();
        Edge edge;
        int i = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToPath))) {
            String line;

            line = reader.readLine();
            String elems[] = line.split(" ");
            if( elems.length == 2 || elems[1].matches("^[0-9]*$")){
                parseToEdge2(line,i);
                while ((line = reader.readLine()) != null) {
                    parseToEdge2(line, i);
                    i++;
                }
                //todo build edges



            } else {
                edge = parseToEdge(line,i);
                edges.add(edge);
                while ((line = reader.readLine()) != null) {
                    edge = parseToEdge(line, i);
                    edges.add(edge);
                    i++;
                }
            }


            buildEdges();
        } catch (IOException e) {
            throw new ReadingGraphFromFileException("Cannot read file from path!", e);
        }
    }

    private void buildEdges(){
        String firstName;
        String secondName;
        int i =0;
        int a=0;
        int b=0;
        int e=0;
        int d=0;

        while(i < c){
            firstName = conctions[i].a;
            secondName = conctions[i].b;

            int j=0;
            String res = weights[j].name;
            a = weights[j].a;
            b = weights[j].b;
            while(!res.equals(firstName)){
                res = weights[j].name;
                a = weights[j].a;
                b = weights[j].b;
                j++;
            }

            int k=0;
            String res2 = weights[k].name;
            e = weights[k].a;
            d = weights[k].b;
            while(!res2.equals(secondName)){
                res2 = weights[k].name;
                e = weights[k].a;
                d = weights[k].b;
                k++;
            }
//
//            System.out.println(firstName + " " + a + " " + b);
//            System.out.println(secondName + " " + e + " " + d);
//            System.out.println();


            Node start = new Node(firstName);
            Node end = new Node(secondName);
            double weight = (a-e)*(a-e) + (b-d)*(b-d);
            weight = Math.sqrt(weight);

            edges.add( new Edge(start, end, weight) );



            i++;
        }

    }

    private void parseToEdge2(String line, int lineNumber){
        Node start;
        Node end;
        int weight;

        String[] splitLine = line.split(" ");

        if (splitLine.length == 0){
            return;
        }

        if( splitLine.length == 2){
            conctions[c++] = new con(splitLine[0], splitLine[1]);
        } else if (splitLine.length == 3){
            if(nodes.containsKey(splitLine[0])){
                System.out.println(line + " ss");
                int index = nodes.get(splitLine[0]);
                System.out.println(weights[index].name);
                System.out.println(weights[index].a);
                System.out.println(weights[index].b);
                weights[index] = null;
                weights[index] = new ref(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
            } else {
                nodes.put(splitLine[0], lineNumber);
                System.out.println("Dodaje: " + line);
                weights[r++] = new ref(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
            }
        } else {
            return;
        }

        String edgeName1 = splitLine[0] + splitLine[1];
        String edgeName2 = splitLine[1] + splitLine[0];
        String[] sort = {edgeName1, edgeName2 };
        Arrays.sort(sort);
        String result = sort[0];

//        return new Edge(start, end, weight);
        return;
    }

    private Edge parseToEdge(String line, int lineNumber) {
        String[] args;
        Node start;
        Node end;
        int weight;

        String[] splitLine = line.split(" ");

        String edgeName1 = splitLine[0] + splitLine[1];
        String edgeName2 = splitLine[1] + splitLine[0];
        String[] sort = {edgeName1, edgeName2 };
        Arrays.sort(sort);
        String result = sort[0];

        if(!(hash.containsKey(result))){
            hash.put(result,lineNumber);
            start = new Node(splitLine[0]);
            end = new Node(splitLine[1]);
            weight = Integer.parseInt(splitLine[2]);

            return new Edge(start, end, weight);

        } else {
            int index = hash.get(result);
            edges.remove(index);

            start = new Node(splitLine[0]);
            end = new Node(splitLine[1]);
            weight = Integer.parseInt(splitLine[2]);

            return new Edge(start, end, weight);
        }

//        Matcher matcher = pattern.matcher(line);

//        if (matcher.find()) {
//            args = line.split(" ");
//            start = new Node(args[0]);
//            end = new Node(args[1]);
//            weight = Integer.parseInt(args[2]);
//
//            return new Edge(start, end, weight);
//
//        } else {
//            throw new ReadingGraphFromFileException(
//                    format("Cannot correctly parse line %d from file", lineNumber));
//        }
    }

    private void sortEdgesByPriority() {
        EdgeWeightComparator weightComparator = new EdgeWeightComparator();

        Collections.sort(edges, weightComparator);
    }

}
