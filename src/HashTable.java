import com.sun.source.tree.ForLoopTree;
import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

// Java program to implement
// a Singly Linked List
class MyLinkedList<T> extends LinkedList<T> {

    Node head; // head of list

    // Linked list Node.
    // Node is a static nested class
    // so main() can access it
    static class Node {

        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // **************INSERTION**************

    // Method to insert a new node
    public MyLinkedList<T> insert(MyLinkedList<T> list,
                                  int data) {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        // Return the list by head
        return list;
    }

    // **************TRAVERSAL**************

    // Method to print the LinkedList.
    public void printList(MyLinkedList<T> list) {
        Node currNode = list.head;

        System.out.print("\nLinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + " ");

            // Go to next node
            currNode = currNode.next;
        }
        System.out.println("\n");
    }

    // **************DELETION BY KEY**************

    // Method to delete a node in the LinkedList by KEY
    public MyLinkedList<T> deleteByKey(MyLinkedList<T> list,
                                       int key) {
        // Store head node
        Node currNode = list.head, prev = null;

        //
        // CASE 1:
        // If head node itself holds the key to be deleted

        if (currNode != null && currNode.data == key) {
            list.head = currNode.next; // Changed head

            // Display the message
            System.out.println(key + " found and deleted");

            // Return the updated List
            return list;
        }

        //
        // CASE 2:
        // If the key is somewhere other than at head
        //

        // Search for the key to be deleted,
        // keep track of the previous node
        // as it is needed to change currNode.next
        while (currNode != null && currNode.data != key) {
            // If currNode does not hold key
            // continue to next node
            prev = currNode;
            currNode = currNode.next;
        }

        // If the key was present, it should be at currNode
        // Therefore the currNode shall not be null
        if (currNode != null) {
            // Since the key is at currNode
            // Unlink currNode from linked list
            prev.next = currNode.next;

            // Display the message
            System.out.println(key + " found and deleted");
        }

        //
        // CASE 3: The key is not present
        //

        // If key was not present in linked list
        // currNode should be null
        if (currNode == null) {
            // Display the message
            System.out.println(key + " not found");
        }

        // return the List
        return list;
    }

    // **************DELETION AT A POSITION**************

    // Method to delete a node in the LinkedList by POSITION
    public MyLinkedList<T> deleteAtPosition(MyLinkedList<T> list, int index) {
        // Store head node
        Node currNode = list.head, prev = null;

        //
        // CASE 1:
        // If index is 0, then head node itself is to be
        // deleted

        if (index == 0 && currNode != null) {
            list.head = currNode.next; // Changed head

            // Display the message
            System.out.println(
                    index + " position element deleted");

            // Return the updated List
            return list;
        }

        //
        // CASE 2:
        // If the index is greater than 0 but less than the
        // size of LinkedList
        //
        // The counter
        int counter = 0;

        // Count for the index to be deleted,
        // keep track of the previous node
        // as it is needed to change currNode.next
        while (currNode != null) {

            if (counter == index) {
                // Since the currNode is the required
                // position Unlink currNode from linked list
                prev.next = currNode.next;

                // Display the message
                System.out.println(
                        index + " position element deleted");
                break;
            } else {
                // If current position is not the index
                // continue to next node
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }

        // If the position element was found, it should be
        // at currNode Therefore the currNode shall not be
        // null
        //
        // CASE 3: The index is greater than the size of the
        // LinkedList
        //
        // In this case, the currNode should be null
        if (currNode == null) {
            // Display the message
            System.out.println(
                    index + " position element not found");
        }

        // return the List
        return list;
    }

    // **************MAIN METHOD**************

    // method to create a Singly linked list with n nodes
    public static void main(String[] args) {
        /* Start with the empty list. */
        MyLinkedList<Integer> list = new MyLinkedList<>();

        //
        // ******INSERTION******
        //

        // Insert the values
        list = list.insert(list, 1);
        list = list.insert(list, 2);
        list = list.insert(list, 3);
        list = list.insert(list, 4);
        list = list.insert(list, 5);
        list = list.insert(list, 6);
        list = list.insert(list, 7);
        list = list.insert(list, 8);

        // Print the LinkedList
        list.printList(list);

        //
        // ******DELETION BY KEY******
        //

        // Delete node with value 1
        // In this case the key is ***at head***
        list.deleteByKey(list, 1);

        // Print the LinkedList
        list.printList(list);

        // Delete node with value 4
        // In this case the key is present ***in the
        // middle***
        list.deleteByKey(list, 4);

        // Print the LinkedList
        list.printList(list);

        // Delete node with value 10
        // In this case the key is ***not present***
        list.deleteByKey(list, 10);

        // Print the LinkedList
        list.printList(list);

        //
        // ******DELETION AT POSITION******
        //

        // Delete node at position 0
        // In this case the key is ***at head***
        list.deleteAtPosition(list, 0);

        // Print the LinkedList
        list.printList(list);

        // Delete node at position 2
        // In this case the key is present ***in the
        // middle***
        list.deleteAtPosition(list, 2);

        // Print the LinkedList
        list.printList(list);

        // Delete node at position 10
        // In this case the key is ***not present***
        list.deleteAtPosition(list, 10);

        // Print the LinkedList
        list.printList(list);
    }
}


// A node of chains
class HashNode<String, Integer> {
    java.lang.String key;
    java.lang.Integer value;
    final int hashCode;

