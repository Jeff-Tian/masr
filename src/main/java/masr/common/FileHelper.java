package masr.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHelper {
    public static String getHash(String filePath) throws NoSuchAlgorithmException, IOException {
        return ArrayHelper.convertBytesToHex(getHashBytes(filePath));
    }

    private static byte[] getHashBytes(String filePath) throws IOException, NoSuchAlgorithmException {
        DigestInputStream dis = new DigestInputStream(Files.newInputStream(Paths.get(filePath)), MessageDigest.getInstance("MD5"));

        readFileToEnd(dis);
        dis.close();

        return dis.getMessageDigest().digest();
    }

    private static void readFileToEnd(DigestInputStream dis) throws IOException {
        int numRead = 0;
        do {
            numRead = readChunkAndUpdate(dis);
        } while (numRead != -1);
    }

    private static int readChunkAndUpdate(DigestInputStream dis) throws IOException {
        byte[] buffer = new byte[1024];
        int numRead = dis.read(buffer);

        if (numRead > 0) {
            dis.getMessageDigest().update(buffer, 0, numRead);
        }

        return numRead;
    }
}
