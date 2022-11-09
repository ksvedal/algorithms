import java.io.*;
import java.util.ArrayList;

public class LempelZiv {

    private final int MIN_LENGTH = 3;

    public void decompress(String from, String to) throws IOException {
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(from)));
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(to)));

        byte[] data = inputStream.readAllBytes();

        ArrayList<Byte> decompressed = new ArrayList<>();

        int indexOut = 0;

        // Iterates over all the data bytes.
        for (int i = 0; i < data.length; i++) {

            // x = byte in index
            byte x = data[i];

            // If the byte is negative
            // Meaning its been compressed
            if (x < 0) {
                // The distance of the compressed section
                short distance = (short)((data[i + 1] & 0xff) | ((data[i + 2] & 0xff) << 8));
                // Starts at the start
                int start = indexOut;

                for (int j = start - distance; j < start - distance - x; j++) {
                    decompressed.add(decompressed.get(j));
                    indexOut++;
                }

                // Adds the size of the byte section to index for the next iteration.
                i += (MIN_LENGTH - 1);
            }

            // If the byte is positive
            else {
                for (int j = i + 1; j <= i + x && j < data.length; j++) {
                    decompressed.add(data[j]);
                    indexOut++;
                }
                i += x;
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