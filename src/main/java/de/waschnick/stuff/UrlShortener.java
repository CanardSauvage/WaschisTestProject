package de.waschnick.stuff;

import org.apache.xerces.impl.dv.util.Base64;

import java.util.zip.Deflater;

public class UrlShortener {

    public static void main(String[] args) throws Exception {
        String longUrl = "https://secure.mypass.de/sso-signin-server/web/passwordrecovery?" + "redirect=true&service=http%253A%252F%252Flocalhost%253A8080%252Ftest3app%252Fweb%252Fregister%252Fregister.jsp%26security%3Dlow&token=3553c93e16a647cb8be943fd722ee090";
        String partToShorten = "redirect=true&service=http%253A%252F%252Flocalhost%253A8080%252Ftest3app%252Fweb%252Fregister%252Fregister.jsp%26security%3Dlow&token=3553c93e16a647cb8be943fd722ee090";
        // FIXME Remove System.out
        System.out.println("Part to shorten: " + partToShorten);
        String compressedPart = compress(partToShorten);
        String compressed = "https://secure.mypass.de/sso-signin-server/short/pw/" + compressedPart;

// FIXME Remove System.out
        System.out.println("Long : " + longUrl);
        System.out.println("Short: " + compressed);
        System.out.println("Size : " + compressed.length());
    }

    public static String compress(String inputString) throws Exception {
        byte[] input = inputString.getBytes("UTF-8");

        // Compress the bytes
        byte[] output = new byte[100];
        Deflater compresser = new Deflater();
        compresser.setInput(input);
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        compresser.end();

        // Decompress the bytes
//        Inflater decompresser = new Inflater();
//        decompresser.setInput(output, 0, compressedDataLength);
//        byte[] result = new byte[100];
//        int resultLength = decompresser.inflate(result);
//        decompresser.end();
//
//        String outputString = new String(result, 0, resultLength, "UTF-8");

        return Base64.encode(output);
    }

//    public static String decompress(String str) throws Exception {
//        if (str == null || str.length() == 0) {
//            return str;
//        }
//        System.out.println("Input String length : " + str.length());
//        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str.getBytes("UTF-8")));
//        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
//        String outStr = "";
//        String line;
//        while ((line = bf.readLine()) != null) {
//            outStr += line;
//        }
//        System.out.println("Output String lenght : " + outStr.length());
//        return outStr;
//    }

}
