package simpleParser.services.exporter;

import org.xml.sax.SAXException;
import simpleParser.models.Invoice;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * BasicExporter is an interface which defines
 * basic export methods.
 */
public interface BasicExporter {
    /**
     * Export single invoice to specific output path.
     *
     * @param invoice
     * @param outputFileAbsolutePath
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     */
    void exportSingleInvoice(Invoice invoice, String outputFileAbsolutePath) throws IOException, ParserConfigurationException, SAXException, TransformerException;
}
