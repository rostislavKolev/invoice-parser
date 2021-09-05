package simpleParser.utils;

import simpleParser.constants.CommonConstants;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getExtension(String inputFilePath) {
        String extension = inputFilePath.substring(inputFilePath.lastIndexOf("."));
        return extension;
    }

    /**
     * Parse date using specific date format (yyyy-mm-dd)
     * @param date
     * @return java.util.Date()
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
        return formatter.parse(date);
    }

    /**
     * Creates parent directory if does not exist.
     *
     * @param parentDirectoryPath
     */
    public static void createDirectoryIfNotExists(String parentDirectoryPath) {
        File parentDirectory = new File(parentDirectoryPath);
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
    }
}
