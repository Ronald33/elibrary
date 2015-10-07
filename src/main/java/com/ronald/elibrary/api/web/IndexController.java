package com.ronald.elibrary.api.web;

//import java.util.Locale;
import javax.servlet.http.HttpServlet;
//import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("index")
public class IndexController extends HttpServlet
{
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView handleRequestInternal()
    {
//        Locale locale = LocaleContextHolder.getLocale();
//        System.out.println(locale.toString());
        
        ModelAndView model = new ModelAndView("index");
        model.addObject("msg", "test");

        return model;
    }
}