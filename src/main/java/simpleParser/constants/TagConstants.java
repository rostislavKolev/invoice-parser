package simpleParser.constants;

public class TagConstants {
    public static final String BUYER = "buyer";
    public static final String IMAGE_NAME = "image_name";
    public static final String INVOICE_IMAGE = "invoice_image";
    public static final String INVOICE_DUE_DATE = "invoice_due_date";
    public static final String INVOICE_NUMBER = "invoice_number";
    public static final String INVOICE_AMOUNT = "invoice_amount";
    public static final String INVOICE_CURRENCY = "invoice_currency";
    public static final String INVOICE_STATUS = "invoice_status";
    public static final String SUPPLIER = "supplier";

    public static final String TAG_INVOICES = "invoices";
    public static final String TAG_SINGLE_INVOICE = "invoice";

    public static String[] getHeaders() {
        return new String[]{BUYER, IMAGE_NAME, INVOICE_IMAGE, INVOICE_DUE_DATE, INVOICE_NUMBER, INVOICE_AMOUNT, INVOICE_CURRENCY, INVOICE_STATUS, SUPPLIER};
    }
}
