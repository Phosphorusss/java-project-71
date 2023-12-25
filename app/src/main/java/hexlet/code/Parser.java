package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parserJson(String content) throws JsonProcessingException {
        System.out.println("Json");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(content, Map.class);
        return data;
    }
    public static Map<String, Object> parserYml(String content) throws JsonProcessingException {
        System.out.println("YML");
        ObjectMapper mapper = new YAMLMapper();
        Map<String, Object> data = mapper.readValue(content, Map.class);
        return data;
    }
}
