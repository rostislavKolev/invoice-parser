package simpleParser.constants;

import java.util.ArrayList;
import java.util.List;

public class FileConstants {
    public static final String XML_FORMAT = "xml";
    public static final String XML_EXTENSION = ".xml";
    public static final String CSV_FORMAT = "csv";
    public static final String CSV_EXTENSION = ".csv";

    public static List<String> getAvailableExportFormats() {
        return new ArrayList<>() {{
            add(CSV_FORMAT);
            add(XML_FORMAT);
        }};
    }

    public static List<String> getAvailableImportFormats() {
        return new ArrayList<>() {{
            add(CSV_EXTENSION);
            add(XML_EXTENSION);
        }};
    }
}
