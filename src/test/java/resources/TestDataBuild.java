package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address) {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguague(language);
        p.setPhone_number("+5491111111111");
        p.setWebSite("www.google.com");
        p.setName(name);
        List<String> myList = new ArrayList<>();
        myList.add("Shoe Park");
        myList.add("Shop");
        p.setTypes(myList);
        Location l = new Location();
        l.setLat(2.3);
        l.setLng(-3.2);
        p.setLocation(l);

        return p;
    }
}
