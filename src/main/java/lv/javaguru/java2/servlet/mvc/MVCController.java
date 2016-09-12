package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.dto.builders.RequestedEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;

public abstract class MVCController {

    @Autowired
    protected RequestedEntityUtil requestedEntityUtil;
    @Autowired
    private PathResolver pathResolver;

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

    protected MVCModel redirectTo(Class targetController) {
        return new MVCModel(pathResolver.linkTo(targetController));
    }
    
}
