package simpleParser.services.parser.splitter;

import simpleParser.models.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvoiceSupplierSplitter implements GenericInvoiceSplitter {

    @Override
    public void split(Map<String, List<Invoice>> splitInvoices, Invoice invoice) {
        if (splitInvoices.containsKey(invoice.getSupplier())) {
            splitInvoices.get(invoice.getSupplier()).add(invoice);
        } else {
            splitInvoices.put(invoice.getSupplier(), new ArrayList<>() {{
                add(invoice);
            }});
        }
    }

    public String getFileName(Invoice invoice) {
        return invoice.getSupplier();
    }
}
