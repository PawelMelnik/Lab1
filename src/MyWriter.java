import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MyWriter {
    private final static int ASCII_0 = 48;
    private FileOutputStream outputFOS;
    private FileWriter writer;

    MyWriter(String fileName) {
        OpenStream(fileName);
    }

    public void OpenStream(String fileName) {
        try {
            outputFOS = new FileOutputStream(fileName);
        } catch (IOException e) {
            Log.report("Can't open file " + fileName);
        }
    }

    public void CloseStream() {
        try {
            if (outputFOS != null)
                outputFOS.close();
        } catch (IOException e) {
            Log.report("Output stream can't be closed.");
        }
    }

    public void CloseFileWriter() {
        try {
            if (writer != null)
                writer.close();
        } catch (IOException e) {
            Log.report("Can't close FileWriter");
        }
    }

    public void InitFileWriter(String outputFile) {
        try {
            writer = new FileWriter(outputFile);
        } catch (IOException ex) {
            Log.report("Can't init FileWriter");
        }
    }

}
