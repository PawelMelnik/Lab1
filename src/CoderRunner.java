import java.io.*;
import java.util.Map;

public class CoderRunner {
    public Map<Enum, String> configMap;
    public Map<Enum, String> paramMap;

    public CoderRunner(Options options)
    {
        this.configMap = options.configMap;
        this.paramMap = options.paramMap;
    }

    private static void RunEncode(String inputFile, String outputFile, int min_length) throws IOException
    {
        try (FileInputStream readerInput = new FileInputStream(inputFile);
             FileOutputStream writerOutput = new FileOutputStream(outputFile))
        {
            if(min_length <= 0) {
                throw new IOException("Wrong portion length. Expected: >=0. Found:" + min_length);
            }

            byte i = (byte)readerInput.read();
            int k = 1;

            while (i != -1)
            {
                byte j = (byte)readerInput.read();

                while (j == i)
                {
                    k++;
                    j = (byte)readerInput.read();
                }

                //k = k >= min_length ? k : 1;
                if (k >= min_length)
                {
                    writerOutput.write((byte)k);
                    writerOutput.flush();
                    writerOutput.write(i);
                    writerOutput.flush();
                }
                else
                {
                    while (k > 0)
                    {
                        writerOutput.write((byte)1);
                        writerOutput.flush();
                        writerOutput.write(i);
                        writerOutput.flush();
                        k--;
                    }
                }

                i = j;
                k = 1;
            }
        }

        catch (IOException e)
        {
            Log.report("Can't encode file");
            throw new IOException();
        }

    }


    private static void RunDecode(String inputFile, String outputFile, int min_length) throws IOException
    {
        try (FileInputStream readerInput = new FileInputStream(inputFile);
             FileOutputStream writerOutput = new FileOutputStream(outputFile))
        {
            if(min_length <= 0) {
                throw new IOException("Wrong portion length. Expected: >=0. Found:" + min_length);
            }

            byte k = 0;
            while((k = (byte)readerInput.read()) != -1)
            {
                byte j = (byte)readerInput.read();
                while( k > 0)
                {
                    writerOutput.write((char)j);
                    writerOutput.flush();
                    k--;
                }
            }
        }

        catch (IOException e)
        {
            Log.report("Can't decode file");
            throw new IOException();
        }

    }

    public void RunCoder() throws IOException
    {
        String inputFile = configMap.get(InterpreterConfig.Params.INPUT_FILE);
        String outputFile = configMap.get(InterpreterConfig.Params.OUTPUT_FILE);
        int min_length = Integer.parseInt(paramMap.get(InterpreterCodingParameters.Params.MIN_LENGTH));
        int code_mode = Integer.parseInt(paramMap.get(InterpreterCodingParameters.Params.CODE_MODE));

        if (code_mode == 0)
        {
            RunEncode(inputFile, outputFile, min_length);
        }
        else
        {
            RunDecode(inputFile, outputFile, min_length);
        }
    }
}

/*private static void RunEncode(String inputFile, String outputFile, int min_length) throws IOException
    {
        DataInputStream readerInput = null;
        BufferedWriter writerOutput = null;

        try {
            readerInput = new DataInputStream(new FileInputStream(inputFile));
            writerOutput = new BufferedWriter(new FileWriter(outputFile));
        }
        catch (IOException exception){
            throw new IOException("Wrong file name.");
        }

        if(min_length <= 0) {
            throw new IOException("Wrong portion lenght! Expected: >=0. Found:" + min_length);
        }

        byte i = (byte)readerInput.read();
        int k = 1;

        while (i != -1)
        {
            byte j = (byte)readerInput.read();

            while (j == i)
            {
                k++;
                j = (byte)readerInput.read();
            }

            k = k >= min_length ? k : 1;
            writerOutput.write((byte)k);
            writerOutput.flush();
            writerOutput.write(i);
            writerOutput.flush();
            i = j;
            k = 1;
        }

    }*/

    /*private static void RunDecode(String inputFile, String outputFile, int min_length) throws IOException
    {
        DataInputStream readerInput = null;
        BufferedWriter writerOutput = null;

        try {
            readerInput = new DataInputStream(new FileInputStream(inputFile));
            writerOutput = new BufferedWriter(new FileWriter(outputFile));
        }
        catch (IOException exception){
            throw new IOException("Wrong file name.");
        }

        if(min_length <= 0) {
            throw new IOException("Wrong portion lenght! Expected: >=0. Found:" + min_length);
        }

        byte k = 0;
        while((k = (byte)readerInput.read()) != -1)
        {
            byte j = (byte)readerInput.read();
            while( k > 0)
            {
                writerOutput.write((char)j);
                writerOutput.flush();
                k--;
            }
        }
    }*/
    /*private static void RunDecode(String inputFile, String outputFile, int min_length) throws IOException
    {
        BufferedReader readerInput = new BufferedReader( new InputStreamReader(new FileInputStream(inputFile), "Cp1251"));
        BufferedWriter writerOutput = new BufferedWriter(new FileWriter(outputFile));

        if(min_length <= 0) {
            throw new IOException("Wrong portion lenght! Expected: >=0. Found:" + min_length);
        }

        byte k = 0;
        while((k = (byte)readerInput.read()) != -1)
        {
            byte j = (byte)readerInput.read();
            while( k > 0)
            {
                writerOutput.write((char)j);
                writerOutput.flush();
                k--;
            }
        }
    }*/
