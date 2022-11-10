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
            short temporaryDistance = 3;

            // Loops until the max value of the Short datatype (32 767)
            while (temporaryDistance <= i && temporaryDistance < Short.MAX_VALUE) {

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

                    // Tags the current bytes as in the dictionary and adds foundLengthByte and distanceBuffer.
                    if (foundLengthInt > foundLengthByte && foundLengthInt > minimumLength) {
                        foundInDictionary = true;
                        foundLengthByte = (byte)foundLengthInt;
                        distanceBuffer = temporaryDistance;
                        if (foundLengthByte == Byte.MAX_VALUE) {
                            break;
                        }
                    }
                }
                temporaryDistance++;
            }
            if (foundInDictionary) {
                if (unchanged != 0) {
                    compressed.add(unchanged);
                    for (int j = i - unchanged; j < i; j++) {
                        compressed.add(fileAsBytes[j]);
                    }
                    unchanged = 0;
                }

                // Adds the negative signed byte value signaling that it has been compressed and is in dictionary.
                compressed.add((byte)(-foundLengthByte));

                // Divides the distance short into two bytes and adds one byte to the compressed ArrayList at a time.
                compressed.add((byte)(distanceBuffer & 0xff));        // First half
                compressed.add((byte)((distanceBuffer >> 8) & 0xff)); // Second half

                i += ((int)(foundLengthByte) - 1);
            } else {
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

        byte[] fileAsBytes = inputStream.readAllBytes();

        ArrayList<Byte> decompressed = new ArrayList<>();

        int currentIndex = 0;
        int totalIndex = 0;
        short distance;

        // Iterates over all the data bytes.
        while (totalIndex < fileAsBytes.length) {

            // x = byte in index
            byte currentByte = fileAsBytes[totalIndex];

            // If the byte is positive (a repeated sequence) just adds the byte.
            if (currentByte >= 0) {
                int j = totalIndex + 1;
                while (j <= totalIndex + currentByte && j < fileAsBytes.length) {
                    decompressed.add(fileAsBytes[j]);
                    currentIndex++;
                    j++;
                }
                totalIndex += currentByte;
            }

            // Otherwise, if the byte is negative, signals that the next two bytes have been compressed.
            // currentByte then acts as a pointer to that part of the dictionary.
            else {
                // Combines the next two bytes into a short.
                distance = (short)((fileAsBytes[totalIndex + 1] & 0xff) | ((fileAsBytes[totalIndex + 2] & 0xff) << 8));

                // Starts at the start
                int start = currentIndex;
                int j = currentIndex - distance;

                // Adds the bytes from the dictionary (j) to the decompressed ArrayList.
                while (j < start - distance - currentByte) {
                    decompressed.add(decompressed.get(j));
                    currentIndex++;
                    j++;
                }

                // Adds the size of the byte section to index for the next iteration.
                totalIndex += (minimumLength - 1);
            }
            totalIndex++;
        }

        // Writes the decompressed bytes to file.
        for (Byte _byte : decompressed) {
            outputStream.writeByte(_byte);
        }

        outputStream.flush();
        outputStream.close();
    }

}