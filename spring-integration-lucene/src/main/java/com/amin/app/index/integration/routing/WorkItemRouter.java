package com.amin.app.index.integration.routing;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amin.app.index.work.WorkItem;

public class WorkItemRouter {
	private final Logger  LOGGER = Logger.getLogger(getClass()); 
	
	public String resolveWorkItemEventChannel(WorkItem workItem) {
		Assert.notNull(workItem, "work item cannot be null");
		Assert.notNull(workItem.getPayLoad(), "workitem must have payload");
		Assert.notNull(workItem.getWorkItemEvent(), "work item event cannot be null");
		LOGGER.debug("inside " + getClass().getName() );
		
		String channel = workItem.getWorkItemEvent().name().toLowerCase();
		return channel;
	}
}
