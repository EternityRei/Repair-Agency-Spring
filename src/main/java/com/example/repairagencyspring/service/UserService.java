package com.example.repairagencyspring.service;

import com.example.repairagencyspring.controller.MainController;
import com.example.repairagencyspring.dto.OrderByUserDTO;
import com.example.repairagencyspring.dto.OrderDTO;
import com.example.repairagencyspring.dto.UserDTO;
import com.example.repairagencyspring.model.Order;
import com.example.repairagencyspring.model.Review;
import com.example.repairagencyspring.model.Role;
import com.example.repairagencyspring.model.User;
import com.example.repairagencyspring.repository.OrderRepository;
import com.example.repairagencyspring.repository.ReviewRepository;
import com.example.repairagencyspring.repository.RoleRepository;
import com.example.repairagencyspring.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RoleRepository roleRepository;

/*    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByEmail(user.getEmail());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return true;
    }

    public List<UserDTO> getAllUsers() {
        return parsingUserInUserDTO(userRepository.getUserListByRolesName("ROLE_USER"));
    }


    public User getUserById(long personId) {
        return userRepository.findById(personId).orElseThrow(() -> new EntityNotFoundException("Person not found"));
    }


    public List<OrderDTO> orderByUserForManager(long id) {
        List<Order> orderList = orderRepository.findAllOrdersByUserId(id);
        Order order;
        List<OrderDTO> list = new ArrayList<>();
        for (Order ord : orderList) {
            order = orderRepository.getById(ord.getId());
            list.add(OrderDTO.builder().id(order.getId())
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
        logger.info(list);
        return list;
    }
    public List<OrderDTO> orderByUser(long id) {
        List<Order> orderList = orderRepository.findOrderByUserId(id);
        Order order;
        List<OrderDTO> list = new ArrayList<>();
        for (Order ord : orderList) {
            order = orderRepository.getById(ord.getId());
            list.add(OrderDTO.builder().id(order.getId())
                    .title(order.getTitle())
                    .description(order.getDescription())
                    .cost(order.getCost())
                    .paymentStatus(order.getPaymentStatus())
                    .workStatus(order.getWorkStatus())
                    .workerId(order.getWorkerId())
                    .timestamp(order.getTimestamp())
                    .build());
        }
        logger.info(list);
        return list;
    }

    public List<OrderByUserDTO> orderByUserDTOS(long id) {
        List<Order> orders = orderRepository.findAllOrdersByUserId(id);
        Order order;
        List<OrderByUserDTO> list = new ArrayList<>();
        for (Order ord : orders) {
            order = orderRepository.getById(ord.getId());
            list.add(OrderByUserDTO.builder().id(order.getId())
                    .title(order.getTitle())
                    .description(order.getDescription())
                    .cost(order.getCost())
                    .paymentStatus(order.getPaymentStatus())
                    .workStatus(order.getWorkStatus())
                    .build());

        }
        logger.info(list);
        return list;
    }

    public List<OrderByUserDTO> reviewByOrder(long id) {
        List<Review> reviewList = orderRepository.findAllReviewsById(id);
        Order order;
        List<OrderByUserDTO> list = new ArrayList<>();
        for (Review rev : reviewList) {
            order = orderRepository.getById(rev.getId());
            list.add(OrderByUserDTO.builder()
                    .id(order.getId())
                    .title(order.getTitle())
                    .description(order.getDescription())
                    .cost(order.getCost())
                    .paymentStatus(order.getPaymentStatus())
                    .workStatus(order.getWorkStatus())
                    .rating(rev.getRating())
                    .contextRating(rev.getContent())
                    .build());
        }
        logger.info(list);
        return list;
    }

    public UserDTO userForEdit(long id) {
        User user = userRepository.getById(id);
        return UserDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getUsername())
                .money(user.getMoney())
                .build();
    }

    public boolean edit(UserDTO userDTO) {
        User user = userRepository.getById(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setMoney(userDTO.getMoney());
        userRepository.save(user);
        return true;
    }


/*    public boolean passwordVerify(User user, String pass) {
        return bCryptPasswordEncoder.matches(pass, user.getPassword());
    }*/



    private List<UserDTO> parsingUserInUserDTO(List<User> list) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : list) {
            var userDTO = UserDTO
                    .builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getUsername())
                    .money(user.getMoney())
                    .build();
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

}
