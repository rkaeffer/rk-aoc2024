package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Day10 {

    static int[] xDir = {0,1,0,-1};
    static int[]  yDir = {1,0,-1,0};

    public static long getMapScore(List<String> input, boolean distinct) {
        List<Node> startNodes=new ArrayList<>();
        Node[][] nodes = new Node[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length() ; j++) {
                nodes[i][j] = new Node(j, i, Integer.parseInt(input.get(i).charAt(j) + ""));
                if(nodes[i][j].value == 0) {
                    startNodes.add(nodes[i][j]);
                }
            }
        }

        long sum = 0L;
        for(Node n : startNodes) {
            n.findPath(nodes, distinct);
            sum += n.endNodes.size();
        }
        return sum;
    }


    static class Node {
        int x;
        int y;

        int value;
        List<Node> endNodes;

        Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            if(this.value == 0) {
                this.endNodes = new ArrayList<>();
            }
        }

        public void findPath(Node[][] nodes, boolean distinct) {
            findEndNode(this, nodes);
            if(distinct) {
                endNodes = endNodes.stream().distinct().collect(Collectors.toList());
            }
        }

        public void findEndNode(Node curNode, Node[][] nodes) {
            for(int i=0; i<4; i++) {
                int nextX = curNode.x + xDir[i];
                int nextY = curNode.y + yDir[i];
                if(isPosSafe(nextX, nextY, nodes)) {
                    if(nodes[nextY][nextX].value == curNode.value + 1) {
                        if (curNode.value == 8) {
                            this.endNodes.add(nodes[nextY][nextX]);
                        } else {
                            findEndNode(nodes[nextY][nextX], nodes);
                        }
                    }
                }
            }
        }
    }

    private static boolean isPosSafe(int x, int y, Node[][] nodes) {
        return x>=0 && y>=0 && x<nodes[0].length && y<nodes.length;
    }
}
