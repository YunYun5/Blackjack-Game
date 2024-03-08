package persistance;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// JSON Writer. Writes JSON representation of the Deck
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    public void write(Writable writable) {
        JSONObject json = writable.toJson();
        saveToFile(json.toString(TAB)); // Writes with indentation for readability
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

}
