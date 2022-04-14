package com.service.impl;

import com.domain.OrderDetail;
import com.domain.Orders;
import com.repository.OrderDetailRepository;
import com.repository.OrderRepository;
import com.service.OrderService;
import com.service.ProductService;
import com.service.UserService;
import com.service.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserService userService;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userService = userService;
        this.productService = productService;
    }

    private OrderDto toDto(Orders obj) {
        if (obj == null) return null;
        OrderDto dto = new OrderDto();
        dto.setId(obj.getId());
        dto.setTotalPrice(obj.getTotal());
        dto.setTotalQuantity(obj.getTotalQuantity());
        dto.setCreatedUser(this.userService.findById(obj.getCreatedUser()));
//        dto.setOrderProduct(this.orderDetailRepository.findAllByOrderId(obj.getId())
//                .stream().map(orderDetail -> {
//                    CartProduct ct = new CartProduct();
//                    ct.setQuantity(orderDetail.getQuantity());
//                    ct.setProduct(this.productService.findById(orderDetail.getProductId()));
//                    return ct;
//                }).collect(Collectors.toList()));
        return dto;
    }

    private Orders toEntity(OrderDto dto) {
        if (dto == null) return null;
        Orders obj = new Orders();
        obj.setId(dto.getId());
        obj.setTotal(dto.getTotalPrice());
        obj.setTotalQuantity(dto.getTotalQuantity());
        obj.setCreatedUser(dto.getCreatedUser().getId());
        return obj;
    }

    @Override
    public OrderDto findById(Long id) {
        return toDto(this.orderRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto obj) {
        Orders order = this.orderRepository.save(toEntity(obj));
        if (order == null) return null;
        final Long orderId = order.getId();
        List<OrderDetail> orderDetails = obj.getOrderProduct().stream().map(orderProduct -> {

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(orderProduct.getProduct().getId());
            orderDetail.setQuantity(orderProduct.getQuantity());
            orderDetail.setPrice(orderProduct.getProduct().getPrice());

            return orderDetail;
        }).collect(Collectors.toList());

        this.orderDetailRepository.saveAll(orderDetails);
        return toDto(order);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        this.orderDetailRepository.deleteAllByOrderId(id);
        this.orderRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<OrderDto> findAll(Pageable page) {
        return this.orderRepository.findAll(page).map(this::toDto);
    }

    @Override
    public Page<OrderDto> findAllByUser(Long userId, Pageable page) {
        return this.orderRepository.findAllByCreatedUser(userId, page).map(this::toDto);
    }
}
