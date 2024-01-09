import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private String jsonAndYmlStylist;
    private String recursiveJsonAndYmlStyList;
    private String jsonAndYmlPlain;
    private String resultJson;
    @BeforeEach
    public void beforeEach() throws IOException {
        String path1 = "/home/ubuntu/java-project-71/app/src/test/resources/jsonAndYmlStylist.txt";
        Path path1Normalize = Paths.get(path1).toAbsolutePath().normalize();
        jsonAndYmlStylist = Files.readString(path1Normalize);

        String path2 = "/home/ubuntu/java-project-71/app/src/test/resources/recursiveJsonAndYmlStyList.txt";
        Path path2Normalize = Paths.get(path2).toAbsolutePath().normalize();
        recursiveJsonAndYmlStyList = Files.readString(path2Normalize);

        String path3 = "/home/ubuntu/java-project-71/app/src/test/resources/jsonAndYmlPlain.txt";
        Path path3Normalize = Paths.get(path3).toAbsolutePath().normalize();
        jsonAndYmlPlain = Files.readString(path3Normalize);

        String path4 = "/home/ubuntu/java-project-71/app/src/test/resources/resultJson.txt";
        Path path4Normalize = Paths.get(path4).toAbsolutePath().normalize();
        resultJson = Files.readString(path4Normalize);
    }
    @Test
    public void testGenerate1() throws Exception {
        var coll = Differ.generate("/home/ubuntu/java-project-71/app/src/test/resources/file1.json",
                "/home/ubuntu/java-project-71/app/src/test/resources/file2.json", "stylish");
        assertEquals(coll, jsonAndYmlStylist);
    }

    @Test
    public void testGenerate2() throws Exception {
        var coll = Differ.generate("/home/ubuntu/java-project-71/app/src/test/resources/file1.yml",
                "/home/ubuntu/java-project-71/app/src/test/resources/file2.yml");
        assertEquals(coll, jsonAndYmlStylist);
    }

    @Test
    public void testGenerate3() throws Exception {
        var coll = Differ.generate("/home/ubuntu/java-project-71/app/src/test/resources/file3.json",
                "/home/ubuntu/java-project-71/app/src/test/resources/file4.json");
        assertEquals(coll, recursiveJsonAndYmlStyList);
    }

    @Test
    public void testGenerate4() throws Exception {
        var coll = Differ.generate("/home/ubuntu/java-project-71/app/src/test/resources/file3.json",
                "/home/ubuntu/java-project-71/app/src/test/resources/file4.json", "plain");
        assertEquals(coll, jsonAndYmlPlain);
    }
    @Test
    public void testGenerate5() throws Exception {
        var coll = Differ.generate("/home/ubuntu/java-project-71/app/src/test/resources/file3.json",
                "/home/ubuntu/java-project-71/app/src/test/resources/file4.json", "json");
        assertEquals(coll, resultJson);
    }
}