    // Reference to next node
    HashNode<String, Integer> next;

    // Constructor
    public HashNode(java.lang.String key, java.lang.Integer value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}

// Class to represent entire hash table
public class HashTable<String, Integer> {

    // bucketArray is used to store array of chains
    private MyLinkedList<HashNode<String, Integer>> bucketArray;

    // Current capacity of array list
    private int numBuckets;

    // Current size of array list
    private int size;

    //List of keys
    private final MyLinkedList<java.lang.String> keyList;

    //Number of collisions
    int numberOfCollisions;

    //List of collisions
    MyLinkedList<Map<java.lang.String, java.lang.Integer>> collisions;
    Map<java.lang.String, java.lang.Integer> frequency;




    // Constructor Initializes capacity, size and
    // empty chains.
    public HashTable() {
        keyList = new MyLinkedList<>();
        bucketArray = new MyLinkedList<>();
        //numBuckets = 30;
        //numBuckets = 300;
        numBuckets = 1000;
        size = 0;
        numberOfCollisions = 0;
        collisions = new MyLinkedList<>();
        frequency = new TreeMap<>();

        // Create empty chains
        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }

    public LinkedList<java.lang.String> listKeys() {
        return new LinkedList<>(keyList);
    }

    public void increase(String key) {
        java.lang.Integer value = this.get(key);
        this.remove(key);
        this.add(key, (Integer) java.lang.Integer.valueOf(value + 1));

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private final int hashCode(String key) {
        return Objects.hashCode(key);
    }

    // This implements hash function to find index
    // for a key
    private int getBucketIndex(String key) {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;
        // key.hashCode() could be negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }

    // Method to remove a given key
    public Integer remove(String key) {
        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        // Get head of chain
        HashNode<String, Integer> head = bucketArray.get(bucketIndex);
        int freq = 0;

        // Search for key in its chain
        HashNode<String, Integer> prev = null;
        while (head != null) {
            // If Key found
            if (head.key.equals(key) && hashCode == head.hashCode) {
                keyList.remove(key);
                break;
            }
            // Else keep moving in chain
            prev = head;
            head = head.next;
        }

        // If key was not there
        if (head == null)
            return null;

        // Reduce size
        size--;

        // Remove key
        if (prev != null) {
            prev.next = head.next;
        }
        else
            bucketArray.set(bucketIndex, head.next);

        numberOfCollisions -= 1;
        return (Integer) head.value;
    }

    // Returns value for a key
    public java.lang.Integer get(String key) {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);

        HashNode<String, Integer> head = bucketArray.get(bucketIndex);

        // Search key in chain
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }

        // If key not found
        return null;
    }

