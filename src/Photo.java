public class Photo {
    private String id;
    private String resolution;
    private String position;

    Photo(String id, String resolution, String position) {
        setId(id);
        setResolution(resolution);
        setPosition(position);
    }

    Photo(Photo photo){
        setPosition(photo.getPosition());
        setResolution(photo.getResolution());
        setId(photo.getId());
    }

    Photo(ApplicationInfo applicationInfo){
        setId(applicationInfo.getId());
        setResolution(applicationInfo.getApplicationInfo()[2]);
        setPosition(applicationInfo.getApplicationInfo()[3]);
    }

    public boolean isValid(){
        String[] resolutionElements = getResolution().split("x");
        int side = Integer.parseInt(resolutionElements[0]);
        boolean isSizeOk = 600 <= side && 1200 >= side;
        return resolutionElements[0].equals((resolutionElements)[1]) && isSizeOk;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getResolution() {
        return resolution;
    }

    private void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPosition() {
        return position;
    }

    private void setPosition(String position) {
        this.position = position;
    }

    @Override
    public Object clone(){
        return new Photo(this);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", resolution='" + resolution + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
