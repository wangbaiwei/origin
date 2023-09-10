package com.wbw.algorithm.grph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    // 值
    public int value;
    // 入度
    public int in;
    // 出度
    public int out;
    // 邻居
    public List<Node> nexts;
    // 边
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
