package hw1.controller;

import hw1.model.*;

import java.util.List;
import java.util.Map;

public interface IAppDB {

    DBItem create(Class c);

    DBItem save(DBItem item);

    DBItem update(DBItem item);

    DBItem findById(int id, Class c);

    List<DBItem> getAll(Class c);

}
