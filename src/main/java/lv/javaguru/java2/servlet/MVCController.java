package lv.javaguru.java2.servlet;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;

public abstract class MVCController {


    public final MVCModel doGet(HttpServletRequest request) {
        System.out.println(" in " + this.getClass().getName() + " executeGet()");
        MVCModel model = executeGet(request);
        System.out.println(" out " + this.getClass().getName() + " executeGet()");
        return model;
    }
    protected MVCModel executeGet(HttpServletRequest request){
        throw  new NotImplementedException();
    }

    public final MVCModel doPost(HttpServletRequest request) {
        System.out.println(" in " + this.getClass().getName() + " executePost()");
        MVCModel model = executePost(request);
        System.out.println(" out " + this.getClass().getName() + " executePost()");
        return model;
    }
    protected MVCModel executePost(HttpServletRequest request){
        throw new NotImplementedException();
    }


    public final MVCModel doDelete(HttpServletRequest request) {
        System.out.println(" in " + this.getClass().getName() + " executeDelete()");
        MVCModel model = executeDelete(request);
        System.out.println(" out " + this.getClass().getName() + " executeDelete()");
        return model;
    }

    protected MVCModel executeDelete(HttpServletRequest request) {
        throw  new NotImplementedException();
    }
}
