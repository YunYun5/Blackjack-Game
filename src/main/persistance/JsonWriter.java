package persistance;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Class used to write JSON files. Mainly used for saving the state of the game
 * The structure of the class was inspired from the demo application given for this
 * phase of the project.
 */
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // Requires: destination != null
    // Modifies: this
    // Effects: Creates a new JSONWriter with the given file destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // Modifies: this
    // Effects: opens the writer and throws FileNotFoundException if file
    // cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // Modifies: this
    // Effects: Writes the content of the writable to the file
    public void write(Writable writable) {
        JSONObject json = writable.toJson();
        saveToFile(json.toString(TAB));
    }

    // Modifies: this
    // Effects: closes the writer
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }

    // Modifies: this
    // Effects: writes the string to the file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
