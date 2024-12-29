package guru.qa.parkMenuData;

public enum MenuData {
    PARK("Park"),
    EXPLORE("Explore");

    public final String description;

    MenuData(String description) {
        this.description = description;
    }
}
