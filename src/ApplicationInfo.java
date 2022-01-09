import java.util.Arrays;

public class ApplicationInfo implements Comparable{
    private String[] applicationInfo;
    private String id;
    private ApplicationInfoType type;

    ApplicationInfo(String[] applicationInfo){
        setApplicationInfo(applicationInfo);
        setId(applicationInfo[1]);
        setType(applicationInfo[0]);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null){
            System.out.println("Null object is not comparable!");
            System.exit(0);

        } else if(this.getClass() != o.getClass()){
            System.out.println("Inconsistent data type.");
            System.exit(0);
        }

        ApplicationInfo otherObject = (ApplicationInfo) o;
        return id.compareTo(otherObject.getId());
    }

    /**
     * @return deep copy of applicationInfo string array
     */
    public String[] getApplicationInfo() {
        String[] copyApplicationInfo = new String[applicationInfo.length];

        for (int i = 0; i < applicationInfo.length; i++){
            copyApplicationInfo[i] = applicationInfo[i];
        }

        return copyApplicationInfo;
    }

    private void setApplicationInfo(String[] applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    public String getId() {
        return id;
    }

    /**
     * @param id id of the application information
     */
    private void setId(String id) {
        this.id = id;
    }

    public ApplicationInfoType getType() {
        return type;
    }

    public void setType(String type) {
        this.type = ApplicationInfoType.valueOf(type);
    }

    @Override
    public Object clone() {
        return new ApplicationInfo(applicationInfo);
    }

    @Override
    public String toString() {
        return "ApplicationInfo{" +
                "applicationInfo=" + Arrays.toString(applicationInfo) +
                ", id='" + id + '\'' +
                '}';
    }
}
