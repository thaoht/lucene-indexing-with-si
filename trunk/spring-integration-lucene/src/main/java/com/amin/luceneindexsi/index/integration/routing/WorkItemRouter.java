package com.amin.luceneindexsi.index.integration.routing;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amin.luceneindexsi.index.work.WorkItem;

public class WorkItemRouter {
	private final Logger  LOGGER = Logger.getLogger(getClass());


    /**
     * Based on the workItemEvent  the method will extract the channel name.
     * Currently there is only add and update channel.q
     * @see com.amin.luceneindexsi.index.work.WorkItem.WorkItemEvent
     * @param workItem Item of work that needs to be done
     * @return channel name
     */
	public String resolveWorkItemEventChannel(WorkItem workItem) {
		Assert.notNull(workItem, "work item cannot be null");
		Assert.notNull(workItem.getPayLoad(), "workitem must have payload");
		Assert.notNull(workItem.getWorkItemEvent(), "work item event cannot be null");
		LOGGER.debug("inside " + getClass().getName());
		return workItem.getWorkItemEvent().name().toLowerCase();
	}
}
