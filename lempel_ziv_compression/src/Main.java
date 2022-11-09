import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LempelZiv lempelZiv = new LempelZiv();

        String original = "test.txt";
        String compressed = "test2";
        String deCompressed = "test3.txt";

        lempelZiv.compress(original, compressed);
        lempelZiv.decompress(compressed, deCompressed);
    }
}