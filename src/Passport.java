public class Passport {
    private String id;
    private String passportNum;
    private Date expirationDate;

    Passport(String id, String passportNum, Date date){
        setId(id);
        setPassportNum(passportNum);
        setExpirationDate(date);
    }

    Passport(Passport passport){
        setPassportNum(passport.getPassportNum());
        setId(passport.getId());
        setExpirationDate(passport.getExpirationDate());
    }

    Passport(ApplicationInfo applicationInfo){
        setPassportNum(applicationInfo.getApplicationInfo()[2]);
        setId(applicationInfo.getId());
        setExpirationDate(new Date(applicationInfo.getApplicationInfo()[3]));

    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getPassportNum() {
        return passportNum;
    }

    private void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    private void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isValid(){

        String lastThree = passportNum.substring(passportNum.length() - 3);
        boolean isLastThreeNumeric = isNumeric(lastThree);
        boolean isValidExpDate = true;


        return passportNum.length() == 10 && passportNum.toCharArray()[0] == 'P'
                && isLastThreeNumeric;
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone(){
        return new Passport(this);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id='" + id + '\'' +
                ", passportNum='" + passportNum + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
