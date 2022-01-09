public class ResultChecker{
    private Applicant applicant;
    private String result = "No appropriate visa time found!";  // initial value
    boolean isAccepted = true;
    private String visaDuration;
    private String rejectionReason;
    private Date today = new Date(java.time.LocalDate.now().toString());



    public void setToday(Date today) {
        this.today = today;
    }

    /**
     * PRECONDITION: Applicant already has valid passport, valid photo and valid FinancialStatusDocument.
     * @param applicant
     */
    ResultChecker(Applicant applicant) {
        setApplicant(applicant);
        if(isAccepted){checkPassport();}
        if(isAccepted){checkPhoto();}
        if(isAccepted){checkFinancialStatusReport();}

    }

    /**
     * This method detects applicant's visa type and returns the application result by
     * calling appropriate checker classes.
     * @param applicant Applicant object which carries all the information about the applicant
     * @return string representation of the result
     */
    private String evaluateResult(Applicant applicant){


        switch (applicant.getVisaType()){

            case Tourist:
                TouristChecker touristChecker = new TouristChecker(applicant);
                setResult(touristChecker.getResult());
                break;

            case Worker:
                WorkerChecker workerChecker = new WorkerChecker(applicant);
                setResult(workerChecker.getResult());
                break;

            case Educational:
                EducationalChecker educationalChecker = new EducationalChecker(applicant);
                setResult(educationalChecker.getResult());
                break;

            case Immigrant:
                ImmigrantChecker immigrantChecker = new ImmigrantChecker(applicant);
                setResult(immigrantChecker.getResult());
                break;
        }

        return result;
    }

    /**
     * This method checks if an applicant has a valid photo.
     * It also changes isAccepted into a false state in case of invalidity detection
     * @return false if applicant does not have a photo
     *               if resolution of photo is not valid
     *               if position in the photo is not valid
     *
     *         true if applicant has a valid photo
     */
    private boolean checkPhoto(){
        if (applicant.getPhoto() == null){
            setIsAccepted(false);
            setRejectionReason(3);  // Applicant does not have a photo
            return false;
        } else if (!applicant.getPhoto().isValid()){
            setIsAccepted(false);
            setRejectionReason(4);  // Resolution of photo is not valid
            return false;
        }

        String position = applicant.getPhoto().getPosition();
        if (!(position.equals("Neutral Face") || position.equals("Natural Smile"))){
            setIsAccepted(false);
            setRejectionReason(5);  // Position in the photo is not valid
            return false;
        }
        return true;
    }

    /**
     * This method checks if an applicant has valid passport. It also sets the RejectionReason and isAccepted accordingly
     *
     * @return false if passport is invalid
     *         true if passport is valid
     */
    private boolean checkPassport(){
        if (applicant.getPassport() == null){
            setIsAccepted(false);
            setRejectionReason(0);  // Applicant does not have a passport
            return false;
        } else if (!applicant.getPassport().isValid()){
            setIsAccepted(false);
            setRejectionReason(1);  // Passport is not valid
            return false;
        } else{
            Date expirationDate = applicant.getPassport().getExpirationDate();
            Date today = new Date(java.time.LocalDate.now().toString());
            if (today.compareTo(expirationDate) > -30 * 6){
                setIsAccepted(false);
                setRejectionReason(2);  // "Passport expiration date is not valid"
            }
        }

        Date today = new Date(java.time.LocalDate.now().toString());
        Date expirationDate = applicant.getPassport().getExpirationDate();

        if (today.compareTo(expirationDate) >= -6 * 30){
            setIsAccepted(false);
            setRejectionReason(2);  // Passport expiration date is not valid
            return true;
        }

        return true;

    }

    private boolean checkFinancialStatusReport(){
        if (applicant.getFinancialStatus() == null) {
            setIsAccepted(false);
            setRejectionReason(6);  // Applicant does not have a financial status report
            return false;
        }
        return true;
    }

    public Applicant getApplicant() {
        return (Applicant) applicant.clone();
    }

    private void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public String getResult(){
        if(isAccepted){
            setResult(evaluateResult(applicant));
        }   // it helps to prevent recursion
        else {
            setResult(
                    "Applicant ID: " + applicant.getId() + ", " +
                    "Name: " + applicant.getName() + ", " +
                    "Visa Type: " + applicant.getVisaType() +", " +
                    "Status: " + "Rejected, " +
                    "Reason: " + rejectionReason);
        }
        return result;
    }

    private void setResult(String result) {
        this.result = result;
    }

    boolean isAccepted() {
        return isAccepted;
    }

    void setIsAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    String getVisaDuration() {
        return visaDuration;
    }

    void setVisaDuration(String visaDuration) {
        this.visaDuration = visaDuration;
    }

    String getRejectionReason() {
        return rejectionReason;
    }

    void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    /**
     * Sets rejection reason
     * Rejection number reasons are as follows:
     *             0. "Applicant does not have a passport"
     *             1. "Passport is not valid"
     *             2. "Passport expiration date is not valid"
     *             3. "Applicant does not have a photo"
     *             4. "Resolution of photo is not valid"
     *             5. "Position in the photo is not valid"
     *             6. "Applicant does not have a financial status report"
     *             7. "Applicant does not have a stable financial status"
     *             8. "Applicant does not have a letter of acceptance"
     *             9. "Expiration date is not valid"
     *
     * @param rejectionNumber  number code of rejection
     */
    void setRejectionReason(int rejectionNumber){
        setRejectionReason(getRejectionReasons()[rejectionNumber]);
    }

    private String[] getRejectionReasons(){
        return new String[]{
                "Applicant does not have a passport",    //0
                "Passport is not valid",                   //1
                "Passport expiration date is not valid",   //2
                "Applicant does not have a photo",         //3
                "Resolution of photo is not valid",        //4
                "Position in the photo is not valid",      //5
                "Applicant does not have a financial status report",   //6
                "Applicant does not have a stable financial status",   //7
                "Applicant does not have a letter of acceptance",      //8
                "Expiration date is not suitable"};                    //9
    }

    Date getToday() {
        return new Date(today);
    }
}
