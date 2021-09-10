package com.yuxiao.daily;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 所有可能的路径
 * <p>
 * 给你一个有n个节点的 有向无环图（DAG），请你找出所有从节点 0到节点 n-1的路径并输出（不要求按特定顺序）
 * <p>
 * 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
 * <p>
 * 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-08-26 22:45
 */
public class AllPaths797 {

    /**
     * 所有路径
     */
    private List<List<Integer>> allPaths = new ArrayList<>();

    public static void main(String[] args) {
        AllPaths797 allPaths797 = new AllPaths797();
        int[][] graph = {{1,2},{3},{3},{}};
        graph = new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        System.out.println(allPaths797.allPathsSourceTarget(graph));
    }


    /**
     * @param graph 图数据
     * @return 所有路径
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {


        // 计算当前路径，开始节点为0，结束节点为nodeCount - 1
        Stack<Integer> path = new Stack<>();
        // 节点都是从0开始
        path.push(0);
        addNextNode(graph[0], graph, path);
        return allPaths;
    }


    /**
     * 单条路径采用栈来存储，单次递归结束后，栈顶数据和终点节点数据相等，则说明当前栈中的路径有效
     * 否则，没退出一层递归弹出一个栈顶数据，保证栈底数据（路径）的可复用
     * @param currentNode 当前可达节点的数组
     * @param allNode 待计算的所有路径数据
     * @param path 当前路径（已走过的节点集合）
     * @return
     */
    private Stack<Integer> addNextNode(int[] currentNode, int[][] allNode, Stack<Integer> path) {
        for (int nextNode : currentNode) {
            path.push(nextNode);
            // 一条路径结束后返回
            path = addNextNode(allNode[nextNode], allNode, path);
            // 当前路径的最后一个节点和尾结点相等，说明此路径可通，添加到结果路径中
            if (path.get(path.size() - 1) == allNode.length - 1) {
                allPaths.add(new ArrayList<>(path));
                System.out.println(path);
            }
            // 每退出一层递归，弹出无效的栈顶数据
            path.pop();
        }
        return path;
    }



}
