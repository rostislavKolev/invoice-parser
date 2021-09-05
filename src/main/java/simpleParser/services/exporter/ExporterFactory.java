package simpleParser.services.exporter;

import simpleParser.constants.FileConstants;
/**
 * ExporterFactory provides right implementation of
 * BasicExporter interface. It depends on the output format which
 * is passed as command line argument.
 */
public class ExporterFactory {
    private static ExporterFactory instance = null;

    private ExporterFactory() {
    }

    /**
     * Singleton pattern is implemented, because
     * application does not need multiple instances.
     * @return single instance of ExporterFactory
     */
    public static ExporterFactory getInstance() {
        if (instance == null) {
            instance = new ExporterFactory();
        }
        return instance;
    }

    /**
     * Return appropriate implementation of BasicParser depends on passed
     * parameter.
     * @param outputFormat
     * @return
     */
    public BasicExporter getExporter(String outputFormat) {
        switch (outputFormat) {
            case FileConstants.CSV_FORMAT:
                return new CSVExporter();
            case FileConstants.XML_FORMAT:
                return new XMLExporter();
            default:
                return null;
        }
    }


}
