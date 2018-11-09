import java.io.IOException;

public class Main {
    public static CoderRunner coder;

    public static void main(String[] args) {
        if (args.length == 1)
        {
            try
            {
                OptionsReader optionsReader = new OptionsReader(args[0]);
                Options options = optionsReader.readOptions();
                if (options != null)
                {
                    coder = new CoderRunner(options);
                    coder.RunCoder();
                }
            }
            catch (IOException e)
            {
                Log.report("Exception detected");
                System.exit(-1);
            }
        }
        else
        {
            Log.report("Wrong file format");
        }
        Log.close();
        System.exit(0);
    }
}
