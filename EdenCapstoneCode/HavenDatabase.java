package application;

import java.io.Serializable;
import java.util.HashMap;

public class HavenDatabase extends HashMap<String, Author> implements Serializable {

    HavenDatabase() {
        super();
    }
}
