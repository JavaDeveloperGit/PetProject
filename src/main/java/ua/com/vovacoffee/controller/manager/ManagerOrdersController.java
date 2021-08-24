package ua.com.vovacoffee.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Order;
import ua.com.vovacoffee.model.Status;
import ua.com.vovacoffee.model.User;
import ua.com.vovacoffee.service.OrderService;
import ua.com.vovacoffee.service.RoleService;
import ua.com.vovacoffee.service.StatusService;
import ua.com.vovacoffee.service.UserService;

import java.util.Date;

@Controller
@RequestMapping(value = "/manager")
public class ManagerOrdersController {

    private UserService userService;

    private OrderService orderService;

    private StatusService statusService;

    private RoleService roleService;

    @Autowired
    public ManagerOrdersController(UserService userService, OrderService orderService,
                                   StatusService statusService, RoleService roleService) {
        super();
        this.userService = userService;
        this.orderService = orderService;
        this.statusService = statusService;
        this.roleService = roleService;
    }

    @RequestMapping(value = {"", "/orders"}, method = RequestMethod.GET)
    public ModelAndView viewAllOrders(ModelAndView modelAndView) {
        modelAndView.addObject("orders", orderService.getAll());
        modelAndView.addObject("status_new", statusService.getDefault());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("manager/order/all");
        return modelAndView;
    }

    @RequestMapping(value = "/view_order_{id}", method = RequestMethod.GET)
    public ModelAndView viewOrder(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("status_new", statusService.getDefault());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.setViewName("manager/order/one");
        return modelAndView;
    }

    @RequestMapping(value = "/edit_order_{id}", method = RequestMethod.GET)
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        if (order.getManager() == null || order.getManager().equals(userService.getAuthenticatedUser())) {
            modelAndView.addObject("order", order);
            modelAndView.addObject("sales", order.getSalePositions());
            modelAndView.addObject("order_price", order.getPrice());
            modelAndView.addObject("statuses", statusService.getAll());
            modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
            modelAndView.setViewName("manager/order/edit");
        } else {
            modelAndView.setViewName("redirect:/manager/orders");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/update_order", method = RequestMethod.POST)
    public ModelAndView updateOrder(@RequestParam long id,
                                    @RequestParam(value = "auth_user") long managerId,
                                    @RequestParam String number,
                                    @RequestParam(value = "status") long statusId,
                                    @RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String phone,
                                    @RequestParam(value = "shipping-address") String shippingAddress,
                                    @RequestParam(value = "shipping-details") String shippingDetails,
                                    @RequestParam String description,
                                    ModelAndView modelAndView) {
        Order order = orderService.get(id);

        if (order.getManager() == null || order.getManager() == userService.getAuthenticatedUser()) {

            User client = order.getClient();
            client.setName(name);
            client.setEmail(email);
            client.setPhone(phone);

            Status status = statusService.get(statusId);
            User manager = null;
            if (!status.equals(statusService.getDefault())) {
                manager = userService.get(managerId);
            }

            order.initialize(number, new Date(), shippingAddress, shippingDetails, description, status, client, manager);
            orderService.update(order);
        }

        modelAndView.setViewName("redirect:/manager/view_order_" + id);
        return modelAndView;
    }

    @RequestMapping(value = "/update_order", method = RequestMethod.GET)
    public void updateOrder() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_order\" is not supported!");
    }
}
