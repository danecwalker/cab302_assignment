package cabbypatty.cab302_assignment.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Journal {
    private int id;
    private String title;
    private String body;
    private Date date;
    private Integer author_id;

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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    public Integer getAuthorID() {
        return author_id;
    }
}
