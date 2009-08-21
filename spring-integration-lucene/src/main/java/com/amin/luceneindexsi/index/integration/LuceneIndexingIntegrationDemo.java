package com.amin.luceneindexsi.index.integration;

import java.net.URI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amin.luceneindexsi.index.integration.gateway.IndexingGateway;

public class LuceneIndexingIntegrationDemo {


    /**
     * Main method for running Lucene Indexing with Spring Integration.
     * Usage: Add URI of file or directory as a VM argument. For example "file:///user/temp/filelocation/file.pdf" or
     * "file:///user/temp/file-directory".
     * If you have configured the maill settings correctly then any exception thrown 
     * in the process will result in an email sent to your mail account stating the exception and cause.
     * @param args  Arguments to be passed. First argument is the URI of file or directory.
     * @throws Exception Any exception thrown from the process.
     */
	public static void main (String[] args) throws Exception {
		if (args.length == 0) {
			System.err.println("usage: specify uri of file or directory");
			System.exit(1);
		}
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"classpath:context-index-integration.xml","classpath:context-index-jms.xml", "classpath:context-index-writer.xml","classpath:context-index-directory.xml"});
		URI uri = new URI(args[0]);
		IndexingGateway indexingGateway = (IndexingGateway) ctx.getBean("indexingGateway");
		indexingGateway.processFileForIndex(uri);
	}
}
