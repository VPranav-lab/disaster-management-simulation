package disaster_management;

import java.util.ArrayList;
import java.util.List;

public class ResourceRegistry<T extends Resource> {
	private List<T> resources = new ArrayList<>();

    public void add(T resource) {
    	resources.add(resource);
    }

    public void remove(T resource) {
    	resources.remove(resource);
    }

    public List<T> getAll() {
        return resources;
    }
}
