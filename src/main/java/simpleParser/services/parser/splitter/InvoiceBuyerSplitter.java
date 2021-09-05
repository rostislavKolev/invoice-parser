package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

public class InvoiceBuyerSplitter implements GenericInvoiceSplitter {
    public String getFileName(Invoice invoice) {
        return invoice.getBuyer();
    }
}
