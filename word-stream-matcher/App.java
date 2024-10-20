import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/*
* A list of words are given as facts. Consider the words lion, cat, ant.
*
* Then a stream of characters is given as the input. If any continuous chars in the stream matches with the
* chars of any words in the fact then it should print true at the moment the character being evaluated is the last
* char of the word in facts. In all other situation it should print false.
*
* consider the char stream abclioncarantuio
*
* the output should be
* a - false
* b - false
* c - false
* l - false
* i - false
* o - false
* n - true
* c - false
* a - false
* r - false
* a - false
* n - false
* t - true
* u - false
* i - false
* o - false
*
* */

public class App {
    public static void main(String[] args) {
        Node rootNode = new Node('r', false);

        List<String> words = Arrays.asList("cd", "exam", "example", "gh");
        createCharTree(words, rootNode);

        String stringStream = "abcghdjfcdexample";
        findWordsInString(stringStream, rootNode);

    }

    private static void findWordsInString(String string, Node rootNode) {
        AtomicReference<Set<Node>> currentNodeSet = new AtomicReference<>(rootNode.children);
        string.chars().forEach(c -> {
            Node matchingNode;
            boolean isWord = false;

            matchingNode = getMatchingNode(c, currentNodeSet.get());

            if (Objects.isNull(matchingNode)) {
                matchingNode = getMatchingNode(c, rootNode.children);
            }

            if(Objects.nonNull(matchingNode)){
               isWord = matchingNode.isWord;
               currentNodeSet.set(matchingNode.children);
            }else {
                currentNodeSet.set(rootNode.children);
            }

            System.out.println(Character.getName(c) + " - " + isWord);
        });
    }

    private static Node getMatchingNode(int c, Set<Node> nodeList) {
        for (Node node : nodeList) {
            if (c == node.value) {
                return node;
            }
        }

        return null;
    }

    private static void createCharTree(List<String> words, Node rootNode) {
        for (String word : words) {
            char[] wordCharArray = word.toCharArray();
            Node currentNode = rootNode;
            for (int i = 0; i < wordCharArray.length; i++) {
                boolean isWord = wordCharArray.length - 1 == i;
                currentNode = addNode(currentNode, wordCharArray[i], isWord);
            }
        }
    }

    private static Node addNode(Node node, char c, boolean isWord) {
        Node newNode = null;
        for (Node n: node.children) {
            if (c == n.value) {
                newNode = n;
                break;
            }
        }

        if (newNode == null) {
            newNode = new Node(c, isWord);
            node.children.add(newNode);
        }

        return newNode;
    }

    static class Node {
        char value;
        boolean isWord;
        Set<Node> children = new HashSet<>();

        public Node(char value, boolean isWord) {
            this.value = value;
            this.isWord = isWord;
        }

        @Override
        public String toString() {
            return "\nNode{" +
                    "value=" + value +
                    ", isWord=" + isWord +
                    ", children=" + children +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}