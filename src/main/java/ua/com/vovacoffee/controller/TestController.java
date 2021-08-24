package ua.com.vovacoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.vovacoffee.service.ShoppingCartService;

@Controller
public class TestController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public TestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView getTestPage(ModelAndView modelAndView) {
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/test");
        return modelAndView;
    }
}
