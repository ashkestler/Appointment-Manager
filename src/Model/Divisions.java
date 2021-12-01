package Model;

public class Divisions {
    private int divisionId;
    private String division;
    private int countryId;

    /**
     * This is the constructor for the Divisions class.
     * @param divisionId
     * @param division
     * @param countryId
     */
    public Divisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /*
    **** Getters
     */
    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return (division);
    }
}
