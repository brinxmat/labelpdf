package no.deichman.labelpdf;

import com.google.gson.Gson;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

/**
 * Responsibility: create an entrypoint for the application.
 */
public final class App {

    App() {}

    public static void main(String[] args) throws IOException {


        Options options = new Options();

        Option help = new Option("help", "Usage: Label --data <JSON data string> --output </path/to/file.pdf>");
        Option data = Option.builder()
                .longOpt("data")
                .argName("data")
                .hasArg()
                .desc("Data to be processed")
                .required()
                .build();
        Option output = Option.builder()
                .longOpt("output")
                .argName("output")
                .hasArg()
                .desc("Path for result file")
                .required()
                .build();
        options.addOption(help);
        options.addOption(data);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine line = parser.parse(options, args);

            if (line.hasOption("data") && line.hasOption("output")) {
                Gson gson = new Gson();
                Label label = gson.fromJson(line.getOptionValue("data"), Label.class);
                label.renderPDF(line.getOptionValue("output"));
            }

        } catch(ParseException exp) {
            System.out.println("Parsing failed: " + exp.getMessage());
        }

    }

}
