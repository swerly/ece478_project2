package part_three;
import part_two.Node;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by jakeboucher on 11/2/16.
 */
public class PartThree {
    private ArrayList<Node> sortedNodes;
    private ArrayList<Node> connectedNodes;

    public PartThree(){}

    public void run(ArrayList<Node> nodes) {
        System.out.println("Part 3 Begin");
        sortedNodes = sortNodes(nodes);
        connectedNodes = new ArrayList<>();
        findLargestClique();
        generateTable();
        System.out.println("Part 3 End");
    }

    private ArrayList<Node> sortNodes(ArrayList<Node> n) {
        Collections.sort(n, (o1, o2) -> o2.getDegree() - o1.getDegree());
        return n;
    }

    private void findLargestClique() {
        connectedNodes.add(sortedNodes.get(0));
        for(int i = 1; i < sortedNodes.size(); i ++) {
            int flag = 0;
            for(int j = 0; j < connectedNodes.size(); j++) {
                if(sortedNodes.get(i).getCustomers().contains(connectedNodes.get(j).getNumber())) {
                    flag++;
                }
                else if(sortedNodes.get(i).getPeers().contains(connectedNodes.get(j).getNumber())) {
                    flag++;
                }
                else if(sortedNodes.get(i).getProviders().contains(connectedNodes.get(j).getNumber())) {
                    flag++;
                }
                else {
                    flag = 0;
                    break;
                }
            }
            if(flag > 0) {
                connectedNodes.add(sortedNodes.get(i));
            }
            else {
                break;
            }
        }
    }

    private void generateTable() {
        //System.out.println(Integer.toString(connectedNodes.size()));
        for(int i = 0; i < connectedNodes.size(); i++) {
            System.out.println(Integer.toString(i + 1) + "\t" + Integer.toString(connectedNodes.get(i).getNumber()));
        }
    }


}
