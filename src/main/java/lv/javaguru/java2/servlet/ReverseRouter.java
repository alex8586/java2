package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.BaseEntity;

import java.util.HashMap;
import java.util.Map;

public class ReverseRouter {
    private Map<String, String> routes;
    private String defaultPath;

    public ReverseRouter(Map<String, ?> controllers, String defaultPath) {
        this.defaultPath = defaultPath;
        routes = new HashMap<String, String>();
        for (Map.Entry<String, ?> controllerAndRoute : controllers.entrySet()) {
            routes.put(controllerAndRoute.getValue().getClass().getSimpleName(), controllerAndRoute.getKey());
        }
    }

    public void setAlias(Class alias, Class of) {
        String route = routes.getOrDefault(of.getSimpleName(), defaultPath);
        routes.put(alias.getSimpleName(), route);
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

    public String linkTo(Class controllerClass) {
        return linkTo(controllerClass.getSimpleName());
    }

    public String linkTo(Class controllerClass, Long id) {
        return linkTo(controllerClass.getSimpleName(), id.toString());
    }

    public String linkTo(BaseEntity entity) {
        return linkTo(entity.getClass(), entity.getId());
    }
}
