package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

public interface GenericInvoiceSplitter {
    String getFileName(Invoice invoice);
}
