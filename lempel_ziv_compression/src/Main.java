import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LempelZiv lempelZiv = new LempelZiv();

        String original = "test.lyx";
        String compressed = "test2";
        String deCompressed = "test3.lyx";

        lempelZiv.compress(original, compressed);
        lempelZiv.decompress(compressed, deCompressed);
    }
}