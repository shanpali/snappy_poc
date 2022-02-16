package abcd;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import org.xerial.snappy.Snappy;

import java.io.IOException;

public class testSnappy {
    String input = "{\"menu\": {\n" +
            "    \"header\": \"SVG Viewer\",\n" +
            "    \"items\": [\n" +
            "        {\"id\": \"Open\"},\n" +
            "        {\"id\": \"OpenNew\", \"label\": \"Open New\"},\n" +
            "        null,\n" +
            "        {\"id\": \"ZoomIn\", \"label\": \"Zoom In\"},\n" +
            "        {\"id\": \"ZoomOut\", \"label\": \"Zoom Out\"},\n" +
            "        {\"id\": \"OriginalView\", \"label\": \"Original View\"},\n" +
            "        null,\n" +
            "        {\"id\": \"Quality\"},\n" +
            "        {\"id\": \"Pause\"},\n" +
            "        {\"id\": \"Mute\"},\n" +
            "        null,\n" +
            "        {\"id\": \"Find\", \"label\": \"Find...\"},\n" +
            "        {\"id\": \"FindAgain\", \"label\": \"Find Again\"},\n" +
            "        {\"id\": \"Copy\"},\n" +
            "        {\"id\": \"CopyAgain\", \"label\": \"Copy Again\"},\n" +
            "        {\"id\": \"CopySVG\", \"label\": \"Copy SVG\"},\n" +
            "        {\"id\": \"ViewSVG\", \"label\": \"View SVG\"},\n" +
            "        {\"id\": \"ViewSource\", \"label\": \"View Source\"},\n" +
            "        {\"id\": \"SaveAs\", \"label\": \"Save As\"},\n" +
            "        null,\n" +
            "        {\"id\": \"Help\"},\n" +
            "        {\"id\": \"About\", \"label\": \"About Adobe CVG Viewer...\"}\n" +
            "    ]\n" +
            "}}";
    byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
    byte[] uncompressed = Snappy.uncompress(compressed);

    String result = new String(uncompressed, "UTF-8");

    public testSnappy() throws IOException {
        System.out.println("result");
    }

    LZ4Factory factory = LZ4Factory.fastestInstance();
    byte[] data = input.getBytes("UTF-8");
    final int decompressedLength = data.length;

    // compress data
    LZ4Compressor compressor = factory.fastCompressor();
    int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
    byte[] compressedlz4 = new byte[maxCompressedLength];
    int compressedLength = compressor.compress(data, 0, decompressedLength, compressedlz4, 0, maxCompressedLength);



    public static void main(String[] args) throws IOException {
        testSnappy test1 = new testSnappy();
        System.out.println("length before snappy : "+(test1.uncompressed).length);
        System.out.println("compressed using snappy : "+(test1.compressed).length);
        System.out.println("size reduced by snappy : "+ (100-(((test1.compressed).length)*100)/(test1.uncompressed).length) +"%");

        System.out.println();

        System.out.println("length before LZ4 : "+test1.decompressedLength);
        System.out.println("compressed using LZ4 : "+test1.compressedLength);
        System.out.println("size reduced by LZ4 : "+ (100-(test1.compressedLength*100)/(test1.decompressedLength) +"%"));

    }


}
