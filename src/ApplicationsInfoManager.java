import java.util.ArrayList;
import java.util.Collections;

class ApplicationsInfoManager extends CSVReader{
    private String[][] applicationsCSVLines;
    private ArrayList<ApplicationInfo> applications = new ArrayList<>();

    ApplicationsInfoManager(String filePath){
        setApplicationsCSVLines(csvToArray(filePath));
        setApplications(applicationsCSVLines);
        sortApplications(applications);

    }

    /**
     * Objects are sorted according to their ID values.
     * @param Applications ArrayList of applications objects.
     */
    private void sortApplications(ArrayList<ApplicationInfo> Applications){
        Collections.sort(Applications);
    }

    /**
     * This method returns a copy of applications list.
     * @return cloned arraylist of applications
     */
    public ArrayList<ApplicationInfo> getApplications() {
        if (applications == null) {
            return null;
        }
        return (ArrayList<ApplicationInfo>) applications.clone();
    }

    /**
     * This method creates an ArrayList of ApplicationInfo objects using 2D array of csv file
     * @param applicationsCSVLines a 2D String Array representation of csv file. Array[line][comma separated element]
     */
    private void setApplications(String[][] applicationsCSVLines){
        for (String[] applicationCSVLine : applicationsCSVLines){
            applications.add(new ApplicationInfo(applicationCSVLine));
        }
    }

    public void setApplicationsCSVLines(String[][] applicationsCSVLines) {
        this.applicationsCSVLines = applicationsCSVLines;
    }

}
