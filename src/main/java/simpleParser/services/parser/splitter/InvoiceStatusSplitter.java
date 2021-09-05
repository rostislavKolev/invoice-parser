package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvoiceStatusSplitter implements GenericInvoiceSplitter {

    @Override
    public void split(Map<String, List<Invoice>> splitInvoices, Invoice invoice) {
        if (splitInvoices.containsKey(invoice.getInvoiceStatus())) {
            splitInvoices.get(invoice.getInvoiceStatus()).add(invoice);
        } else {
            splitInvoices.put(invoice.getInvoiceStatus(), new ArrayList<>() {{
                add(invoice);
            }});
        }
    }

    @Override
    public String getFileName(Invoice invoice) {
        return invoice.getInvoiceStatus();
    }
}
