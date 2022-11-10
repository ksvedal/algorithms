import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LempelZiv lempelZiv = new LempelZiv();

        String original = "oppgavetekst.pdf";
        String compressed = "compressed_oppgavetekst_pdf";
        String deCompressed = "decompressed_oppgavetekst.pdf";

        lempelZiv.compress(original, compressed);
        lempelZiv.decompress(compressed, deCompressed);
    }
}