package com.amin.luceneindexsi.index.integration.routing;

import com.amin.luceneindexsi.index.work.WorkItem;
import com.amin.luceneindexsi.index.work.WorkItem.WorkItemEvent;
import org.apache.lucene.document.Document;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class WorkItemRouterTest {
	
	private WorkItemRouter underTest;
	
	@Before
	public void init() {
		underTest = new WorkItemRouter();
	}
	
	@Test
	public void canResolveAddWorkItemChannel() {
		WorkItem workItem = new WorkItem(WorkItemEvent.ADD, new Document());
		String channel = underTest.resolveWorkItemEventChannel(workItem);
		assertNotNull(channel);
		assertEquals(WorkItemEvent.ADD.name().toLowerCase(), channel);
	}
	
	@Test
	public void canResolveUpdateWorkItemChannel() {
		WorkItem workItem = new WorkItem(WorkItemEvent.UPDATE, new Document());
		String channel = underTest.resolveWorkItemEventChannel(workItem);
		assertNotNull(channel);
		assertEquals(WorkItemEvent.UPDATE.name().toLowerCase(), channel);
	}
}
