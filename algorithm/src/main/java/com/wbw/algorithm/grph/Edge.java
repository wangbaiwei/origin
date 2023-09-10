package com.wbw.algorithm.grph;

public class Edge {

    public int weight;
    public Edge from;
    public Edge to;

    public Edge(int weight, Edge from, Edge to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
