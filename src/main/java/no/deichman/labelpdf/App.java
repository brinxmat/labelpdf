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

    private App() {}

    public static void main(String[] args) throws Exception {


        Options options = new Options();

        Option help = Option.builder()
                .longOpt("help")
                .argName("help")
                .desc("Usage: Label --data=<JSON data string> --output=</path/to/file.pdf>")
                .build();
        Option data = Option.builder()
                .longOpt("data")
                .argName("data")
                .hasArg()
                .desc("Data to be processed")
                .build();
        Option output = Option.builder()
                .longOpt("output")
                .argName("output")
                .hasArg()
                .desc("Path for result file")
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
            } else {
                System.out.println(options.getOption("help").getDescription());
            }

        } catch(ParseException exp) {
            System.out.println("Parsing failed: " + exp.getMessage());
            System.out.println(options.getOption("help").getDescription());
        }

    }

}
