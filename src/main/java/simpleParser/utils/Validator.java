package simpleParser.utils;

import java.nio.file.Files;
import java.nio.file.Path;

import simpleParser.constants.CommonConstants;
import simpleParser.constants.FileConstants;

public class Validator {

    /**
     * Validate arguments
     * @param args
     */
    public static void validateArguments(String[] args) {
        if (args.length != CommonConstants.DEFAULT_ARGS_LENGTH) {
            throw new IllegalArgumentException("Wrong input arguments.");
        }
        String inputPath = args[0];
        String splitBy = args[1];
        String outputPath = args[2];
        String outputFormat = args[3];

        Validator.validatePath(inputPath);
        Validator.validateSplitBy(splitBy);
        Validator.validateInputFileFormat(Utils.getExtension(inputPath));
        Validator.validatePath(outputPath);
        Validator.validateOutputFormat(outputFormat);
    }

    private static void validateOutputFormat(String outputFormat) {
        if (!FileConstants.getAvailableExportFormats().contains(outputFormat)) {
            throw new IllegalArgumentException(outputFormat + " is not supported.");
        }
    }

    private static void validatePath(String path) {
        if (!Files.exists(Path.of(path))) {
            throw new IllegalArgumentException(path + " does not exist");
        }
    }

    private static void validateInputFileFormat(String inputFileExtension) {
        if (!FileConstants.getAvailableImportFormats().contains(inputFileExtension)) {
            throw new UnsupportedOperationException(inputFileExtension + " is not supported.");
        }
    }

    private static void validateSplitBy(String splitBy) {

    }
}
