package part_two;

import java.util.ArrayList;

/**
 * Created by sethw on 10/31/2016.
 */
public class Node {
    private int number;
    private int degree;
    private ArrayList<Integer> peers;
    private ArrayList<Integer> providers;
    private ArrayList<Integer> customers;

    public Node(){
        this(-1);
    }

    public Node(int number){
        this.number = number;
        degree = -1;

        peers = new ArrayList<>();
        providers = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public int getDegree() {
        return degree;
    }

    public ArrayList<Integer> getPeers() {
        return peers;
    }

    public ArrayList<Integer> getProviders() {
        return providers;
    }

    public ArrayList<Integer> getCustomers() {
        return customers;
    }

    public boolean addPeer(int peerNumber){
        if (!peers.contains(peerNumber)){
            peers.add(peerNumber);
            return true;
        }
        return false;
    }

    public boolean addCustomer(int customerNumber){
        if (!customers.contains(customerNumber)){
            customers.add(customerNumber);
            return true;
        }
        return false;
    }

    public boolean addProvider(int providerNumber){
        if (!providers.contains(providerNumber)){
            providers.add(providerNumber);
            return true;
        }
        return false;
    }

    public int calculateDegree(){
        this.degree = peers.size() + customers.size() + providers.size();
        return this.degree;
    }

    public static int containsNode(ArrayList<Node> nodes, int nodeNumber){
        for (Node node : nodes)
            if (node.getNumber() == nodeNumber) return nodes.indexOf(node);
        return Integer.MAX_VALUE;
    }
}
