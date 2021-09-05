package simpleParser.services.parser;

import org.apache.commons.csv.CSVParser;
import org.xml.sax.SAXException;
import simpleParser.models.Invoice;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import simpleParser.services.exporter.BasicExporter;
import simpleParser.services.parser.splitter.GenericInvoiceSplitter;
import simpleParser.constants.TagConstants;
import simpleParser.utils.Utils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/**
 * ParserCSV is an implementation of BasicParser interface.
 * It provides functionality to read csv file as input, split it
 * by specific property and export in output format passed as
 * command line argument.
 */
public class ParserCSV implements BasicParser {
    private GenericInvoiceSplitter invoiceSplitter;
    private BasicExporter exporter;

    /**
     * Create ParserCSV using constructor.
     *
     * @param invoiceSplitter
     * @param exporter
     */
    public ParserCSV(GenericInvoiceSplitter invoiceSplitter, BasicExporter exporter) {
        this.invoiceSplitter = invoiceSplitter;
        this.exporter = exporter;
    }

    /**
     * ParserCSV parses CSV input file, splits data by specific property,
     * converts and exports to described output directory.
     *
     * @param inputFileAbsolutePath
     * @param splitByProperty
     * @param outputDirectoryAbsolutePath
     * @param outputFormat
     * @throws ParseException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws SAXException
     */
    public void parse(String inputFileAbsolutePath, String splitByProperty, String outputDirectoryAbsolutePath, String outputFormat) throws ParseException, IOException, ParserConfigurationException, TransformerException, SAXException {
        CSVFormat format = CSVFormat.DEFAULT.withHeader(TagConstants.getHeaders()).withSkipHeaderRecord().withDelimiter(',');
        CSVParser parser = new CSVParser(new FileReader(inputFileAbsolutePath, StandardCharsets.UTF_8), format);

        for (CSVRecord record : parser) {
            Invoice currentInvoice = mapRecordToInvoice(record);
            String parentDirectory = outputDirectoryAbsolutePath + File.separator + outputFormat + File.separator + splitByProperty;
            Utils.createDirectoryIfNotExists(parentDirectory);
            exporter.exportSingleInvoice(currentInvoice, parentDirectory + File.separator + this.invoiceSplitter.getFileName(currentInvoice));
        }
        parser.close();
    }

    /**
     * Maps CSVRecord to Invoice.
     *
     * @param record csv record
     * @return Invoice entity
     * @throws ParseException
     */
    private Invoice mapRecordToInvoice(CSVRecord record) throws ParseException {
        Invoice invoice = new Invoice();
        invoice.setBuyer(getPropertyOrNull(record, TagConstants.BUYER));
        invoice.setImageName(getPropertyOrNull(record, TagConstants.IMAGE_NAME));
        invoice.setInvoiceImage(getPropertyOrNull(record, TagConstants.INVOICE_IMAGE));
        invoice.setInvoiceDueDate(Utils.parseDate(getPropertyOrNull(record, TagConstants.INVOICE_DUE_DATE)));
        invoice.setInvoiceNumber(getPropertyOrNull(record, TagConstants.INVOICE_NUMBER));
        invoice.setInvoiceAmount(new BigDecimal(getPropertyOrNull(record, TagConstants.INVOICE_AMOUNT)));
        invoice.setInvoiceCurrency(getPropertyOrNull(record, TagConstants.INVOICE_CURRENCY));
        invoice.setInvoiceStatus(getPropertyOrNull(record, TagConstants.INVOICE_STATUS));
        invoice.setSupplier(getPropertyOrNull(record, TagConstants.SUPPLIER));

        return invoice;
    }

    /**
     * Gets property or returns null
     *
     * @param record
     * @param property
     * @return property or null
     */
    private String getPropertyOrNull(CSVRecord record, String property) {
        if (record.get(property).equals("")) {
            return null;
        }
        return record.get(property);
    }

}
