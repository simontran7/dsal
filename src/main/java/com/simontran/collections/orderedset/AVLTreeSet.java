package com.simontran.collections.orderedset;

public class AVLTreeSet<K extends Comparable<K>> implements OrderedSet<K> {
    private static class Node<K> {
        private K key;
        private Node<K> left;
        private Node<K> right;
        private int height;

        public Node(K key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    private Node<K> root;

    public AVLTreeSet() {
        this.root = null;
    }

    public K min() {
        if (this.root == null) return null;
        return minNode(this.root).key;
    }

    public K max() {
        if (this.root == null) return null;
        return maxNode(this.root).key;
    }

    public K successor(K key) {
        Node<K> successor = successorNode(this.root, key);
        return successor == null ? null : successor.key;
    }

    public K predecessor(K key) {
        Node<K> predecessor = predecessorNode(this.root, key);
        return predecessor == null ? null : predecessor.key;
    }

    public boolean contains(K key) {
        return getNode(this.root, key) != null;
    }

    public void add(K key) {
        this.root = insertNode(this.root, key);
    }

    public void remove(K key) {
        this.root = removeNode(this.root, key);
    }

    private Node<K> minNode(Node<K> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<K> maxNode(Node<K> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node<K> successorNode(Node<K> node, K key) {
        Node<K> successor = null;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                successor = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return successor;
    }

    private Node<K> predecessorNode(Node<K> node, K key) {
        Node<K> predecessor = null;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                predecessor = node;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return predecessor;
    }

    private Node<K> getNode(Node<K> node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private Node<K> insertNode(Node<K> node, K key) {
        if (node == null) return new Node<>(key);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insertNode(node.left, key);
        } else if (cmp > 0) {
            node.right = insertNode(node.right, key);
        } else {
            return node;
        }
        updateHeight(node);
        return balance(node);
    }

    private Node<K> removeNode(Node<K> node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeNode(node.left, key);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node<K> successor = minNode(node.right);
            node.key = successor.key;
            node.right = removeNode(node.right, successor.key);
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node<K> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node<K> node) {
        return node == null ? -1 : node.height;
    }

    private Node<K> balance(Node<K> node) {
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1) { // right heavy
            if (balanceFactor(node.right) < 0) { // right triangle
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        } else if (balanceFactor < -1) { // left heavy
            if (balanceFactor(node.left) > 0) { // left triangle
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        return node;
    }

    private int balanceFactor(Node<K> node) {
        return height(node.right) - height(node.left);
    }

    private Node<K> rotateLeft(Node<K> root) {
        Node<K> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node<K> rotateRight(Node<K> root) {
        Node<K> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }
}
