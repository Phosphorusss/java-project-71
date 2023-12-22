import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DifferTest {
    @Test
    public void test1() throws Exception {
        var coll = Differ.generate("/home/ubuntu/java-project-71/app/src/test/resources/file1.json", "/home/ubuntu/java-project-71/app/src/test/resources/file2.json");
        var result = "{\n" +
                "- follow: false\n" +
                "  host: hexlet.io\n" +
                "- proxy: 123.234.53.22\n" +
                "- timeout: 50\n" +
                "+ timeout: 20\n" +
                "+ verbose: true\n" +
                "}";
        assertEquals(result, coll);
    }

    @Test
    public void test2() throws Exception {
        var coll = Differ.generate("/home/ubuntu/java-project-71/app/src/test/resources/file1.json", "/home/ubuntu/java-project-71/app/src/test/resources/file3.json");
        var result = "{\n" +
                "- host: hexlet.io\n" +
                "- timeout: 50\n" +
                "- proxy: 123.234.53.22\n" +
                "- follow: false\n" +
                "}";
        assertEquals(result, coll);
    }
}
