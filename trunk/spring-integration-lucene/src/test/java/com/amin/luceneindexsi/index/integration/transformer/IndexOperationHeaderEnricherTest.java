package com.amin.luceneindexsi.index.integration.transformer;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.apache.lucene.document.Document;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.integration.core.Message;
import com.amin.luceneindexsi.index.document.DocumentMetaDataInformation;
import com.amin.mock.annotation.MockeryContext;
import com.amin.mock.annotation.Mocked;

@RunWith(com.amin.mock.jmock.runner.JMockRunner.class)
public class IndexOperationHeaderEnricherTest {

    private IndexOperationHeaderEnricher underTest;
    @Mocked private DocumentMetaDataInformation documentInfoMetaData;
    @MockeryContext(useClassOverInterface = true)private Mockery mockery;
    private Document document;

    @Before
    public void createSUT(){
        document = new Document();
        underTest = new IndexOperationHeaderEnricher(documentInfoMetaData);
    }

    @Test
    public void indexOperationHeaderShouldBeAddedToMessage(){
        mockery.checking(new Expectations(){{
            oneOf(documentInfoMetaData).indexOperationFor(document); will(returnValue(DocumentMetaDataInformation.IndexOperation.ADD));
        }});

        Message<?> message = underTest.transform(MessageBuilder.withPayload(document).build());

        assertEquals(document, message.getPayload());
        assertEquals(DocumentMetaDataInformation.IndexOperation.ADD.name().toLowerCase(),
                message.getHeaders().get(IndexOperationHeaderEnricher.INDEX_OPERATION_HEADER_NAME));

        mockery.assertIsSatisfied();

    }

}
