package resources;
//Enum is special class in java which has collection of constants or methods
public enum APIResources {
    ADDPLACEAPI("/maps/api/place/add/json"),
    GETPLACEAPI("/maps/api/place/get/json"),
    DELETEPLACEAPI("/maps/api/place/delete/json");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return this.resource;
    }
}
