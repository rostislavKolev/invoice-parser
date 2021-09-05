package simpleParser.services.exporter;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import simpleParser.constants.CommonConstants;
import simpleParser.constants.FileConstants;
import simpleParser.models.Invoice;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import simpleParser.constants.TagConstants;
import simpleParser.utils.Utils;

/**
 * XMLExporter is an implementation of BasicExporter.
 * It provides functionality to export invoices into XML files
 */
public class XMLExporter implements BasicExporter {

    /**
     * Export single invoice to specific output path in XML format.
     *
     * @param invoice
     * @param outputFileAbsolutePath
     * @throws IOException
     */
    @Override
    public void exportSingleInvoice(Invoice invoice, String outputFileAbsolutePath) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Utils.createDirectoryIfNotExists(outputFileAbsolutePath);
        exportInvoiceImageIfExists(invoice, outputFileAbsolutePath);
        String fileUrl = configureOutputFile(outputFileAbsolutePath);

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = provideDocument(fileUrl, documentBuilder);

        Node invoicesTag = document.getElementsByTagName(TagConstants.TAG_INVOICES).item(0);
        Element currentInvoiceTag = createInvoiceElement(invoice, document);
        invoicesTag.appendChild(currentInvoiceTag);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(fileUrl));
        transformer.transform(domSource, streamResult);
    }

    private void exportInvoiceImageIfExists(Invoice invoice, String outputFileAbsolutePath) throws IOException {
        if (invoice.getImageName() != null) {
            exportImage(invoice.getImageName(), invoice.getInvoiceImage(), outputFileAbsolutePath);
        }
    }

    /**
     * Create invoice element
     *
     * @param invoice
     * @param document
     * @return Element which contains invoice
     * @throws IOException
     */
    private Element createInvoiceElement(Invoice invoice, Document document) throws IOException {
        Element currentInvoiceTag = document.createElement(TagConstants.TAG_SINGLE_INVOICE);
        Element buyerName = document.createElement(TagConstants.BUYER);
        buyerName.appendChild(document.createTextNode(invoice.getBuyer()));
        currentInvoiceTag.appendChild(buyerName);

        Element imageName = document.createElement(TagConstants.IMAGE_NAME);
        imageName.appendChild(document.createTextNode(invoice.getImageName()));
        currentInvoiceTag.appendChild(imageName);

        SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
        Element invoiceDueDate = document.createElement(TagConstants.INVOICE_DUE_DATE);
        invoiceDueDate.appendChild(document.createTextNode(formatter.format(invoice.getInvoiceDueDate())));
        currentInvoiceTag.appendChild(invoiceDueDate);

        Element invoiceNumber = document.createElement(TagConstants.INVOICE_NUMBER);
        invoiceNumber.appendChild(document.createTextNode(invoice.getInvoiceNumber()));
        currentInvoiceTag.appendChild(invoiceNumber);

        Element invoiceAmount = document.createElement(TagConstants.INVOICE_AMOUNT);
        invoiceAmount.appendChild(document.createTextNode(String.valueOf(invoice.getInvoiceAmount())));
        currentInvoiceTag.appendChild(invoiceAmount);


        Element invoiceCurrency = document.createElement(TagConstants.INVOICE_CURRENCY);
        invoiceCurrency.appendChild(document.createTextNode(invoice.getInvoiceCurrency()));
        currentInvoiceTag.appendChild(invoiceCurrency);

        Element invoiceStatus = document.createElement(TagConstants.INVOICE_STATUS);
        invoiceStatus.appendChild(document.createTextNode(invoice.getInvoiceStatus()));
        currentInvoiceTag.appendChild(invoiceStatus);

        Element supplier = document.createElement(TagConstants.SUPPLIER);
        supplier.appendChild(document.createTextNode(invoice.getSupplier()));

        currentInvoiceTag.appendChild(supplier);
        return currentInvoiceTag;
    }

    private Document provideDocument(String fileUrl, DocumentBuilder documentBuilder) throws IOException, SAXException {
        Document document;
        if (Files.exists(Paths.get(fileUrl))) {
            document = documentBuilder.parse(fileUrl);
        } else {
            document = documentBuilder.newDocument();
            Element invoicesTag = document.createElement(TagConstants.TAG_INVOICES);
            document.appendChild(invoicesTag);
        }
        return document;
    }

    private String configureOutputFile(String parentDirectory) {
        String outputPath = parentDirectory + File.separator + parentDirectory.substring(parentDirectory.lastIndexOf(File.separator) + 1) + FileConstants.XML_EXTENSION;
        return outputPath;
    }

    private void exportImage(String imageName, String encodedImage, String destFolder) throws IOException {
        byte[] decodedImg = Base64.getDecoder()
                .decode(encodedImage.getBytes(StandardCharsets.UTF_8));
        Path imagePath = Paths.get(destFolder, imageName);
        Files.write(imagePath, decodedImg);
    }
}
