package lv.javaguru.java2.servlet.adminpages;

import lv.javaguru.java2.businesslogic.admin.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminStatistic {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public ModelAndView statistic(HttpServletRequest request){
        ModelAndView model = new ModelAndView("/admin_statistic");
        model.addAllObjects(statisticService.model());
        return model;
    }

    @RequestMapping(value = "/statistic/sortBy/{sortValue}", method = RequestMethod.GET)
    public ModelAndView statisticSortBy(@PathVariable String sortValue, HttpServletRequest request){
        ModelAndView model = new ModelAndView("/admin_statistic");
        model.addAllObjects(statisticService.sortBy(sortValue));
        return model;
    }
}
