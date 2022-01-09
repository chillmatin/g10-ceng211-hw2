public class EducationalChecker extends ResultChecker {
    Applicant applicant = getApplicant();
    int income = applicant.getFinancialStatus().getIncome();
    int savings = applicant.getFinancialStatus().getSavings();
    int durationInLA;
    boolean hasLA = false;
    boolean hasIL = false;
    boolean hasGC = false;
    boolean isAccepted = true;

    /**
     * PRECONDITION: Applicant already has valid passport, valid photo and valid FinancialStatusDocument.
     * @param applicant
     */
    EducationalChecker(Applicant applicant) {
        super(applicant);
        checkDocuments();
        if (isAccepted) {
            checkFinancialStability();
        }
        if (isAccepted) {
            checkLetterOfAcceptance();
        }
        if (isAccepted) {
            determineVisaDuration();
        }
    }

    private void checkDocuments() {
        for (Document document : applicant.getDocuments()) {
            if (document.getType().equals("GC")) {
                hasGC = true;
            } else if (document.getType().equals("IL")) {
                hasIL = true;
            } else if (document.getType().equals("LA")) {
                hasLA = true;
            }

        }
    }


    private void checkFinancialStability() {
        int[] incomesArr = {1000, 2000, 3000};
        int[] savingsArr = {6000, 3000};

        if(hasIL){
            incomesArr = new int[]{500, 1000, 1500};
            savingsArr = new int[]{3000, 1500};
        }

        if (income < incomesArr[0]){
            setIsAccepted(false);
            setRejectionReason(7);      // Applicant does not have a stable financial status
        }else if (income < incomesArr[1] && savings < savingsArr[0]){
            setIsAccepted(false);
            setRejectionReason(7);
        }else if (income < incomesArr[2] && savings < savingsArr[1]){
            setIsAccepted(false);
            setRejectionReason(7);
        }
    }

    private void checkLetterOfAcceptance() {
        if (!hasLA){
            setIsAccepted(false);
            setRejectionReason(8); // Applicant does not have a letter of acceptance
        }else{
            durationInLA = 0;
            for (Document document : applicant.getDocuments()){
                if (document.getType().equals("LA")){
                    durationInLA = document.getDuration();
                }
            }
        }
    }

    private void determineVisaDuration() {
        String[] durations = {"1 year", "2 years", "4 years"};
        Date today = getToday();

        if (durationInLA * 30 >= 2 * 365 && today.compareTo(applicant.getPassport().getExpirationDate()) <= -4 * 365){
            setVisaDuration(durations[2]);
        } else if( durationInLA * 30 >= 365 && today.compareTo(applicant.getPassport().getExpirationDate()) <= -2 * 365 ){
            setVisaDuration(durations[1]);
        } else if ( durationInLA * 30 <= 365 && today.compareTo(applicant.getPassport().getExpirationDate()) <= -365){
            setVisaDuration(durations[0]);
        } else {
            setIsAccepted(false);
            setRejectionReason(9);  // Expiration date is not suitable
            // TODO NOTHING GIVEN ABOUT WHAT TO GIVE AS REASON IN THE PDF!

        }

    }

    public String getResult(){      // POLYMORPHISM YAAAY
        if (isAccepted){
            return "Applicant ID: " + applicant.getId() + ", " +
                    "Name: " + applicant.getName() + ", " +
                    "Visa Type: " + applicant.getVisaType() +", " +
                    "Status: " + "Accepted, " +
                    "Visa Duration: " + getVisaDuration();
        } else{
            return super.getResult();
        }
    }

    boolean isAccepted() {
        return isAccepted;
    }

    void setIsAccepted(boolean accepted) {
        super.setIsAccepted(false);
        isAccepted = accepted;
    }


}


