package lv.javaguru.java2.servlet;

import java.util.HashMap;
import java.util.Map;

public class ReverseRouter {
    private Map<String, String> routes;
    private String defaultPath;

    public ReverseRouter(Map<String, ?> controllers, String defaultPath) {
        this.defaultPath = defaultPath;
        routes = new HashMap<String, String>();
        for (Map.Entry<String, ?> controllerAndRoute : controllers.entrySet()) {
            routes.put(controllerAndRoute.getValue().getClass().getSimpleName(), controllerAndRoute.getKey().substring(1));
        }
    }

    public String linkTo(String controller) {
        return routes.getOrDefault(controller, defaultPath);
    }

    public String linkTo(String controller, String id) {
        if (routes.containsKey(controller))
            return routes.get(controller) + "?id=" + id;
        else
            return defaultPath;
    }
}
