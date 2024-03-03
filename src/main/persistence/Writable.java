package persistence;



import org.json.JSONObject;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/*
An interface containing a toJson method that every savable object will implement
 */
public interface Writable {

    //EFFECTS: Turns objet into JSONObject
    JSONObject toJson();
}
