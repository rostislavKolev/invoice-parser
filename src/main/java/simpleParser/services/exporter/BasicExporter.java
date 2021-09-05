package simpleParser.services.exporter;

import org.xml.sax.SAXException;
import simpleParser.models.Invoice;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface BasicExporter {
    void exportSingleInvoice(Invoice invoice, String newFileUrl) throws IOException, ParserConfigurationException, SAXException, TransformerException;
}
