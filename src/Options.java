import java.util.Map;

public class Options {
    public static Map<Enum, String> configMap;
    public static Map<Enum, String> paramMap;

    public Options(Map<Enum, String> configMap, Map<Enum, String> paramMap)
    {
        this.configMap = configMap;
        this.paramMap = paramMap;
    }
}
