package edu.sabana.aads.hackerrank.tree;

import java.util.*;


class LevelOrderTraversal {
    
    static class Node {
        LevelOrderTraversal.Node left;
        LevelOrderTraversal.Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static String levelOrder(LevelOrderTraversal.Node root) {
        
        StringBuilder sb = new StringBuilder();
        Queue<LevelOrderTraversal.Node> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            LevelOrderTraversal.Node node = queue.poll();

            if (node != null) {
                sb.append(node.data).append(" ");
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        return sb.toString();
    }

    public static LevelOrderTraversal.Node insert(LevelOrderTraversal.Node root, int data) {
        if (root == null) {
            return new LevelOrderTraversal.Node(data);
        } else {
            LevelOrderTraversal.Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        LevelOrderTraversal.Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        System.out.println(levelOrder(root));
    }
}