package lv.javaguru.java2.servlet;

public class MVCModel {

    private final Object data;
    private final String viewName;
    private final boolean isRedirect;

    public MVCModel(Object data, String viewName) {
        this.data = data;
        this.viewName = viewName;
        this.isRedirect = false;
    }
    public MVCModel(String viewName) {
        this.data = null;
        this.viewName = "/java2" + viewName;
        this.isRedirect = true;
    }

    public Object getData() {
        return data;
    }

    public String getViewName() {
        return viewName;
    }

    public boolean isRedirect(){
        return isRedirect;
    }
}
