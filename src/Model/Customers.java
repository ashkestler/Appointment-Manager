package Model;

public class Customers {
    private int customerId, divisionId;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private int countryId;


    public Customers(int customerId, int divisionId, String name, String address, String postalCode, String phone, String division, int countryId) {
        this.customerId = customerId;
        this.divisionId = divisionId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.countryId = countryId;
    }

    public int getCountryId() { return countryId; }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
