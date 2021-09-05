package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

public class InvoiceStatusSplitter implements GenericInvoiceSplitter {

    @Override
    public String getFileName(Invoice invoice) {
        return invoice.getInvoiceStatus();
    }
}
