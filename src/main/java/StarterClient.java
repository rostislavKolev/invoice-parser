import org.xml.sax.SAXException;
import simpleParser.services.parser.BasicParser;
import simpleParser.services.parser.ParserFactory;
import simpleParser.utils.Utils;
import simpleParser.utils.Validator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;

/**
 * StarterClient is the starting point of simple-parser application.
 * Several command-line arguments are required in order to execute parsing.
 */
public class StarterClient {
    private static String inputFileAbsolutePath;
    private static String outputDirectoryAbsolutePath;
    private static String outputFormat;
    private static String splitByProperty;

    /**
     * Main method expects these args:
     *  inputFileAbsolutePath - String;
     *  outputDirectoryAbsolutePath - String;
     *  outputFormat - String;
     *  splitByProperty - String;
     * @param args
     */
    public static void main(String[] args) {
        Validator.validateArguments(args);
        assignArguments(args);
        BasicParser parser = ParserFactory.getInstance().getParser(Utils.getExtension(inputFileAbsolutePath), outputFormat, splitByProperty);
        try {
            parser.parse(inputFileAbsolutePath, splitByProperty, outputDirectoryAbsolutePath, outputFormat);
        } catch (ParseException | IOException | ParserConfigurationException | TransformerException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Assign all arguments
     *
     * @param args
     */
    private static void assignArguments(String[] args) {
        inputFileAbsolutePath = args[0];
        splitByProperty = args[1];
        outputDirectoryAbsolutePath = args[2];
        outputFormat = args[3];
    }
}