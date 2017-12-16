package ua.artcode.market.exclude.Utils;

import com.sun.net.httpserver.HttpExchange;
import ua.artcode.market.exceptions.ServerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerUtils {
    public static List<String> getParams(HttpExchange httpExchange) throws ServerException {
        String requestUrl = httpExchange.getRequestURI().toString();

        if (!requestUrl.contains("?")) {
            throw new ServerException("url does not have any params");
        }

        String[] params = requestUrl.split("\\?")[1].split("&");
        List<String> res = new ArrayList<>();

        Arrays.stream(params).forEach(s -> res.add(s.split("=")[1]));

        return res;
    }
}