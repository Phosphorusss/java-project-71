package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
public class Plain {
    public static String plain(List<Map<String, Object>> formationDiff) {
        StringBuilder diff = new StringBuilder();
        for (var element: formationDiff) {
            switch ((String) element.get("status")) {
                case "added":
                    diff.append("Property " + "'" + element.get("key") + "'" + " was added with value: " + isСompositeProperty(isString(element.get("value"))) + "\n");
                    break;
                case "deleted":
                    diff.append("Property " + "'" + element.get("key") + "'" + " was removed" + "\n");
                    break;
                case "changed":
                    diff.append("Property " + "'" + element.get("key") + "'"  + " was updated.");
                    diff.append(" From " + isСompositeProperty(isString(element.get("oldValue"))) + " to " + isСompositeProperty(isString(element.get("newValue"))) + "\n");
                    break;
                default:
                    break;
            }
        }
        return diff.toString();
    }

    public static Object isСompositeProperty(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value;
    }

    public static Object isString(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value;
    }
}
