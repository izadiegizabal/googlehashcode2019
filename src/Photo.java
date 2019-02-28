import java.util.ArrayList;

public class Photo {

    private Integer index;
    private Boolean vertical;
    private ArrayList<String> tags;

    public Photo(Integer index, Boolean vertical, ArrayList<String> tags){
        this.index = index;
        this.vertical = vertical;
        this.tags = new ArrayList<>(tags);
    }

    @Override
    public String toString() {
        return "Index: " + this.index + "\nOrientation: " + (vertical ? "Vertical" : "Horizontal") +  "\nTags: " + tags.toString() + "\n";
    }

    public Boolean getVertical() {
        return vertical;
    }

    public Integer getIndex() {
        return index;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setVertical(Boolean vertical) {
        this.vertical = vertical;
    }
}
