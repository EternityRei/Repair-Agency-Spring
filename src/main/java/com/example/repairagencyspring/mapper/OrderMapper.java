package com.example.repairagencyspring.mapper;

import com.example.repairagencyspring.dto.OrderDTO;
import com.example.repairagencyspring.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /*
    *     @Mapping(target = "password", ignore = false)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "password", ignore = false)
    User userDTOtoUser(UserDTO userDTO);

    List<UserDTO> toUserDTO(List<User> allUsers);*/

    @Mapping(target = "id", source = "id", ignore = false)
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(target = "id", ignore = false)
    Order orderDTOtoOrder(OrderDTO orderDTO);

    List<OrderDTO> toOrderDTO(List<Order> allOrders);
}
