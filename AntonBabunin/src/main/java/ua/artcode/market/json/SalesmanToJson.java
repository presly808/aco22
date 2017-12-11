package ua.artcode.market.json;

import com.google.gson.*;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;

import java.lang.reflect.Type;


public class SalesmanToJson
        implements JsonSerializer<Salesman>, JsonDeserializer<Salesman> {
    @Override
    public Salesman deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        String login = object.get("login").getAsString();
        String password = object.get("password").getAsString();
        return new Salesman(login, password);
    }

    @Override
    public JsonElement serialize(Salesman salesman, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("login", salesman.getLogin());
        object.addProperty("password", salesman.getPassword());
        return object;
    }
}

/*

Employee salesman = new Salesman("asd", "asd", "asd", new Money(123, 0));
    GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Salesman.class, new SalesmanToJson());
        String gson = builder.create().toJson(salesman);

        System.out.println(gson);*/
