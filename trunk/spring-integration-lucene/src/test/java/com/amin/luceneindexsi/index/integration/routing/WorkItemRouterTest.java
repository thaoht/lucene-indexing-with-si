package com.amin.luceneindexsi.index.integration.routing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.lucene.document.Document;
import org.junit.Before;
import org.junit.Test;

import com.amin.luceneindexsi.index.integration.routing.WorkItemRouter;
import com.amin.luceneindexsi.index.work.WorkItem;
import com.amin.luceneindexsi.index.work.WorkItem.WorkItemEvent;

public class WorkItemRouterTest {
	
	private WorkItemRouter underTest;
	
	@Before
	public void setUp() {
		underTest = new WorkItemRouter();
	}
	
	@Test
	public void testCanResolveAddWorkItemChannel() {
		WorkItem workItem = new WorkItem(WorkItemEvent.ADD, new Document());
		String channel = underTest.resolveWorkItemEventChannel(workItem);
		assertNotNull(channel);
		assertEquals(WorkItemEvent.ADD.name().toLowerCase(), channel);
	}
	
	@Test
	public void testCanResolveUpdateWorkItemChannel() {
		WorkItem workItem = new WorkItem(WorkItemEvent.UPDATE, new Document());
		String channel = underTest.resolveWorkItemEventChannel(workItem);
		assertNotNull(channel);
		assertEquals(WorkItemEvent.UPDATE.name().toLowerCase(), channel);
	}
}
