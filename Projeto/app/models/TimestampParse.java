package models;

/**
 * Created by miriam on 05/05/17.
 */

public class TimestampParse {

    private String timestamp;

    public String getTime() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return timestamp;
    }
}
