import java.util.ArrayList;

public class ApplicantManager {

    private ArrayList<ApplicationInfo> applicationInfos = new ArrayList<>();
    private ArrayList<Applicant> applicants = new ArrayList<>();


    ApplicantManager(String filePath){

        ApplicationsInfoManager appInfoMan = new ApplicationsInfoManager(filePath);
        setApplicationInfos(appInfoMan.getApplications());
        setApplicants(applicationInfos);
    }

    public ArrayList<ApplicationInfo> getApplicationInfos() {
        return (ArrayList<ApplicationInfo>) applicationInfos.clone();
    }

    private void setApplicationInfos(ArrayList<ApplicationInfo> applicationInfos) {
        this.applicationInfos = applicationInfos;
    }

    public ArrayList<Applicant> getApplicants() {
        if (applicants == null){
            return null;
        }
        return (ArrayList<Applicant>) applicants.clone();
    }

    private void setApplicants(ArrayList<ApplicationInfo> applicationInfos){


        String id = null;
        String name = null;
        Passport passport = null;
        Photo photo = null;
        FinancialStatus financialStatus = null;
        ArrayList<Document> documents = new ArrayList<>();

        String currentId = "";
        boolean firstTime = true;

        for (ApplicationInfo applicationInfo : applicationInfos){

            if (!applicationInfo.getId().equals(currentId) && !firstTime){
                applicants.add(new Applicant(id, name, passport, photo, financialStatus, (ArrayList<Document>) documents.clone()));
                id = null;
                name = null;
                passport = null;
                photo = null;
                financialStatus = null;
                documents.clear();

            }
            firstTime = false;
            currentId = applicationInfo.getId();

            ApplicationInfoType infoType = applicationInfo.getType();
            switch (infoType){
                case A:
                    id = applicationInfo.getId();
                    name = applicationInfo.getApplicationInfo()[2];
                    break;
                case S:
                    id = applicationInfo.getId();
                    passport = new Passport(applicationInfo);
                    break;
                case P:
                    id = applicationInfo.getId();
                    photo = new Photo(applicationInfo);
                    break;
                case F:
                    id = applicationInfo.getId();
                    financialStatus = new FinancialStatus(applicationInfo);
                    break;
                case D:
                    id = applicationInfo.getId();
                    documents.add(new Document(applicationInfo));
                    break;
            }



        }

        applicants.add(new Applicant(id, name, passport, photo,
                financialStatus, (ArrayList<Document>) documents.clone())); // loop does not add the last one

    }

}
