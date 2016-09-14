package lv.javaguru.java2.businesslogic.adminka;

import org.springframework.stereotype.Component;

/**
 * Created by Maksims on 9/12/2016.
 */

@Component
public interface ViewBlock {

    String getBlockName();
    String getBlockJSP();
    Object getBlockModel();
}
