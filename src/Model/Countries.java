package Model;

public class Countries {
    private int countryId;
    private String country;

    /**
     * This is the constructor for the Countries class.
     * @param countryId
     * @param country
     */
    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }


    /*
    **** Getters
     */
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return (country);
    }
}

