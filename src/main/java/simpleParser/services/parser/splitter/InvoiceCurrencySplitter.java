package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvoiceCurrencySplitter implements GenericInvoiceSplitter {
    @Override
    public void split(Map<String, List<Invoice>> splitInvoices, Invoice invoice) {
        if (splitInvoices.containsKey(invoice.getInvoiceCurrency())) {
            splitInvoices.get(invoice.getInvoiceCurrency()).add(invoice);
        } else {
            splitInvoices.put(invoice.getInvoiceCurrency(), new ArrayList<>() {{
                add(invoice);
            }});
        }
    }

    @Override
    public String getFileName(Invoice invoice) {
        return invoice.getInvoiceCurrency();
    }
}
