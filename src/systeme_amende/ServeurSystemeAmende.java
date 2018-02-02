package systeme_amende;

import javax.xml.ws.Endpoint;

public class ServeurSystemeAmende {
    static public void main(String[] args){
        Endpoint.publish("http://localhost:9999/ws/systemeamendes", new SystemeAmendesImpl());
    }
}
