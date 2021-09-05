package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvoiceBuyerSplitter implements GenericInvoiceSplitter {

    @Override
    public void split(Map<String, List<Invoice>> splitInvoices, Invoice invoice) {
        if (splitInvoices.containsKey(invoice.getBuyer())) {
            splitInvoices.get(invoice.getBuyer()).add(invoice);
        } else {
            splitInvoices.put(invoice.getBuyer(), new ArrayList<>() {{
                add(invoice);
            }});
        }
    }

    public String getFileName(Invoice invoice) {
        return invoice.getBuyer();
    }
}
