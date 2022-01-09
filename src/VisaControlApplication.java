public class VisaControlApplication {

    public static void main(String[] args) {

        ApplicantManager appMan = new ApplicantManager("HW2_ApplicantsInfo.csv");
        ResultChecker resultChecker;
        for (Applicant applicant : appMan.getApplicants()){
            resultChecker = new ResultChecker(applicant);
            System.out.println(resultChecker.getResult());
        }

    }

}
