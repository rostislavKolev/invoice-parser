package simpleParser.services.parser;

import simpleParser.constants.FileConstants;
import simpleParser.services.exporter.BasicExporter;
import simpleParser.services.exporter.ExporterFactory;
import simpleParser.services.parser.splitter.GenericInvoiceSplitter;
import simpleParser.services.parser.splitter.SplitterFactory;

/**
 * ParserFactory provides right implementation of
 * BasicParser interface. It depends on the format of the input file,
 * the desired output format and by which property invoices should be split.
 */
public class ParserFactory {
    private static ParserFactory instance = null;

    private ParserFactory() {
    }

    /**
     * Singleton pattern is implemented, because
     * application does not need multiple instances.
     * @return single instance of ParserFactory
     */
    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }
        return instance;
    }

    /**
     * Return appropriate implementation of BasicParser depends on passed
     * parameters.
     * @param fileExtension input file extension
     * @param outputFormat output format
     * @param splitByProperty invoices will be split by this property
     * @return Appropriate implementation of BasicParser
     */
    public BasicParser getParser(String fileExtension, String outputFormat, String splitByProperty) {
        GenericInvoiceSplitter splitter = SplitterFactory.getInstance().getSplitter(splitByProperty);
        BasicExporter exporter = ExporterFactory.getInstance().getExporter(outputFormat);
        switch (fileExtension) {
            case FileConstants.CSV_EXTENSION:
                return new ParserCSV(splitter, exporter);
            default:
                return null;
        }
    }


}
