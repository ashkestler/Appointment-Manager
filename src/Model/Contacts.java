package Model;

public class Contacts {
    private int contactId;
    private String name;
    private String email;

    /**
     * This is the constructor for the Contacts class.
     * @param contactId
     * @param name
     * @param email
     */
    public Contacts(int contactId, String name, String email) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
    }

    /*
    **** Getters
     */
    public int getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return (name);
    }
}
