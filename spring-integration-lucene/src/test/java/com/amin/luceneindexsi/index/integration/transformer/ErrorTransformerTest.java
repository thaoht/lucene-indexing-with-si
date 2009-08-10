package com.amin.luceneindexsi.index.integration.transformer;


import static org.junit.Assert.assertNotNull;

import org.apache.lucene.document.Document;
import org.junit.Before;
import org.junit.Test;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessagingException;
import org.springframework.integration.message.ErrorMessage;
import org.springframework.integration.message.GenericMessage;
import org.springframework.mail.MailMessage;

import com.amin.luceneindexsi.index.integration.transformer.ErrorTransformer;

public class ErrorTransformerTest {

	private ErrorTransformer underTest;
	
	@Before
	public void setUp() throws Exception {
		underTest = new ErrorTransformer();
	}
	
	@Test
	public void testCanTransformErrorMessageIntoMailMessage() {
		RuntimeException exception = new RuntimeException("message");
		Message<Document> message = new GenericMessage<Document>(new Document());
		MessagingException messagingException = new MessagingException(message,exception);
		ErrorMessage errorMessage = new ErrorMessage(messagingException);
		MailMessage mailMessage = underTest.transformError(errorMessage);
		assertNotNull(mailMessage);
	}
}
