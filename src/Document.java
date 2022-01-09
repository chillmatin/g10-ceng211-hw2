public class Document {

    private String id;
    private String type;
    private int duration;   // in months


    Document(String id, String type, int duration){
        setId(id);
        setType(type);
        setDuration(duration);
    }

    Document(String id, String type){
        setId(id);
        setType(type);
    }

    Document(Document doc){
        setId(doc.getId());
        setType(doc.getType());
        setDuration(doc.getDuration());
    }

    Document(ApplicationInfo applicationInfo){
        setId(applicationInfo.getId());
        setType(applicationInfo.getApplicationInfo()[2]);

        int size = applicationInfo.getApplicationInfo().length;

        if(size > 3){
            String durationString = applicationInfo.getApplicationInfo()[3];
            setDuration(Integer.valueOf(durationString));
        }

    }



    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public Object clone(){
        return new Document(this);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", duration=" + duration +
                '}';
    }
}
