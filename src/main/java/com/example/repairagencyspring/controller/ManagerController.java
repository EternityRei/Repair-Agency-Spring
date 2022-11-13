package com.example.repairagencyspring.controller;

import com.example.repairagencyspring.dto.OrderDTO;
import com.example.repairagencyspring.dto.UserDTO;
import com.example.repairagencyspring.model.User;
import com.example.repairagencyspring.service.OrderService;
import com.example.repairagencyspring.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("managerPage")
public class ManagerController {
    private Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @GetMapping()
    public String showCheck() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long managerId = ((User)principal).getId();
        logger.info(managerId);
        return "manager/managerPage";
    }

    @GetMapping("/orders")
    public String order(Model model){
        List<OrderDTO> orderDTOS = orderService.getAll();
        model.addAttribute("allMasterOrders", orderDTOS);
        return "manager/orders";
    }

    @PostMapping("/orders/edit")
    public String editOrder(@RequestParam(name = "id") Long id, Model model) {
        OrderDTO orderDTO = orderService.orderForEdit(id);
        model.addAttribute("win", true);
        model.addAttribute("orderForm", orderDTO);

        List<OrderDTO> orderDTOS = orderService.getAll();
        model.addAttribute("orders", orderDTOS);
        return "manager/orders";
    }

    @PostMapping("/orders/update")
    public String updateOrder(@ModelAttribute("bookForm") OrderDTO orderForm, Model model) {
        orderService.edit(orderForm);
        List<OrderDTO> orderDTOS = orderService.getAll();
        model.addAttribute("orders", orderDTOS);
        return "manager/orders";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDTO> userDTOS = userService.getAllUsers();
        model.addAttribute("allUsersForManager", userDTOS);
        return "manager/users";
    }

    @PostMapping("/users/edit")
    public String editUser(@RequestParam(name = "id") Long id, Model model){
        UserDTO userDTO = userService.userForEdit(id);
        model.addAttribute("win", true);
        model.addAttribute("userForm", userDTO);

        List<UserDTO> userDTOS = userService.getAllUsers();
        model.addAttribute("users", userDTOS);
        return "manager/users";
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute("userForm") UserDTO userForm, Model model) {
        userService.edit(userForm);
        List<UserDTO> userDTOS = userService.getAllUsers();
        model.addAttribute("users", userDTOS);
        return "manager/users";
    }
}
