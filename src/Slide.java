import java.util.ArrayList;

public class Slide {

    private ArrayList<Photo> photos;
    private ArrayList<String> tags;

    public Slide(ArrayList<Photo> photos) {
        this.photos = new ArrayList<>(photos);
        tags = new ArrayList<>();
        this.photos.forEach(photo -> photo.getTags().forEach(tag -> {
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }));
    }


    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        String indexes = "";
        for (Photo photo : this.photos) {
            indexes += photo.getIndex() + " ";
        }
        return "Indexes: " + indexes + "Tags: " + this.tags;
    }
}
