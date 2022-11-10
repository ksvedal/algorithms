import java.io.*;
import java.util.ArrayList;

public class LempelZiv {

    private final int minimumLength = 3;

    public void compress(String from, String to) throws IOException {

        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(from)));
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(to));

        // puts the input as a byte block into a byteArray represented by its decimal value (-128 to 127)
        byte[] fileAsBytes = inputStream.readAllBytes();

        ArrayList<Byte> compressed = new ArrayList<>();
        byte unchanged = minimumLength;
        int i = minimumLength;

        // Until end of the byte Array
        while (i < fileAsBytes.length) {
            boolean foundMatch = false;
            short distanceBuffer = 0;
            byte foundLengthByte = 0;
            short temporaryDistance = 3;

            // Until the temp distance reaches the index or the max value of the Short datatype (32 767)
            while (temporaryDistance <= i && temporaryDistance < Short.MAX_VALUE) {

                // Checks if byte in current distance has match with the current index
                if (fileAsBytes[i - temporaryDistance] == fileAsBytes[i]) {
                    int matchLength = 1;

                    // Until max value of a byte (127)
                    while (matchLength < temporaryDistance && matchLength < Byte.MAX_VALUE && i + matchLength < fileAsBytes.length) {

                        // checks the two bytes and adds to the match length unless they differ.
                        if (fileAsBytes[i + matchLength] == fileAsBytes[matchLength + (i - temporaryDistance)]) {
                            matchLength++;
                        }
                        else {
                            break;
                        }
                    }

                    // Tags the current bytes as match and adds foundLengthByte and distanceBuffer.
                    if (matchLength > foundLengthByte && matchLength > minimumLength) {
                        foundMatch = true;
                        foundLengthByte = (byte)matchLength;
                        distanceBuffer = temporaryDistance;
                        if (foundLengthByte == Byte.MAX_VALUE) {
                            break;
                        }
                    }
                }
                temporaryDistance++;
            }

            // If there has been a match between two bytes
            if (foundMatch) {

                // Firstly empties the unchanged bytes into the compressed file.
                if (unchanged != 0) {
                    compressed.add(unchanged);
                    for (int j = i - unchanged; j < i; j++) compressed.add(fileAsBytes[j]);
                    unchanged = 0;
                }

                // Adds the negative signed byte value signaling that it has been compressed and is in dictionary.
                compressed.add((byte)(-foundLengthByte));

                // Divides the distance short into four bytes and adds one byte to the compressed ArrayList at a time.
                compressed.add((byte)(distanceBuffer & 0xff));        // First half
                compressed.add((byte)((distanceBuffer >> 8) & 0xff)); // Second half

                // The two next lines is if we use int ( 4 byte blocks ) which made the implementation worse.
                    // compressed.add((byte)((distanceBuffer >> 16) & 0xff));
                    // compressed.add((byte)((distanceBuffer >> 24) & 0xff));

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
            i++;
        }

        // In the end, add the unchanged bytes to the end of the compressed file (if there is any left).
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
        int distance;

        // Iterates over all the data bytes.
        while (totalIndex < fileAsBytes.length) {

            // x = byte in index
            byte currentByte = fileAsBytes[totalIndex];

            // If the byte is positive (a repeated sequence) just adds the byte.
            if (currentByte >= 0) {
                int i = totalIndex + 1;
                while (i <= totalIndex + currentByte && i < fileAsBytes.length) {
                    decompressed.add(fileAsBytes[i]);
                    currentIndex++;
                    i++;
                }
                totalIndex += currentByte+1;
            }

            // Otherwise, if the byte is negative, signals that there is a match hit.
            // currentIndex and distance then indicates the reference.
            else {

                // Combines the next two bytes into a short.
                distance = (short)((fileAsBytes[totalIndex + 1] & 0xff) | ((fileAsBytes[totalIndex + 2] & 0xff) << 8));

                // The next line is if we use int ( 4 byte blocks ) which made the implementation worse.
                // distance = (int)((fileAsBytes[totalIndex + 1] & 0xff) | ((fileAsBytes[totalIndex + 2] & 0xff) << 8) | ((fileAsBytes[totalIndex + 3] & 0xff) << 16) | ((fileAsBytes[totalIndex + 4] & 0xff) << 24));

                // Start
                int start = currentIndex;
                int i = currentIndex - distance;

                // Adds the bytes that has been pointed to.
                while (i < start - distance - currentByte) {
                    decompressed.add(decompressed.get(i));
                    currentIndex++;
                    i++;
                }

                // Adds the size of the byte section to index for the next iteration.
                totalIndex += (minimumLength);
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