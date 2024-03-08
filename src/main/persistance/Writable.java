package persistance;

import org.json.JSONObject;

// Interface to make JSONObjects
public interface Writable {

    // Effects returns as JSONObject
    JSONObject toJson();
}
