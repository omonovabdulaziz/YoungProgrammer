package omo.nov.keyboardtrainer.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampDeserializer extends JsonDeserializer<Timestamp> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateAsString = p.getText();
        try {
            Date parsedDate = dateFormat.parse(dateAsString);
            if (parsedDate != null) {
                // Return the parsed date as a Timestamp without any modification
                return new Timestamp(parsedDate.getTime());
            } else {
                throw new IOException("Failed to parse date: " + dateAsString);
            }
        } catch (ParseException e) {
            throw new IOException("Error parsing date: " + dateAsString, e);
        }
    }
}
