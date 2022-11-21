package com.example.repairagencyspring.controller;

import com.example.repairagencyspring.dto.OrderDTO;
import com.example.repairagencyspring.model.User;
import com.example.repairagencyspring.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("masterPage")
public class MasterController {
    private Logger logger = Logger.getLogger(MasterController.class);

    @Autowired
    OrderService orderService;

    @GetMapping
    public String showCheck(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((User) principal).getId();
        logger.info(userId);
        List<OrderDTO> orders = orderService.getAll();
        model.addAttribute("ordersForMaster", orders);
        return "master/masterPage";
    }

    @GetMapping("/orders")
    public String order(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long masterId = ((User) principal).getId();
        List<OrderDTO> orderDTOS = orderService.getAllOrdersByUserID(masterId);
        model.addAttribute("allMasterOrders", orderDTOS);
        return "master/orders";
    }

    @PostMapping("/orders/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        OrderDTO orderDTO = orderService.orderForEdit(id);
        model.addAttribute("win", true);
        model.addAttribute("orderForm", orderDTO);

        List<OrderDTO> orderDTOS = orderService.getAll();
        model.addAttribute("orders", orderDTOS);
        return "master/orders";
    }

    @PostMapping("/orders/update")
    public String update(@ModelAttribute("bookForm") OrderDTO orderForm, Model model) {
        orderService.edit(orderForm);
        List<OrderDTO> orderDTOS = orderService.getAll();
        model.addAttribute("orders", orderDTOS);
        return "master/orders";
    }
}
