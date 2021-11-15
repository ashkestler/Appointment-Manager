package Model;

import java.time.LocalDateTime;

public class Appointments {
    private int apptId;
    private String title;
    private String desc;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int custId;
    private int userId;
    private String contact;

    public Appointments(int apptId, String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end, int custId, int userId, String contact) {
        this.apptId = apptId;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.custId = custId;
        this.userId = userId;
        this.contact = contact;
    }

    public int getApptId() {
        return apptId;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public int getCustId() {
        return custId;
    }

    public int getUserId() {
        return userId;
    }

    public String getContact() {
        return contact;
    }
}
