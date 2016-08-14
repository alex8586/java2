package lv.javaguru.java2.servlet;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;

abstract class MVCController {

    public MVCModel doGet(HttpServletRequest request){
        System.out.println(" in " + this.getClass().getName() + " executeGet()");
        MVCModel model = executeGet(request);
        System.out.println(" out " + this.getClass().getName() + " executeGet()");
        return model;
    }
    protected MVCModel executeGet(HttpServletRequest request){
        throw  new NotImplementedException();
    };

    public MVCModel doPost(HttpServletRequest request){
        System.out.println(" in " + this.getClass().getName() + " executeGet()");
        MVCModel model = executePost(request);
        System.out.println(" out " + this.getClass().getName() + " executeGet()");
        return model;
    }
    protected MVCModel executePost(HttpServletRequest request){
        throw  new NotImplementedException();
    }

}
