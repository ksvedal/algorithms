import java.io.*;
import java.util.ArrayList;

public class LempelZiv {

    private final int minimumLength = 3;

    public void compress(String from, String to) throws IOException {

        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(from)));
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(to));

        // puts the input as a byte block into a byteArray represented by its decimal value (-128 - 127)
        byte[] fileAsBytes = inputStream.readAllBytes();

        ArrayList<Byte> compressed = new ArrayList<>();
        byte unchanged = minimumLength;
        int i;

        // Loops until end of the byte Array
        for (i = minimumLength; i < fileAsBytes.length; i++) {
            boolean foundInDictionary = false;
            short distanceBuffer = 0;
            byte foundLengthByte = 0;

            // Loops until the max value of the Short datatype (32 767)
            for (short temporaryDistance = 3; temporaryDistance <= i && temporaryDistance < Short.MAX_VALUE; temporaryDistance++) {

                // Checks if byte in current index of bytearray is found in dictionary
                if (fileAsBytes[i - temporaryDistance] == fileAsBytes[i]) {
                    int foundLengthInt;

                    for (foundLengthInt = 1; foundLengthInt < temporaryDistance && foundLengthInt < Byte.MAX_VALUE && i + foundLengthInt < fileAsBytes.length;) {

                        // Finds the chars in dictionary and checks how long it lasts
                        if (fileAsBytes[i + foundLengthInt] == fileAsBytes[i - temporaryDistance + foundLengthInt]) {
                            foundLengthInt++;
                        }
                        else {
                            break;
                        }

                    }
                    if (foundLengthInt > foundLengthByte && foundLengthInt > minimumLength) {
                        foundInDictionary = true;
                        foundLengthByte = (byte)foundLengthInt;
                        distanceBuffer = temporaryDistance;
                        if (foundLengthByte == Byte.MAX_VALUE) {
                            break;
                        }
                    }
                }
            }
            if (foundInDictionary) {
                if (unchanged != 0) {
                    compressed.add(unchanged);
                    for (int j = i - unchanged; j < i; j++) {
                        compressed.add(fileAsBytes[j]);
                    }
                    unchanged = 0;
                }

                // Adds the negative signed byte value signaling that it has been compressed.
                compressed.add((byte)(-foundLengthByte));

                // Divides the distance short into two bytes and adds one byte at a time.
                compressed.add((byte)(distanceBuffer & 0xff));        // First half
                compressed.add((byte)((distanceBuffer >> 8) & 0xff)); // Second half

                i += ((int)(foundLengthByte) - 1);
            }
            else {
                unchanged++;
            }

            // If unchanged byte buffer fills up, add them to the compressed file and clear the buffer.
            if (unchanged == Byte.MAX_VALUE) {
                compressed.add(unchanged);
                for (int j = i - unchanged + 1; j <= i; j++) {
                    compressed.add(fileAsBytes[j]);
                }
                unchanged = 0;
            }
        }

        // Add the unchanged byte buffer to the end of the compressed file (if there is any left).
        compressed.add(unchanged);
        for (int j = i - unchanged; j < i; j++) {
            compressed.add(fileAsBytes[j]);
        }

        inputStream.close();

        for (Byte b : compressed)
            outputStream.write(b);

        outputStream.flush();
        outputStream.close();
    }

    public void decompress(String from, String to) throws IOException {
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(from)));
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(to)));

        byte[] data = inputStream.readAllBytes();

        ArrayList<Byte> decompressed = new ArrayList<>();

        int currentIndex = 0;
        int totalIndex = 0;

        // Iterates over all the data bytes.
        for (totalIndex = 0; totalIndex < data.length; totalIndex++) {

            // x = byte in index
            byte x = data[totalIndex];

            // If the byte is negative signals that the next two bytes have been compressed.
            if (x < 0) {
                // Combines the next two bytes into a short.
                short distance = (short)((data[totalIndex + 1] & 0xff) | ((data[totalIndex + 2] & 0xff) << 8));
                // Starts at the start
                int start = currentIndex;

                // Adds the bytes from the dictionary (j) to the decompressed ArrayList.
                for (int j = start - distance; j < start - distance - x; j++) {
                    decompressed.add(decompressed.get(j));
                    currentIndex++;
                }

                // Adds the size of the byte section to index for the next iteration.
                totalIndex += (minimumLength - 1);
            }

            // If the byte is positive (a repeated sequence) just adds the byte.
            else {
                for (int j = totalIndex + 1; j <= totalIndex + x && j < data.length; j++) {
                    decompressed.add(data[j]);
                    currentIndex++;
                }
                totalIndex += x;
            }
        }

        // Writes the decompressed bytes to file.
        for (Byte _byte : decompressed) {
            outputStream.writeByte(_byte);
        }

        outputStream.flush();
        outputStream.close();
    }

}