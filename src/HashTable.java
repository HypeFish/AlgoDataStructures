import java.util.*;

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
    private LinkedList<HashNode<String, Integer>> bucketArray;

    // Current capacity of array list
    private int numBuckets;

    // Current size of array list
    private int size;

    //List of keys
    private final LinkedList<String> keyList;

    //Number of collisions
    int numberOfCollisions;

    //List of collisions
    LinkedList<Map<java.lang.String, java.lang.Integer>> collisions;


    // Constructor Initializes capacity, size and
    // empty chains.
    public HashTable() {
        keyList = new LinkedList<>();
        bucketArray = new LinkedList<>();
        int random = new Random().nextInt(3);
        switch (random) {
            case 0 -> numBuckets = 30;
            case 1 -> numBuckets = 300;
            case 2 -> numBuckets = 1000;
            default -> numBuckets = 0;
        }
        size = 0;
        numberOfCollisions = 0;

        collisions = new LinkedList<>();

        // Create empty chains
        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }

    public LinkedList<String> listKeys() {
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

        // Search for key in its chain
        HashNode<String, Integer> prev = null;
        while (head != null) {
            // If Key found
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;

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
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);

        keyList.remove();
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
                Map<java.lang.String, java.lang.Integer> pair = new TreeMap<>();
                pair.put((java.lang.String) key, (java.lang.Integer) value);
                collisions.add(pair);
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
        keyList.add(key);

        bucketArray.set(bucketIndex, newNode);

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0 * size) / numBuckets >= 0.7) {
            LinkedList<HashNode<String, Integer>> temp = bucketArray;
            bucketArray = new LinkedList<>();
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

    // Driver method to test HashTable class
    public static void main(java.lang.String[] args) {
        HashTable<java.lang.String, java.lang.Integer> map = new HashTable<>();
        map.add("this", 1);
        map.add("coder", 2);
        map.add("this", 4);
        map.add("hi", 5);
        map.add("hi", 2);
        map.add("dog", 5);
        System.out.println(map.numberOfCollisions);
        System.out.println(map.collisions);
        System.out.println(map.size());
        System.out.println(map.listKeys());
        System.out.println(map.remove("this"));
        System.out.println(map.remove("this"));
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        System.out.println(map.listKeys());
        System.out.println(map.get("hi"));
        map.increase("hi");
        System.out.println(map.get("hi"));
    }
}


