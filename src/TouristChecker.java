import java.util.ArrayList;

public class TouristChecker extends ResultChecker{
    Applicant applicant = getApplicant();
    int income = applicant.getFinancialStatus().getIncome();
    int savings = applicant.getFinancialStatus().getSavings();
    boolean hasLA = false;
    boolean hasIL = false;
    boolean hasGC = false;
    boolean isAccepted = true;

    /**
     * PRECONDITION: Applicant already has valid passport, valid photo and valid FinancialStatusDocument.
     * @param applicant
     */
    TouristChecker(Applicant applicant) {
        super(applicant);
        checkDocuments();
        if(isAccepted){checkFinancialStability();}
        if(isAccepted){determineVisaDuration();}

    }

    /**
     * Analyses documents and determines if an applicant has greencard, invitation letter, or letter of acceptance
     */
    private void checkDocuments(){
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

    private void checkFinancialStability(){
        int[] incomesArr = {2000, 3000, 4000};
        int[] savingsArr = {12000, 6000};

        if(hasIL){
            incomesArr = new int[]{1000, 1500, 2000};
            savingsArr = new int[]{6000, 3000};
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

    private void determineVisaDuration(){
         final String[] durations = {"6 months", "1 year", "5 years"};
         double DC;
         Date today = getToday();
         if(hasIL){
             DC = (((income - 2000.0) * 6.0 + savings)/6000.0);
         }else{
             DC = ((income - 2000.0) * 6.0 + savings)/12000.0;
         }

        if (DC >= 1 && DC < 2){
            setVisaDuration(durations[0]);
        }
        else if (DC >= 2 && DC < 4){
            if (today.compareTo(applicant.getPassport().getExpirationDate()) > -365){
                setVisaDuration(durations[0]);
            } else{
                setVisaDuration(durations[1]);
            }

        } else if (DC >= 4 ){
            if (today.compareTo((applicant.getPassport().getExpirationDate())) > -365){
                setVisaDuration(durations[0]);
            } else if (today.compareTo(applicant.getPassport().getExpirationDate()) > -365 * 5){
                setVisaDuration(durations[1]);
            } else{
                setVisaDuration(durations[2]);
            }
        }

        if (DC < 1){
            setVisaDuration("PDF SAYS NOTHING ABOUT THE CASE WHEN DC IS LESS THAN 1!");
            // TODO decide what to do if DC is less than 1
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
