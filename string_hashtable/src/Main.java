import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        float count = 0;
        int size = 127;
        HashTable hashTable = new HashTable(size);
        File file = new File("navn.txt");

        // Read the file and add all the names into the hash table.
        try (Scanner sc = new Scanner(new File(String.valueOf(file)))){
            while (sc.hasNext()){
                hashTable.addName(sc.nextLine());
                count++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        hashTable.printTable();

        // Search for a name with multiple names in the same index.
        System.out.println("\n");
        Name search = hashTable.findName("[REDACTED]");
        if (search == null){
            System.out.println("No matching names with search.");
        } else {
            System.out.println("Found name: " + search);
        }
        System.out.println();

        // Print number of people in list, collisions and load factor.
        System.out.println("Number of people: " + count);
        System.out.println("Collisions: " + hashTable.collisions);
        System.out.println("Collisions per person: " + (hashTable.collisions / count));
        System.out.println("Load factor: " + (count / size));
    }
}

/**
 * A table of names indexed by hashing.
 * If duplicate hashed values, the duplicated names will link to each other.
 */
class HashTable {
    Name[] table;
    int collisions = 0;

    /**
     * Hash table of names.
     * @param size Size of table.
     */
    public HashTable(int size){
        this.table = new Name[size];
    }

    /**
     * Adds name to the hash table using hashing function.
     * If the index hashed value is a duplicate, will take the place and link to the old one.
     * @param nameToAdd Name to add.
     */
    public void addName(String nameToAdd){
        Name name = new Name(nameToAdd);
        int index = name.hashName(table.length);

        // If there is already a value at the key spot, link the old one to this.
        if (table[index] != null){
            collisions++;
            System.out.println(nameToAdd + " Collided with " + table[index].toString());
            name.linkNext(table[index]);
        }
        table[index] = name;
    }

    /**
     * Finds exact name by string.
     * @param nameToSearch Name to search for.
     * @return null if no match. String of name if matched.
     */
    public Name findName(String nameToSearch){
        Name name = new Name(nameToSearch);
        // Hash the searched name to get its key value
        int hashValue = name.hashName(table.length);

        // If there is nothing at key value, return null.
        if (table[hashValue] == null){
            return null;
        } else {

            // Otherwise, try if the first name in the spot of the key value equals the search term.
            if (table[hashValue].getName().equals(nameToSearch)){
                return table[hashValue];
            } else {

                // If not, try all the proceeding links from the original key value.
                Name check = table[hashValue];
                while (check.next != null) {
                    System.out.println("The search collided with: " + check);
                    if (check.next.getName().equals(nameToSearch)){
                        return check.next;
                    } else {
                        check = check.next;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Prints the entire table with indicators for the names that point to other names.
     */
    public void printTable() {
        int i = 1;
        for (Name name : table) {
            Name check = name;
            System.out.print("\n   On index: " + i + ": ");
            i++;
            if (name != null) {
                System.out.print(name);
                while (check.next != null) {
                    check = check.next;
                    System.out.print(",      -->       " + check);
                }
            }
        }
    }
}

/**
 * Contains the name of a person as string and the next name in line (making it work
 * like a singly linked list in case of collisions).
 */
class Name {
    private final String name;
    Name next;

    /**
     * The full name of a person.
     * @param name Name of a person.
     */
    public Name(String name){
        this.name = name;
    }

    /**
     * Returns name
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Links the next name to this.
     * @param next name to link.
     */
    public void linkNext(Name next){
        this.next = next;
    }

    /**
     * Modular hashing function using a prime number to avoid common factors between different numbers.
     * @param size Size of table.
     * @return Number result from hashing the name.
     */
    public int hashName(int size) {
        int hash = 0;
        for (int i = 0; i < this.name.length(); i++) {
            hash = (11 * hash + this.name.charAt(i)) % size;
        }
        return hash;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
