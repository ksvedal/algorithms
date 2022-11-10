import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LempelZiv lempelZiv = new LempelZiv();

        String original = "forelesning.lyx";
        String compressed = "compressed_forelesning.lyx";
        String deCompressed = "decompressed_forelesning.lyx";

        lempelZiv.compress(original, compressed);
        lempelZiv.decompress(compressed, deCompressed);
    }
}