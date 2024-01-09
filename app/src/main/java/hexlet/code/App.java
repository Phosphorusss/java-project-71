package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelp;
    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean usageVersion;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    String format;

    @Override
    public Integer call() throws Exception {
        if (format == null) {
            System.out.println(Differ.generate(filepath1, filepath2));
        } else {
            System.out.println(Differ.generate(filepath1, filepath2, format));
        }
        return null;
    }


    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }
}