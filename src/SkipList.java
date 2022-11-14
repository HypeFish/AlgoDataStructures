//import java.util.Objects;
//import java.util.Random;
//import java.util.Scanner;
//
//interface SkippableList<T extends Comparable<? super T>> {
//    int LEVELS = 5;
//
//    boolean delete(T target);
//    void print();
//    void insert(T data);
//    SkipNode<T> search(T data);
//}
//
//public class SkipList<T extends Comparable<? super T>> implements SkippableList<T> {
//
//    public static void main(String[] args) {
//        SkipList<Integer> sl = new SkipList<>();
//        int[] data = {5, 3, 1, 13, 20, 11, 24, 6, 9, 10, 70, 0, 4, 43, 12};
//        for (int i : data) {
//            sl.insert(i);
//        }
//
//        sl.print();
//        sl.search(4);
//
//        sl.delete(9);
//        sl.print();
//
//        sl.insert(69);
//        sl.print();
//        sl.search(69);
//
//        Scanner s = new Scanner(System.in);
//        System.out.println("Would you like to search x, delete x, or insert x? Press q to quit");
//
//        boolean running = true;
//        while (running) {
//            String next = s.next();
//            switch (next) {
//                case "q" -> running = false;
//                case "search" -> {
//                    int int1 = s.nextInt();
//                    System.out.println(sl.search(int1));
//                    sl.print();
//                }
//                case "delete" -> {
//                    int int1 = s.nextInt();
//                    System.out.println(sl.delete(int1));
//                }
//                case "insert" -> {
//                    int int1 = s.nextInt();
//                    sl.insert(int1);
//                    sl.print();
//                }
//                default -> System.out.println("Unknown command ");
//            }
//        }
//    }
//
//    private final SkipNode<T> head = new SkipNode<>(null);
//    private final Random rand = new Random();
//
//    @Override
//    public void insert(T data) {
//        SkipNode<T> SkipNode = new SkipNode<>(data);
//        for (int i = 0; i < LEVELS; i++) {
//            if (rand.nextInt((int) Math.pow(2, i)) == 0) { //insert with prob = 1/(2^i)
//                insert(SkipNode, i);
//            }
//        }
//    }
//
//    @Override
//    public boolean delete(T target) {
//        System.out.println("Deleting " + target.toString());
//        SkipNode<T> victim = search(target, false);
//        if (victim == null) return false;
//        victim.data = null;
//
//        for (int i = 0; i < LEVELS; i++) {
//            head.refreshAfterDelete(i);
//        }
//
//        System.out.println();
//        return true;
//    }
//
//    @Override
//    public SkipNode<T> search(T data) {
//        return search(data, true);
//    }
//
//    @Override
//    public void print() {
//        for (int i = 0; i < LEVELS; i++) {
//            head.print(i);
//        }
//
//        System.out.println();
//    }
//
//    private void insert(SkipNode<T> SkipNode, int level) {
//        head.insert(SkipNode, level);
//    }
//
//    private SkipNode<T> search(T data, boolean print) {
//        SkipNode<T> result = null;
//        for (int i = LEVELS-1; i >= 0; i--) {
//            if ((result = head.search(data, i, print)) != null) {
//                if (print) {
//                    System.out.println("Found " + data.toString() + " at level " + i + ", so stoppped" );
//                    System.out.println();
//                }
//
//                break;
//            }
//        }
//
//        return result;
//    }
//
//}
//
//class SkipNode<N extends Comparable<? super N>> {
//
//    N data;
//    @SuppressWarnings("unchecked")
//    SkipNode<N>[] next = (SkipNode<N>[]) new SkipNode[SkippableList.LEVELS];
//
//    SkipNode(N data) {
//        this.data = data;
//    }
//
//    void refreshAfterDelete(int level) {
//        SkipNode<N> current = this.getNext(level);
//        while (current != null && current.getNext(level) != null) {
//            if (current.getNext(level).data == null) {
//                SkipNode<N> successor = current.getNext(level).getNext(level);
//                current.setNext(successor, level);
//                return;
//            }
//
//            current = current.getNext(level);
//        }
//    }
//
//    void setNext(SkipNode<N> next, int level) {
//        this.next[level] = next;
//    }
//
//    SkipNode<N> getNext(int level) {
//        return this.next[level];
//    }
//
//    SkipNode<N> search(N data, int level, boolean print) {
//        if (print) {
//            System.out.print("Searching for: " + data + " at ");
//            print(level);
//        }
//
//        SkipNode<N> result = null;
//        SkipNode<N> current = this.getNext(level);
//        while (current != null && current.data.compareTo(data) < 1) {
//            if (current.data.equals(data)) {
//                result = current;
//                break;
//            }
//
//            current = current.getNext(level);
//        }
//
//        return result;
//    }
//
//    void insert(SkipNode<N> SkipNode, int level) {
//        SkipNode<N> current = this.getNext(level);
//        if (current == null) {
//            this.setNext(SkipNode, level);
//            return;
//        }
//
//        if (SkipNode.data.compareTo(current.data) < 1) {
//            this.setNext(SkipNode, level);
//            SkipNode.setNext(current, level);
//            return;
//        }
//
//        while (current.getNext(level) != null && current.data.compareTo(SkipNode.data) < 1 &&
//                current.getNext(level).data.compareTo(SkipNode.data) < 1) {
//
//            current = current.getNext(level);
//        }
//
//        SkipNode<N> successor = current.getNext(level);
//        current.setNext(SkipNode, level);
//        SkipNode.setNext(successor, level);
//    }
//
//    void print(int level) {
//        System.out.print("level " + level + ": [");
//        int length = 0;
//        SkipNode<N> current = this.getNext(level);
//        if (current == null) {
//            System.out.print("");
//        }
//        while (current != null) {
//            length++;
//            System.out.print(current.data.toString() + " ");
//            current = current.getNext(level);
//        }
//
//        System.out.println("], length: " + length);
//    }
//
//}
//

