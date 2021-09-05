package simpleParser.services.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;

/**
 * BasicParser is an interface which defines
 * basic methods which every implementation needs
 * to implement.
 */
public interface BasicParser {

    /**
     * BasicParser parses input file, splits data by specific property,
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
    void parse(String inputFileAbsolutePath, String splitByProperty, String outputDirectoryAbsolutePath, String outputFormat) throws ParseException, IOException, ParserConfigurationException, TransformerException, SAXException;
}
