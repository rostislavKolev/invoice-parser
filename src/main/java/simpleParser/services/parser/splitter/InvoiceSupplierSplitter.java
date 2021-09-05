package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

public class InvoiceSupplierSplitter implements GenericInvoiceSplitter {

    public String getFileName(Invoice invoice) {
        return invoice.getSupplier();
    }
}
