public class ImmigrantChecker extends ResultChecker{
    Applicant applicant = getApplicant();
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
    ImmigrantChecker(Applicant applicant) {
        super(applicant);
        checkDocuments();
        if(isAccepted){checkFinancialStability();}
        if(isAccepted){determineVisaDuration();}
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

    private void checkFinancialStability(){
        int leastSavings = 50000;

        if(hasGC){
            leastSavings = 4000;
        }
        if(hasIL){
            leastSavings /= 2;
        }

        if(savings < leastSavings){
            setIsAccepted(false);
            setRejectionReason(7);  // "Applicant does not have a stable financial status"
        }
    }

    private void determineVisaDuration() {
        setVisaDuration("Permanent");
    }


    boolean isAccepted() {
        return isAccepted;
    }

    void setIsAccepted(boolean accepted) {
        super.setIsAccepted(false);
        isAccepted = accepted;
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


}
