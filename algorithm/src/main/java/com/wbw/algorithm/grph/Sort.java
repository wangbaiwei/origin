package com.wbw.algorithm.grph;

import com.wbw.algorithm.common.CommonUtil;
import org.junit.Test;

import java.util.*;

public class Sort {


    public List<Node> sortedTopology(Graph graph) {

        // 保存结点及入度
        Map<Node, Integer> inMap = new HashMap<>();
        // 队列：保存入读为0的结点
        Queue<Node> zeroInNode = new LinkedList<>();
        graph.nodes.forEach((integer, node) -> {
            inMap.put(node, integer);
            if (integer == 0) {
                zeroInNode.offer(node);
            }
        });

        List<Node> result = new ArrayList<>();
        while (!zeroInNode.isEmpty()) {
            Node poll = zeroInNode.poll();
            result.add(poll);
            for (Node next : poll.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInNode.offer(next);
                }
            }
        }
        return result;
    }


    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }


    public class Solution {
        /**
         * @param graph: A list of Directed graph node
         * @return: Any topological order for the given graph.
         */
        public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            // write your code here

            Map<DirectedGraphNode, Record> nodeRecord = new HashMap<>();

            List<Map.Entry<DirectedGraphNode, Record>> list = new ArrayList<>(nodeRecord.entrySet());
            list.sort((a, b) -> a.getValue().count == b.getValue().count ? 0 : a.getValue().count < b.getValue().count ? -1 : 1);

            ArrayList<DirectedGraphNode> ans = new ArrayList<>();
            for (Map.Entry<DirectedGraphNode, Record> r : list) {
                ans.add(r.getKey());
            }

            return ans;

        }

        public Record statistic(DirectedGraphNode node, Map<DirectedGraphNode, Record> nodeRecord) {
            if (node.neighbors == null || node.neighbors.size() == 0) {
                return new Record(node, 0);
            }
            if (nodeRecord.containsKey(node)) {
                return nodeRecord.get(node);
            }

            long count = 0;
            for (DirectedGraphNode child : node.neighbors) {
                count += statistic(child, nodeRecord).count;
            }
            return new Record(node, count + 1);

        }

        class Record {
            DirectedGraphNode node;
            long count;

            public Record(DirectedGraphNode node, long count) {
                this.node = node;
                this.count = count;
            }
        }
    }


    @Test
    public void testTmp() {

        int[] randomArr = CommonUtil.getRandomArr(5);
        System.out.println(Arrays.toString(randomArr));
        for (int i = 0; i < randomArr.length; i++) {
            if ((randomArr[i] & 1) == 1) {
                System.out.println(randomArr[i]);
            }
        }
        "".replaceFirst("", "");
    }

    @Test
    public void testCircularGameLosers() {
        int[] ints = circularGameLosers(5, 2);
        System.out.println(Arrays.toString(ints));
    }


    public int[] circularGameLosers(int n, int k) {
        boolean[] flag = new boolean[n];

        for (int i = k, j = 0; !flag[j]; i += k) {
            flag[j] = true;
            j = (j + i) % n;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!flag[i]) {
                res.add(i + 1);
            }
        }
        int[] ans = new int[res.size()];
        res.stream().forEach(e -> {
            int i = 0;
            ans[i++] = e;
        });
        return ans;
    }

}
