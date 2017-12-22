package masr.common;

import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileHelperTest {
    @Test
    public void getHash() throws Exception {
        assertEquals("3A83337688E10A251F2C67EE1897A715", FileHelper.getHash(Paths.get(getClass().getClassLoader().getResource("hash-test.txt").toURI()).toString()));
    }

}