package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class Differ {
    public static String generate(String file1, String file2, String format) throws Exception {
        String readeFilePath1 = file1;
        String readeFilePath2 = file2;

        Path path1 = Paths.get(readeFilePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(readeFilePath2).toAbsolutePath().normalize();

        if (!Files.exists(path1) && !Files.exists(path2)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        Map<String, Object> data1 = new HashMap<>();
        Map<String, Object> data2 = new HashMap<>();

        if (readeFilePath1.endsWith(".json")) {
            data1 = Parser.parserJson(content1);
            data2 = Parser.parserJson(content2);
        } else if (readeFilePath2.endsWith(".yml")) {
            data1 = Parser.parserYml(content1);
            data2 = Parser.parserYml(content2);
        }
        var f1 = FormationDiff.formationDiff(data1, data2);
        return Formatter.formatter(format, f1);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }
}