    // Adds a key value pair to hash
    public void add(String key, Integer value) {

        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<String, Integer> head = bucketArray.get(bucketIndex);


        // Check if key is already present
        while (head != null) {

            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = (java.lang.Integer) value;
                numberOfCollisions += 1;

                HashMap<java.lang.String, java.lang.Integer> pair = new HashMap<>();
                pair.put((java.lang.String) key, (java.lang.Integer) value);
                collisions.add(pair);
                frequency = pair;
                return;
            }
            head = head.next;
        }

        // Insert key in chain
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<String, Integer> newNode
                = new HashNode<>((java.lang.String) key, (java.lang.Integer) value, hashCode);
        newNode.next = head;

        //Puts the key in the list
        keyList.add((java.lang.String) key);

        bucketArray.set(bucketIndex, newNode);

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0 * size) / numBuckets >= 0.7) {
            LinkedList<HashNode<String, Integer>> temp = bucketArray;
            bucketArray = new MyLinkedList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);

            for (HashNode<String, Integer> headNode : temp) {
                while (headNode != null) {
                    add((String) headNode.key, (Integer) headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    private int frequency(java.lang.String key) {
        ArrayList<java.lang.String> list = new ArrayList<>(keyList);
        return Collections.frequency(list, key);
    }

    public List<Map<java.lang.String, java.lang.Integer>> listCollisions() {
        List<Map<java.lang.String, java.lang.Integer>> list = new LinkedList<>();
        Map<java.lang.String, java.lang.Integer> pair = new TreeMap<>();

        for (java.lang.String key: keyList) {
            pair.put(key, frequency(key));
        }
        list.add(pair);
        return list;
    }


    // Driver method to test HashTable class
    public static void main(java.lang.String[] args) throws IOException {
//        HashTable<java.lang.String, java.lang.Integer> map = new HashTable<>();
//        map.add("this", 1);
//        map.add("coder", 2);
//        map.add("this", 4);
//        map.add("hi", 5);
//        map.add("hi", 2);
//        map.add("dog", 5);
//        System.out.println(map.numberOfCollisions);
//        System.out.println(map.collisions);
//        System.out.println(map.size());
//        System.out.println(map.listKeys());
//        System.out.println(map.remove("this"));
//        System.out.println(map.remove("this"));
//        System.out.println(map.size());
//        System.out.println(map.isEmpty());
//        System.out.println(map.listKeys());
//        System.out.println(map.get("hi"));
//        map.increase("hi");
//        System.out.println(map.get("hi"));
        // File path is passed as parameter

        HashTable<java.lang.String, java.lang.Integer> alice = new HashTable<>();
        int wordCount = 0;




        Scanner scanner = new Scanner(new BufferedInputStream(
                (new FileInputStream("alice_in_wonderland.txt"))));
        while (scanner.hasNext()) {
            alice.add(scanner.next(), wordCount);
            wordCount++;
        }



        System.out.println(alice.numberOfCollisions);
        System.out.println(alice.frequency("Alice"));
        System.out.println(alice.listCollisions());
        System.out.println(alice.listKeys());
        System.out.println(alice.size());

        System.out.println();

        System.out.println(alice.remove("Project"));
        System.out.println(alice.listKeys());
        System.out.println(alice.numberOfCollisions);
        System.out.println(alice.size());

        System.out.println();

        System.out.println(alice.remove("Alice"));
        System.out.println(alice.listKeys());
        System.out.println(alice.numberOfCollisions);
        System.out.println(alice.size());

        System.out.println();

        System.out.println(alice.get("The"));
        alice.increase("The");
        System.out.println(alice.get("The"));

    }
}



