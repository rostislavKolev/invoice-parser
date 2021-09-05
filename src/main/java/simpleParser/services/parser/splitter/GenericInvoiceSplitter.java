package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

import java.util.List;
import java.util.Map;

public interface GenericInvoiceSplitter {
    void split(Map<String, List<Invoice>> splitInvoices, Invoice invoice);

    String getFileName(Invoice invoice);
}
