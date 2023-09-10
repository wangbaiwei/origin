package com.wbw.algorithm.tree;

import java.util.List;

public class LowerAncestor {


    class Employee {
        int happy;
        List<Employee> nexts;

        public Employee(int happy, List<Employee> nexts) {
            this.happy = happy;
            this.nexts = nexts;
        }
    }

    public Info process(Employee employee) {
        if (employee == null) {
            return new Info(0, 0);
        }
        int no = 0;
        int yes = employee.happy;
        for (Employee next : employee.nexts) {
            // 向孩子要信息
            Info nextInfo = process(next);
            // x来只能孩子不来
            yes += nextInfo.no;
            // x不来，孩子来与不来的最大值的和
            no += Math.max(nextInfo.no, nextInfo.yes);
        }
        return new Info(no, yes);
    }

    class Info {
        int no;
        int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }



}
