package org.nuxeo.sample;


import java.util.Arrays;
import java.util.List;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.PostCommitFilteringEventListener;
import org.nuxeo.ecm.core.event.EventBundle;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WorkflowProcessListner implements PostCommitFilteringEventListener {
	private static final Log log = LogFactory.getLog(WorkflowProcessListner.class);
	
	protected final List<String> handled = Arrays.asList("documentProxyPublished","documentCreated", "documentModified");
  

    @Override
    public void handleEvent(EventBundle events) {
        for (Event event : events) {
        	log.info("Event Name: " + event.getName());
            if (acceptEvent(event)) {
                handleEvent(event);
            }
        }
    }

    @Override
    public boolean acceptEvent(Event event) {
        return handled.contains(event.getName());
    }

  
    public void handleEvent(Event event) {
        EventContext ctx = event.getContext();
        if (!(ctx instanceof DocumentEventContext)) {
          return;
        }

        DocumentEventContext docCtx = (DocumentEventContext) ctx;
        DocumentModel doc = docCtx.getSourceDocument();

        // Add some logic starting from here.
    }
}
