package part_two;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by sethw on 10/31/2016.
 */
public class PartTwo {
    private ArrayList<Node> nodes;
    private String[] fieldsCurLine;

    public PartTwo(){}

    public void start(){
        nodes = new ArrayList<>();

        System.out.println("\n\n ------ PART TWO BEGIN ------");
        parseAndPopulate();
        performAnalysis();
        System.out.println("\n ------ PART TWO END ------\n");

    }

    private void parseAndPopulate(){
        FileInputStream inp;
        BufferedReader br = null;
        String curLine;

        //BEGIN OPENING FILE AND POPULATING NODES
        try {
            inp = new FileInputStream(new File(getClass().getResource("./20161001.as-rel2.txt").toURI()));
            br = new BufferedReader(new InputStreamReader(inp));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("\n...POPULATING NODES...\n");
            while ((curLine = br.readLine()) != null){
                if(curLine.charAt(0) == '#') continue;

                fieldsCurLine = curLine.split("\\|");

                firstFieldHandle();
                secondFieldHandle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void performAnalysis(){
        System.out.println("\n...PERFORMING ANALYSIS...\n");
        int tDegree, bin1=0, bin2_5=0, bin5_100=0, bin100_200=0, bin200_1000=0, bin1000plus=0;
        int why = 0;

        int enterprise=0, content=0, transit=0;

        for (Node node : nodes){
            tDegree = node.calculateDegree();

            if (tDegree < 1 ) {
                why++;
            }else if (tDegree == 1){
                bin1++;
            } else if (tDegree < 5){
                bin2_5++;
            } else if (tDegree < 100){
                bin5_100++;
            } else if (tDegree < 200){
                bin100_200++;
            } else if (tDegree < 1000){
                bin200_1000++;
            } else if (tDegree >= 1000){
                bin1000plus++;
            }

            if (node.getDegree() <= 2 && node.getCustomers().size() == 0 && node.getPeers().size() == 0) {
                enterprise++;
            } else if (node.getCustomers().size() ==  0 && node.getPeers().size() >= 1) {
                content++;
            } else if (node.getCustomers().size() >= 1){
                transit++;
            }
        }
        float binTot = bin1+bin2_5+bin5_100+bin100_200+bin200_1000+bin1000plus;
        System.out.printf("Total nodes: %s\n", nodes.size());
        System.out.printf("\n1: %f\n2-5: %f\n5-100: %f\n100-200: %f\n200-1000: %f\n1000+: %f\n",
                bin1/binTot, bin2_5/binTot, bin5_100/binTot, bin100_200/binTot, bin200_1000/binTot, bin1000plus/binTot);
        if (why > 0) System.out.println("WHY: " + why);

        System.out.printf("\nE: %d   C: %d   T: %d\n", enterprise, content, transit);
        System.out.printf("Leftover: %d\n", nodes.size()-(content+transit+enterprise));
    }

    private void firstFieldHandle(){
        Node tNode;
        //get index of node if it exists in the list
        //returns -1 if node doesn't exist
        int index = Node.containsNode(nodes, Integer.parseInt(fieldsCurLine[0]));

        //if node doesn't exist in the list yet
        if (index == Integer.MAX_VALUE){
            //create the node and add it
            tNode = new Node(Integer.parseInt(fieldsCurLine[0]));
            nodes.add(tNode);
        }
        //else node exists and we want to modify it
        else {
            tNode = nodes.get(index);
        }

        //p2p
        if (Integer.parseInt(fieldsCurLine[2]) == 0){
            tNode.addPeer(Integer.parseInt(fieldsCurLine[1]));
        }
        //p2c
        else if (Integer.parseInt(fieldsCurLine[2]) == -1){
            tNode.addCustomer(Integer.parseInt(fieldsCurLine[1]));
        }
    }

    private void secondFieldHandle(){
        Node tNode;

        int index = Node.containsNode(nodes, Integer.parseInt(fieldsCurLine[1]));

        //if node doesn't exist in the list yet
        if (index == Integer.MAX_VALUE){
            //create the node and add it
            tNode = new Node(Integer.parseInt(fieldsCurLine[1]));
            nodes.add(tNode);
        }
        //else node exists and we want to modify it
        else {
            tNode = nodes.get(index);
        }

        //p2p
        if (Integer.parseInt(fieldsCurLine[2]) == 0){
            tNode.addPeer(Integer.parseInt(fieldsCurLine[0]));
        }
        //p2c
        else if (Integer.parseInt(fieldsCurLine[2]) == -1){
            tNode.addProvider(Integer.parseInt(fieldsCurLine[0]));
        }
    }

    public ArrayList<Node> getNodes(){
        return this.nodes;
    }
}
