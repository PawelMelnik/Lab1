import java.io.IOException;
import java.util.Map;
import java.util.EnumMap;

public class OptionsReader {
    public static String fileName;

    public OptionsReader(String filename)
    {
        this.fileName = filename;
    }

    public static void Interpret(Map<Enum, String> configMap, Map<Enum, String> paramMap) throws IOException
    {
        InterpreterConfig.Interpreted(fileName, configMap);
        InterpreterCodingParameters.Interpreted(configMap.get(InterpreterConfig.Params.PARAM_FILE), paramMap);
    }

    public static Options readOptions() throws IOException
    {
        Map<Enum, String> configMap = new EnumMap(InterpreterConfig.Params.class);
        Map<Enum, String> paramMap = new EnumMap(InterpreterCodingParameters.Params.class);
        Interpret(configMap, paramMap);
        Options options = new Options(configMap, paramMap);
        return options;
    }
}
