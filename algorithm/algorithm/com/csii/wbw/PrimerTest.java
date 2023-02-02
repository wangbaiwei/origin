package com.csii.wbw;

public class PrimerTest{
    public static void main(String[] args) {
       int max = 2;
       do {
           System.out.println(max);
       }while (max-->0);
    }

    public static int getV(Node node, int k) {
        int flag = 0;

        Node temp = node.next;
        Node curr = temp;
        while (curr.next != null && k-- > 1) {
            if (curr.next != null) {
                curr = curr.next;
            };
        }
        if (k != 0) {
            return -1;
        }
        while (curr.next != null) {
            temp = temp.next;
            curr = curr.next;
        }
        return temp.value;
    }



}
class Node {
    int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }
}