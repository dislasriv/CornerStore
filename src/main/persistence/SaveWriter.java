package persistence;

import model.Player;
import model.Store;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/*
class that writes current app state to file
 */
public class SaveWriter {

    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs file writer to write to save.
    //          Also opens writer; throws FileNotFoundException if destination file cannot
    //          be opened for writing
    public SaveWriter(String destination) throws FileNotFoundException {
        this.destination = destination;
        writer = new PrintWriter(new File(destination));
    }

    //EFFECTS: Turns every dynamic field active in the app into JSON text that the writer
    //         prints to the save file (using writer.print).
    public void writeData(Store store, int day) {
        //call a bunch of toJSONS
        //might just sum up in one method and then close
        JSONObject app = new JSONObject();
        JSONObject storeJson = store.toJson();
        app.put("store", storeJson);
        app.put("day", day);

        writer.println(app.toString(4));
    }

    //getters
    public PrintWriter getWriter() {
        return writer;
    }

    public String getDest() {
        return destination;
    }


}
