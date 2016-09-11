package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PathResolver {

    private static String DEFAULT_PATH = "error.jsp";
    private Map<String, Object> straightRoutes = new HashMap<>();
    private Map<String, String> reversedRoutes = new HashMap<>();

    public void registerPath(String path, MVCController controller) {
        straightRoutes.put(path, controller);
        System.out.println(controller + " " + getOriginalClassName(controller));
        reversedRoutes.put(getOriginalClassName(controller), path);
    }

    public void setAlias(Class alias, Class of) {
        String route = reversedRoutes.getOrDefault(of.getSimpleName(), DEFAULT_PATH);
        reversedRoutes.put(alias.getSimpleName(), route);
    }

    public MVCController getPathFor(String path) {
        return (MVCController) straightRoutes.get(path);
    }

    public String linkTo(String controller) {
        return reversedRoutes.getOrDefault(controller, DEFAULT_PATH);
    }

    public String linkTo(String controller, String id) {
        if (reversedRoutes.containsKey(controller))
            return reversedRoutes.get(controller) + "?id=" + id;
        else
            return DEFAULT_PATH;
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

    private String getOriginalClassName(MVCController controller) {
        String name = controller.getClass().getSimpleName();
        if (name.contains("$$")) {
            name = name.substring(0, name.indexOf("$"));
        }
        return name;
    }
}