import java.util.Random;
import java.util.Scanner;

interface SkippableList<T extends Comparable<? super T>> {

    int LEVELS = 5;

    boolean delete(T target);
    void print();
    void insert(T data);
    SkipNode<T> search(T data);
}

class SkipNode<N extends Comparable<? super N>> {

    N data;
    @SuppressWarnings("unchecked")
    SkipNode<N>[] next = (SkipNode<N>[]) new SkipNode[SkippableList.LEVELS];

    SkipNode(N data) {
        this.data = data;
    }

    void refreshAfterDelete(int level) {
        SkipNode<N> current = this;
        while (current != null && current.getNext(level) != null) {
            if (current.getNext(level).data == null) {
                SkipNode<N> successor = current.getNext(level).getNext(level);
                current.setNext(successor, level);
                return;
            }

            current = current.getNext(level);
        }
    }

    void setNext(SkipNode<N> next, int level) {
        this.next[level] = next;
    }

    SkipNode<N> getNext(int level) {
        return this.next[level];
    }

    SkipNode<N> search(N data, int level, boolean print) {
        if (print) {
            System.out.print("Searching for: " + data + " at ");
            print(level);
        }

        SkipNode<N> result = null;
        SkipNode<N> current = this.getNext(level);
        while (current != null && current.data.compareTo(data) < 1) {
            if (current.data.equals(data)) {
                result = current;
                break;
            }

            current = current.getNext(level);
        }

        return result;
    }

    void insert(SkipNode<N> skipNode, int level) {
        SkipNode<N> current = this.getNext(level);
        if (current == null) {
            this.setNext(skipNode, level);
            return;
        }

        if (skipNode.data.compareTo(current.data) < 1) {
            this.setNext(skipNode, level);
            skipNode.setNext(current, level);
            return;
        }

        while (current.getNext(level) != null
                && current.data.compareTo(skipNode.data) < 1 &&
                current.getNext(level).data.compareTo(skipNode.data) < 1) {

            current = current.getNext(level);
        }

        SkipNode<N> successor = current.getNext(level);
        current.setNext(skipNode, level);
        skipNode.setNext(successor, level);
    }

    void print(int level) {

        int length = 0;
        SkipNode<N> current = this.getNext(level);
        System.out.print("level " + level + ": [ ");

        while (current != null) {
            length++;
            System.out.print(current.data + " ");
            current = current.getNext(level);
        }
        System.out.println("], length: " + length);
    }

}

public class SkipList<T extends Comparable<? super T>> implements SkippableList<T> {

    private final SkipNode<T> head = new SkipNode<>(null);
    private final Random rand = new Random();

    @Override
    public void insert(T data) {
        SkipNode<T> skipNode = new SkipNode<>(data);
        for (int i = 0; i < LEVELS; i++) {
            if (rand.nextInt((int) Math.pow(2, i)) == 0) {
                //insert with prob = 1/(2^i)
                insert(skipNode, i);
            }
        }
    }

    @Override
    public boolean delete(T target) {
        int level = LEVELS;
        System.out.println("Deleting " + target);
        SkipNode<T> victim = search(target, true);
        if (victim == null) return false;
        victim.data = null;
        for (int i = 0; i < level; i++) {
            head.refreshAfterDelete(i);
        }

        System.out.println("deleted...");
        return true;
    }

    @Override
    public SkipNode<T> search(T data) {
        return search(data, true);
    }

    @Override
    public void print() {
        int level = LEVELS;
        for (int i = level-1; i >= 0 ; i--) {
            head.print(i);
        }
        System.out.println();
    }

    private void insert(SkipNode<T> SkipNode, int level) {
        head.insert(SkipNode, level);
    }

    private SkipNode<T> search(T data, boolean print) {
        SkipNode<T> result = null;
        for (int i = LEVELS-1; i >= 0; i--) {
            if ((result = head.search(data, i, print)) != null) {
                if (print) {
                    System.out.println("Found " + data.toString()
                            + " at level " + i + ", so stopped" );
                    System.out.println();
                }
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SkipList<Integer> sl = new SkipList<>();
        int[] data = {4,2,7,0,9,1,3,7,3,4,5,6,0,2,8};
        for (int i : data) {
            sl.insert(i);
        }

        sl.print();
        sl.search(4);

        sl.delete(4);

        System.out.println("Inserting 10");
        sl.insert(10);
        sl.print();
        sl.search(10);

        Scanner s = new Scanner(System.in);
        System.out.println("Would you like to search x, delete x, or insert x? Press q to quit");

        boolean running = true;
        while (running) {
            String next = s.next();
            switch (next) {
                case "q" -> running = false;
                case "search" -> {
                    int int1 = s.nextInt();
                    System.out.println(sl.search(int1));
                    sl.print();
                }
                case "delete" -> {
                    int int1 = s.nextInt();
                    System.out.println(sl.delete(int1));
                    sl.print();
                }
                case "insert" -> {
                    int int1 = s.nextInt();
                    sl.insert(int1);
                    sl.print();
                }
                default -> System.out.println("Unknown command ");
            }
        }
    }

}

