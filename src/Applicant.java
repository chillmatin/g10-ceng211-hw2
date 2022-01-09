/* class Applicant:
ID, name
Passport
Photo
Financial Status
Document
 */

import java.util.ArrayList;

public class Applicant {

    private String id;
    private String name;
    private Passport passport;
    private Photo photo;
    private FinancialStatus financialStatus;
    private ArrayList<Document> documents;
    private VisaType visaType;

    public Applicant(Applicant applicant) {
        setId(applicant.getId());
        setName(applicant.getName());
        setPassport(applicant.getPassport());
        setPhoto(applicant.getPhoto());
        setFinancialStatus(applicant.getFinancialStatus());
        setDocuments(applicant.getDocuments());
        setVisaType(applicant.getVisaType());
    }

    public Applicant(String id, String name, Passport passport,
                     Photo photo, FinancialStatus financialStatus, ArrayList<Document> documents)
    {
        setId(id);
        setName(name);
        setPassport(passport);
        setPhoto(photo);
        setFinancialStatus(financialStatus);
        setDocuments(documents);
        setVisaType();
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        if (passport == null){
            return null;
        }
        return (Passport) passport.clone();
    }

    private void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Photo getPhoto() {
        if (photo == null){
            return null;
        }
        return (Photo) photo.clone();
    }

    private void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public FinancialStatus getFinancialStatus() {
        if (financialStatus == null){
            return null;
        }
        return (FinancialStatus) financialStatus.clone();
    }

    private void setFinancialStatus(FinancialStatus financialStatus) {
        this.financialStatus = financialStatus;
    }

    public ArrayList<Document> getDocuments() {
        if (documents == null){
            return null;
        }
        return (ArrayList<Document>) documents.clone();
    }

    private void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public VisaType getVisaType() {
        return visaType;
    }

    private void setVisaType(VisaType visaType) {
        this.visaType = visaType;
    }

    private void setVisaType(){
        String typeCode = id.substring(0,2);

        switch (typeCode) {
            case "11" -> setVisaType(VisaType.Tourist);
            case "23" -> setVisaType(VisaType.Worker);
            case "25" -> setVisaType(VisaType.Educational);
            case "30" -> setVisaType(VisaType.Immigrant);
        }
    }


    @Override
    public Object clone(){
        return new Applicant(this);
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", passport=" + passport +
                ", photo=" + photo +
                ", financialStatus=" + financialStatus +
                ", documents=" + documents +
                '}';
    }
}
