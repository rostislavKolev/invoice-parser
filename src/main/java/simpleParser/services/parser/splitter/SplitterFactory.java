package simpleParser.services.parser.splitter;

import simpleParser.constants.TagConstants;

/**
 * SplitterFactory provides right implementation of
 * GenericInvoiceSplitter interface. It depends on property
 * which is required to be passed as command line argument.
 */
public class SplitterFactory {
    private static SplitterFactory instance = null;

    private SplitterFactory() {
    }

    /**
     * Singleton pattern is implemented, because
     * application does not need multiple instances.
     *
     * @return single instance of SplitterFactory
     */
    public static SplitterFactory getInstance() {
        if (instance == null) {
            instance = new SplitterFactory();
        }
        return instance;
    }

    /**
     * Return appropriate implementation of GenericInvoiceSplitter depends on passed
     * parameter.
     * @param splitByProperty
     * @return
     */
    public GenericInvoiceSplitter getSplitter(String splitByProperty) {
        switch (splitByProperty) {
            case TagConstants.BUYER:
                return new InvoiceBuyerSplitter();
            case TagConstants.INVOICE_STATUS:
                return new InvoiceStatusSplitter();
            case TagConstants.INVOICE_CURRENCY:
                return new InvoiceCurrencySplitter();
            case TagConstants.SUPPLIER:
                return new InvoiceSupplierSplitter();
            default:
                return null;
        }
    }
}
