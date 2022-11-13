package com.example.repairagencyspring.controller;

import com.example.repairagencyspring.dto.OrderByUserDTO;
import com.example.repairagencyspring.dto.OrderDTO;
import com.example.repairagencyspring.dto.ReviewDTO;
import com.example.repairagencyspring.model.User;
import com.example.repairagencyspring.service.OrderService;
import com.example.repairagencyspring.service.ReviewService;
import com.example.repairagencyspring.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("userPage")
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public String showCheck(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((User)principal).getId();
        logger.info(userId);
        List<OrderDTO> orders = orderService.getAllOrdersByUserID(userId);
        model.addAttribute("lastOrders", orders);
        return "user/userPage";
    }

    @GetMapping("/orders")
    public String order(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((User) principal).getId();
        List<OrderDTO> orders = orderService.getAllOrdersByUserID(userId);
        model.addAttribute("allOrders", orders);
        return "user/orders";
    }

    @PostMapping("/orders/makeAnOrder")
    public String makeOrder(@ModelAttribute("orderForm") OrderDTO orderForm, Model model){
        orderService.add(orderForm);
        List<OrderDTO> orderDTOS = orderService.getAll();
        model.addAttribute("uploadedOrders", orderDTOS);
        return "user/orders";
    }

    @PostMapping("/orders/cancel")
    public String returnBook(@RequestParam(name = "id") Long id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((User) principal).getId();
        orderService.cancelOrder(userId, id);

        List<OrderByUserDTO> orderByUserDTO = userService.orderByUserDTOS(userId);
        model.addAttribute("orders", orderByUserDTO);
        return "user/orders";
    }

    @PostMapping("/orders/pay")
    public String pay(@RequestParam(name = "id") Long id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((User) principal).getId();
        orderService.payOrder(userId, id);

        List<OrderByUserDTO> orderByUserDTO = userService.orderByUserDTOS(userId);
        model.addAttribute("ordersDTO", orderByUserDTO);
        return "user/userOrders";
    }

    @GetMapping("/reviews")
    public String review(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((User) principal).getId();
        List<ReviewDTO> reviews = reviewService.getAllReviewsByUserId(userId);
        model.addAttribute("allReviews", reviews);
        return "user/reviews";
    }

    @PostMapping("/reviews/leaveReview")
    public String leaveReview(@ModelAttribute("reviewForm") ReviewDTO reviewForm, Model model){
        reviewService.addReview(reviewForm);
        List<ReviewDTO> reviewDTOS = reviewService.getAll();
        model.addAttribute("reviewsDTOS", reviewDTOS);
        return "user/reviews";
    }
}
