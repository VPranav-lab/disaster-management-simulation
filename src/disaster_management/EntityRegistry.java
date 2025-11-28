package disaster_management;

import java.util.ArrayList;
import java.util.List;

public class EntityRegistry<T extends Entity> {
	private List<T> entities = new ArrayList<>();

    public void add(T entity) {
        entities.add(entity);
    }

    public void remove(T entity) {
        entities.remove(entity);
    }

    public List<T> getAll() {
        return entities;
    }  
}
