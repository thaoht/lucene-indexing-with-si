package com.amin.luceneindexsi.index.work;

import java.io.Serializable;

import org.apache.lucene.document.Document;

public class WorkItem implements Serializable{

	private static final long serialVersionUID = -6880936711577653069L;

	public enum WorkItemEvent {
		ADD,
		UPDATE;
	}
	
	private final WorkItemEvent workItemEvent;
	private final Document payLoad;
	
	public WorkItem(final WorkItemEvent workItemEvent, final Document payLoad) {
		this.workItemEvent = workItemEvent;
		this.payLoad = payLoad;
	}
	
	public WorkItemEvent getWorkItemEvent() {
		return workItemEvent;
	}

	public Document getPayLoad() {
		return payLoad;
	}
}
