package no.deichman.labelpdf;

import com.google.gson.Gson;
import no.deichman.labelpdf.data.LabelData;
import no.deichman.labelpdf.data.LabelDataImpl;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Responsibility: utility class to create an entrypoint for the application.
 */
public final class LabelMain {

    private LabelMain() {}

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
        Option labelFormat = Option.builder()
                .longOpt("label")
                .argName("label")
                .hasArg()
                .desc("Format information for label")
                .build();
        options.addOption(help);
        options.addOption(data);
        options.addOption(output);
        options.addOption(labelFormat);

        CommandLineParser parser = new DefaultParser();
        Label label = new Label();

        try {
            CommandLine line = parser.parse(options, args);

            if (line.hasOption("data") && line.hasOption("output")) {
                LabelData labelData = new Gson().fromJson(line.getOptionValue("data"), LabelDataImpl.class);
                label.setData(labelData);
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
