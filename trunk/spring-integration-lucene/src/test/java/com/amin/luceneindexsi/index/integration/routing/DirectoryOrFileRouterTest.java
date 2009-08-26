package com.amin.luceneindexsi.index.integration.routing;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: amin mohammed-coleman
 * Date: Aug 24, 2009
 * Time: 8:03:56 PM
 */
public class DirectoryOrFileRouterTest {

    private DirectoryOrFileRouter underTest;
    private static final String CURRENT_WORKING_DIR = System.getProperty("user.dir");

    @Before
    public void setUp() {
        underTest = new DirectoryOrFileRouter();
        underTest.setDirectoryRoute("isDirectoryChannel");
        underTest.setFileRoute("isFileChannel");

    }

    @Test
    public void testDirectoryChannelReturnedIfUriIsDirectory() throws Exception {
        URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/");
        String channel = underTest.isDirectory(uri);
        assertNotNull(channel);
        assertEquals("isDirectoryChannel", channel);

    }

    @Test
    public void testFileChannelIsReturnedIfUriIsFile() throws Exception {
        URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/springsecurity.pdf");
        String channel = underTest.isDirectory(uri);
        assertNotNull(channel);
        assertEquals("isFileChannel", channel);

    }
}
