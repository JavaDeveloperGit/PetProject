package ua.com.vovacoffee.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.vovacoffee.exception.ForbiddenException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.*;
import ua.com.vovacoffee.service.*;

import java.util.ArrayList;

@Controller
public class HomeController {

    private ProductService productService;

    private CategoryService categoryService;

    private ShoppingCartService shoppingCartService;

    private OrderService orderService;

    private StatusService statusService;

    private RoleService roleService;

    private SenderService senderService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService,
                          ShoppingCartService shoppingCartService, OrderService orderService,
                          StatusService statusService, RoleService roleService, SenderService senderService) {
        super();
        this.productService = productService;
        this.categoryService = categoryService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.statusService = statusService;
        this.roleService = roleService;
        this.statusService = statusService;
        this.senderService = senderService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("products", productService.getRandom(12));
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/home");
        return modelAndView;
    }

    @RequestMapping(value = "/category_{url}", method = RequestMethod.GET)
    public ModelAndView viewProductsInCategory(@PathVariable("url") String url, ModelAndView modelAndView) {
        modelAndView.addObject("category", categoryService.get(url));
        modelAndView.addObject("products", productService.getByCategoryUrl(url));
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/category");
        return modelAndView;
    }

    @RequestMapping(value = "/all_products", method = RequestMethod.GET)
    public ModelAndView viewAllProducts(ModelAndView modelAndView) {
        modelAndView.addObject("products", productService.getAll());
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/products");
        return modelAndView;
    }

    @RequestMapping(value = "/product_{url}", method = RequestMethod.GET)
    public ModelAndView viewProduct(@PathVariable("url") String url, ModelAndView modelAndView) {
        Product product;

        try {
            int article = Integer.parseInt(url);
            product = productService.getByArticle(article);
        } catch (NumberFormatException ex) {
            product = productService.getByUrl(url);
        }

        long categoryId = product.getCategory().getId();

        modelAndView.addObject("product", product);
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.addObject("featured_products", productService.getRandomByCategoryId(4, categoryId, product.getId()));
        modelAndView.setViewName("client/product");
        return modelAndView;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView viewCart(ModelAndView modelAndView) {
        modelAndView.addObject("sale_positions", shoppingCartService.getSalePositions());
        modelAndView.addObject("price_of_cart", shoppingCartService.getPrice());
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/cart");
        return modelAndView;
    }

    @RequestMapping(value = "/cart_add", method = RequestMethod.POST)
    public ModelAndView addProductToCart(@RequestParam long id, ModelAndView modelAndView) {
        SalePosition salePosition = new SalePosition(productService.get(id), 1);
        shoppingCartService.add(salePosition);
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    @RequestMapping(value = "/cart_add", method = RequestMethod.GET)
    public void addProductToCart() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/cart_add\" is not supported!");
    }

    @RequestMapping(value = "/cart_add_quickly", method = RequestMethod.POST)
    public ModelAndView addProductToCartQuickly(@RequestParam long id,
                                                @RequestParam String url,
                                                ModelAndView modelAndView) {
        SalePosition salePosition = new SalePosition(productService.get(id), 1);
        shoppingCartService.add(salePosition);
        modelAndView.setViewName("redirect:" + url);
        return modelAndView;
    }

    @RequestMapping(value = "/cart_add_quickly", method = RequestMethod.GET)
    public void addProductToCartQuickly() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/cart_add_quickly\" is not supported!");
    }

    @RequestMapping(value = "/cart_clear", method = RequestMethod.GET)
    public ModelAndView clearCart(ModelAndView modelAndView) {
        shoppingCartService.clear();
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ModelAndView viewCheckout(@RequestParam(value = "user_name") String name,
                                     @RequestParam(value = "user_email") String email,
                                     @RequestParam(value = "user_phone") String phone,
                                     ModelAndView modelAndView) {
        if (shoppingCartService.getSize() > 0) {
            Role role = roleService.getDefault();
            User client = new User(name, email, phone, role);

            Status status = statusService.getDefault();
            Order order = new Order(status, client, new ArrayList<SalePosition>(shoppingCartService.getSalePositions()));
            orderService.add(order);

            senderService.send(order);

            modelAndView.addObject("order", order);
            modelAndView.addObject("sale_positions", order.getSalePositions());
            modelAndView.addObject("price_of_cart", shoppingCartService.getPrice());
            modelAndView.addObject("cart_size", 0);
            modelAndView.setViewName("client/checkout");

            shoppingCartService.clear();
        } else {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView viewCheckout(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    @RequestMapping(value = "/forbidden_exception", method = RequestMethod.GET)
    public void getForbiddenException() throws ForbiddenException {
        throw new ForbiddenException("You do not have sufficient permissions to access this page.");
    }
}
