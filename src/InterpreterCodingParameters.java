import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InterpreterCodingParameters extends Interpreter {
    public enum Params {CODE_MODE, MIN_LENGTH}

    public static final Map<String, Enum> lexemeMap;

    static {
        lexemeMap = new HashMap<>();
        lexemeMap.put("CODE_MODE", Params.CODE_MODE);
        lexemeMap.put("MIN_LENGTH", Params.MIN_LENGTH);
    }

    public static void Interpreted(String paramFile, Map<Enum, String> resultMap) throws IOException
    {
        MyReader reader = new MyReader(paramFile);
        String Paramstring;

        while ((Paramstring = reader.ReadLine()) != null)
        {
            if ((Paramstring = Paramstring.replaceAll("\\s", "")).isEmpty())
            {
                continue;
            }

            String[] ParamPair = Paramstring.split(COLON);

            if (!IsPairCorrect(ParamPair, lexemeMap))
            {
                throw new IOException();
            }

            resultMap.put(lexemeMap.get(ParamPair[0]), ParamPair[1]);
        }

        if (resultMap.putIfAbsent(Params.CODE_MODE, "0") == null)
        {
            Log.report("Missing CODE_MODE, using default: 0");
        }

        if (resultMap.putIfAbsent(Params.MIN_LENGTH, "1") == null)
        {
            Log.report("Missing MIN_LENGTH, using default: 1");
        }

        reader.CloseStream();
    }
}
