# Introduction #

This is a reference project demonstrating the use of Lucene (Information Retrieval) and Spring Integration(SI).  The project only looks at the indexing part of Lucene using SI, searching the index is not covered.  A search component can be wired up to look at the directory where the indices are stored.


# Details #

You will need to checkout the following project and build from source. This will place mock-runner artifact in you maven repository:

http://code.google.com/p/mock-annotation/


The project is built using maven and developed on Eclipse.  If you wish to import the project into eclipse run "mvn eclipse:eclipse". This will set up the necessary .project files for the IDE.

To run the main demo of the reference project you will need to do the following:

  * Under src/test/resources Populate the environment.properties to include:
    1. path.index = Your preferred location of index e.g. /User/xyz/tmp/lucene
    1. jms.server.url = You will need to have activemq/messaging broker url
    1. mail.smtp.host
    1. mail.smtp.port
    1. mail.username
    1. mail.password

  * Run messaging broker (activemq was used for the project)

  * If you wish to run the LuceneIndexingIntegrationDemo class you will require an environment.properties file to be included in the classpath.  The details of what should be included are similar to the ones described above. In the run configuration of LuceneIndexingIntegrationDemo pass in a VM argument.  This argument must be either a file or directory and must be in the format of "[file:///Users/xyz/files](file:///Users/xyz/files)" or "[file:///Users/xyz/files/file1.pdf](file:///Users/xyz/files/file1.pdf)"

The mail settings are used for handling any errors that occur during the indexing operation. You can configure your gmail account  or use Apache James.

It is important to note that this project is concerned with file indexing.  I am using Apache Tika to extract content from the file(s) so there is no special configuration that is required for file extensions.  Apache Tika uses its built in mechanism to determine the particular file type and loads the correct library to extract text from the file.