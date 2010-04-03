package com.amin.luceneindexsi.index.integration.routing;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

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
    public void init() {
        underTest = new DirectoryOrFileRouter();
        underTest.setDirectoryRoute("isDirectoryChannel");
        underTest.setFileRoute("isFileChannel");

    }

    @Test
    public void directoryChannelReturnedIfUriIsDirectory() throws Exception {
        URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/");
        String channel = underTest.isDirectory(uri);
        assertNotNull(channel);
        assertEquals("isDirectoryChannel", channel);

    }

    @Test
    public void fileChannelIsReturnedIfUriIsFile() throws Exception {
        URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/springsecurity.pdf");
        String channel = underTest.isDirectory(uri);
        assertNotNull(channel);
        assertEquals("isFileChannel", channel);

    }
}
