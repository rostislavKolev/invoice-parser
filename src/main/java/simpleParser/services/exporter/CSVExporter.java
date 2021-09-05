package simpleParser.services.exporter;

import simpleParser.constants.CommonConstants;
import simpleParser.constants.FileConstants;
import simpleParser.models.Invoice;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import simpleParser.constants.TagConstants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;

/**
 * CSVExporter is an implementation of BasicExporter.
 * It provides functionality to export invoices into CSV files
 */
public class CSVExporter implements BasicExporter {

    /**
     * Export single invoice to specific output path in CSV format.
     *
     * @param invoice
     * @param outputFileAbsolutePath
     * @throws IOException
     */
    @Override
    public void exportSingleInvoice(Invoice invoice, String outputFileAbsolutePath) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileAbsolutePath + FileConstants.CSV_EXTENSION), StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader(TagConstants.getHeaders()));
        SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);

        csvPrinter.printRecord(invoice.getBuyer(), invoice.getImageName(), invoice.getInvoiceImage(),
                formatter.format(invoice.getInvoiceDueDate()), invoice.getInvoiceNumber(), invoice.getInvoiceAmount(),
                invoice.getInvoiceCurrency(), invoice.getInvoiceStatus(), invoice.getSupplier());

        csvPrinter.flush();
    }

}
