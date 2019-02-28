import java.util.ArrayList;

public class Slide {

    private ArrayList<Photo> photos;

    public Slide(ArrayList<Photo> photos) {
        this.photos = new ArrayList<>(photos);
    }


    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return this.photos.toString();
    }
}
