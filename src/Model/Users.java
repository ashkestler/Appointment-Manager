package Model;

public class Users {
    private int userId;
    private String userName;
    private String password;

    /**
     * This is the constructor for the Users class.
     * @param userId
     * @param userName
     * @param password
     */
    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /*
    **** Getters
     */
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return (userName);
    }
}
