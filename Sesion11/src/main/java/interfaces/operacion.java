package interfaces;

import com.sun.jdi.Value;
import models.Student;

public interface operacion {

    public void create(Object object);

    public Object read();

    public void update(Object object);

    public void delete(String id);

    public Object search(Value name);

    Student search(String value);
}
