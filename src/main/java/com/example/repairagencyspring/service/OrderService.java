package com.example.repairagencyspring.service;

import com.example.repairagencyspring.dto.OrderDTO;
import com.example.repairagencyspring.model.Order;
import com.example.repairagencyspring.repository.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    private final Logger logger = Logger.getLogger(OrderService.class);


    public boolean deleteBook(long orderId) {
        orderRepository.deleteById(orderId);
        return true;
    }


    public List<OrderDTO> getAllOrdersByUserID(long userId) {
        List<Order> orderList = orderRepository.findAllOrdersByUserId(userId);
        return parsingOrderInOrderDTO(orderList);
    }

    public List<OrderDTO> findByTitle(String title) {
        List<OrderDTO> orderDTO = parsingOrderInOrderDTO(orderRepository.getAll());
        return orderDTO.stream()
                .filter(o -> o.getTitle().toUpperCase().contains(title.toUpperCase()))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAll(){
        List<Order> orders = orderRepository.getAll();
        return parsingOrderInOrderDTO(orders);
    }

    public OrderDTO orderForEdit(long id) {
        Order order = orderRepository.getById(id);
        return OrderDTO.builder()
                .id(order.getId())
                .title(order.getTitle())
                .description(order.getDescription())
                .cost(order.getCost())
                .paymentStatus(order.getPaymentStatus())
                .workStatus(order.getWorkStatus())
                .userId(order.getUserId())
                .workerId(order.getWorkerId())
                .timestamp(order.getTimestamp())
                .build();
    }


/*    public List<OrderDTO> sort(String operation) {
        return switch (operation) {
            case "sortDate" -> sortByDate();
            case "sortWorkStatus" -> sortByWorkStatus();
            case "sortPaymentStatus" -> sortByPaymentStatus();
            case "sortPrice" -> sortByPrice();
            default -> sortById();
        };
    }*/

    public boolean add(OrderDTO orderDTO) {
        Order order = Order.builder()
                .title(orderDTO.getTitle())
                .description(orderDTO.getDescription())
                .cost(orderDTO.getCost())
                .paymentStatus(orderDTO.getPaymentStatus())
                .workStatus(orderDTO.getWorkStatus())
                .userId(orderDTO.getUserId())
                .workerId(orderDTO.getWorkerId())
                .timestamp(orderDTO.getTimestamp())
                .build();
        orderRepository.save(order);
        return true;
    }


    public boolean edit(OrderDTO orderDTO) {
        Order order = orderRepository.getById(orderDTO.getId());
        order.setTitle(orderDTO.getTitle());
        order.setDescription(orderDTO.getDescription());
        order.setCost(orderDTO.getCost());
        order.setPaymentStatus(orderDTO.getPaymentStatus());
        order.setWorkStatus(orderDTO.getWorkStatus());
        order.setUserId(orderDTO.getUserId());
        order.setWorkerId(orderDTO.getWorkerId());
        order.setTimestamp(orderDTO.getTimestamp());
        orderRepository.save(order);
        return true;
    }


    private List<OrderDTO> sortByDate(Timestamp timestamp){
        List<OrderDTO> list = orderRepository.findAllOrdersByTimestampIs(timestamp);
        list.sort(new OrderDTO.DateComparator());
        return list;
    }

    private List<OrderDTO> sortByWorkStatus(String workStatus) {
        List<OrderDTO> list = orderRepository.findAllOrdersByWorkStatusIs(workStatus);
        list.sort(new OrderDTO.WorkStatusComparator());
        return list;
    }

    private List<OrderDTO> sortByPaymentStatus(String paymentStatus) {
        List<OrderDTO> list = orderRepository.findAllOrdersByPaymentStatusIs(paymentStatus);
        list.sort(new OrderDTO.PaymentStatusComparator());
        return list;
    }

    private List<OrderDTO> sortByPrice(float cost) {
        List<OrderDTO> list = orderRepository.findAllOrdersByCostIs(cost);
        list.sort(new OrderDTO.PriceComparator());
        return list;
    }

    private List<OrderDTO> sortById(long id){
        List<OrderDTO> list = orderRepository.findAllOrdersById(id);
        list.sort(new OrderDTO.IdComparator());
        return list;
    }


    private List<OrderDTO> parsingOrderInOrderDTO(List<Order> list) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : list) {
            orderDTOs.add(OrderDTO.builder()
                    .id(order.getId())
                    .title(order.getTitle())
                    .description(order.getDescription())
                    .cost(order.getCost())
                    .paymentStatus(order.getPaymentStatus())
                    .workStatus(order.getWorkStatus())
                    .userId(order.getUserId())
                    .workerId(order.getWorkerId())
                    .timestamp(order.getTimestamp())
                    .build());
        }
        return orderDTOs;
    }

    public boolean cancelOrder(long userId, long id) {
        Order order = orderRepository.findOrderByUserIdAndId(userId,id);
        orderRepository.deleteById(order.getId());
        return true;
    }

    public boolean payOrder(long userId, long id) {
        Order order = orderRepository.findOrderByUserIdAndId(userId, id);
        order.setPaymentStatus("paid");
        orderRepository.save(order);
        return true;
    }
}
