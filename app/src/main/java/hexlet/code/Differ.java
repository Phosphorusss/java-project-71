package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static String generate(String file1, String file2) throws Exception {
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
            data1.putAll(Parser.parserJson(content1));
            data2.putAll(Parser.parserJson(content2));
        } else if (readeFilePath2.endsWith(".yml")) {
            data1 = Parser.parserJson(content1);
            data2 = Parser.parserJson(content2);
        }

        Set<String> data1Set = new TreeSet<>(data1.keySet());
        Set<String> data2Set = new TreeSet<>(data2.keySet());
        Set<String> keysAll = new TreeSet<>(data2.keySet());
        keysAll.addAll(data1Set);
        keysAll.addAll(data2Set);

        StringBuilder diff = new StringBuilder();
        diff.append("{\n");
        for (var key : keysAll) {
            if (!data1.containsKey(key) && data2.containsKey(key)) {
                diff.append("+ ");
                diff.append(key + ": ");
                diff.append(data2.get(key) + "\n");
            } else if (data1.containsKey(key) && !data2.containsKey(key)) {
                diff.append("- ");
                diff.append(key + ": ");
                diff.append(data1.get(key) + "\n");
            } else if ((data1.containsKey(key) && data2.containsKey(key)) && (!data1.get(key).equals(data2.get(key)))) {
                diff.append("- ");
                diff.append(key + ": ");
                diff.append(data1.get(key) + "\n");
                diff.append("+ ");
                diff.append(key + ": ");
                diff.append(data2.get(key) + "\n");
            } else if (data1.containsKey(key) && data2.containsKey(key)) {
                diff.append("  " + key + ": ");
                diff.append(data1.get(key) + "\n");
            }
        }
        diff.append("}");
        return diff.toString();
    }
}
