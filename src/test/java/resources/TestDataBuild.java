package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload() {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("J A Frias 7777");
        p.setLanguague("French-IN");
        p.setPhone_number("+5491111111111");
        p.setWebSite("www.google.com");
        p.setName("Gaston");
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
