import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        float count = 0;
        int size = 128;
        HashTable hashTable = new HashTable(size);
        File file = new File("navn.txt");

        // Read the file and add all the names into the hash table.
        try (Scanner sc = new Scanner(new File(String.valueOf(file)))){
            while (sc.hasNext()){
                Name name = new Name(sc.nextLine());
                hashTable.Add(name);
                count++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // Search for my own name (no collision)
        if (search == null){
            System.out.println("No matching names with search.");
        } else {
            System.out.println(search);
        }

        // Search for a name with collision
        if (search == null){
            System.out.println("No matching names with search.");
        } else {
            System.out.println(search);
        }

        // Print number of people in list, collisions and load factor.
        System.out.println("\nNumber of people: " + count);
        System.out.println("Collisions: " + hashTable.collisions);
        System.out.println("Collisions per person: " + (hashTable.collisions / count));
        System.out.println("Load factor: " + (count / size));
    }
}

class HashTable {
    Name[] table;
    int collisions = 0;

    public HashTable(int size){
        this.table = new Name[size];
    }

    public void Add(Name name){
        int index = name.hash(table.length);

        // If there is already a value at the key spot, link the old one to this.
        if (table[index] != null){
            collisions++;
            System.out.println(name + " Collided with " + table[index].toString());
            name.linkNext(table[index]);
        }
        table[index] = name;
    }

    public Name findName(String name){
        Hash hash = new Hash();
        // Hash the searched name to get its key value
        int hashValue = hash.multiplicationHash(name, 17, table.length);

        // If there is nothing at key value, return null.
        if (table[hashValue] == null){
            return null;
        } else {

            // Otherwise, try if the first name in the spot of the key value equals the search term.
            if (table[hashValue].getName().equals(name)){
                return table[hashValue];
            } else {

                // If not, try all the proceeding links from the original key value.
                Name check = table[hashValue];
                while (check.next != null) {
                    System.out.println("The search collided with: " + check);
                    if (check.next.getName().equals(name)){
                        return check.next;
                    } else {
                        check = check.next;
                    }
                }
            }
        }
        return null;
    }
}

/**
 * Contains the name of a person as string, its hash value, and the next name in line (making it work
 * like a singly linked list in case of collisions).
 */
class Name {
    private final String name;
    private final Hash hash = new Hash();
    Name next;

    public Name(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int hash(int size){
        return hash.multiplicationHash(this.name,17, size);
    }

    public void linkNext(Name next){
        this.next = next;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class Hash {
    static int A = 1327217885;

    public int getIntFromString(String name){
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            int c = name.charAt(i);
            sum += (i*100)*c;
        }
        return sum;
    }

    public int multiplicationHash(String name, int x, int size){
        System.out.println(getIntFromString(name) * A >> (32-x) & (size - 1));
        return getIntFromString(name) * A >> (32-x) & (size - 1);
    }

    public int restDivisionHash(String name, int size){
        return (getIntFromString(name) % size);
    }
}