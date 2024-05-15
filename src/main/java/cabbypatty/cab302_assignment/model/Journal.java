package cabbypatty.cab302_assignment.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A simple model class representing a journal with an id, title, body, date, author_id, and mood.
 */
public class Journal {
    private int id;
    private String title;
    private String body;
    private Date date;
    private Integer author_id;

    private Integer mood;

    /**
     * Constructs a new Journal data with the specified id, body, date, and author_id.
     * @param id The id of journal
     * @param body The body of journal
     * @param date The date of journal
     * @param author_id The author_id of journal
     */
    public Journal(int id, String body, Date date, Integer author_id) {
        this.id = id;
        ZoneId systemTimeZone = ZoneId.systemDefault();

        // Convert the Instant to ZonedDateTime in the local time zone
        ZonedDateTime localDateTime = date.toInstant().atZone(systemTimeZone);
        // 19 August 2021 - 12:00pm local time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy - h:mma");
        this.title = localDateTime.format(formatter);
        this.body = body;
        this.date = date;
        this.author_id = author_id;
    }

    /**
     * Returns the ID of the journal class represented by this Class object as an int.
     * @return id The id of the journal
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the title of the journal class represented by this Class object as a String.
     * @return title The title of the journal
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the body of the journal class represented by this Class object as a String.
     * @return body The body of the journal
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the date of the journal class represented by this Class object as a Date.
     * @return date The date of the journal
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the author_id of the journal class represented by this Class object as an Integer.
     * @return id The author_id of the journal
     */
    public Integer getAuthorID() {
        return author_id;
    }
}
