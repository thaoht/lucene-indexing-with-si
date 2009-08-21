package com.amin.luceneindexsi.index.integration.transformer;

import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessagingException;
import org.springframework.integration.message.ErrorMessage;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;

import com.amin.luceneindexsi.index.integration.exception.IndexingException;

public class ErrorTransformer {
	
	public String transformError(ErrorMessage errorMessage) {
		Message<?> failedMessage = ((MessagingException) errorMessage.getPayload()).getFailedMessage();
		Throwable cause = ((MessagingException) errorMessage.getPayload()).getCause();
		IndexingException indexingException = new IndexingException(cause,failedMessage.getPayload());
		StringBuilder message = new StringBuilder();
		message.append("An exception occured while performing an index operation").append(", the following provides more details:\n");
		message.append("Exception message:\n").append(indexingException.getExceptionThrown().getMessage() + "\n");
		message.append("\n\n");
		message.append("Exception occured:\n");
		message.append(indexingException.getExceptionThrown().getStackTrace()[0].toString());
		message.append("\n\n");
		message.append("Message failed:\n").append(indexingException.getPayLoad().toString());
		return message.toString();
	}
}
