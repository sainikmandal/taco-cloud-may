package com.sainik.tacocloudmay.controllers;

import com.sainik.tacocloudmay.models.TacoOrder;
import com.sainik.tacocloudmay.repository.TacoOrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final TacoOrderRepository tacoOrderRepository;

    public OrderController(TacoOrderRepository tacoOrderRepository) {
        this.tacoOrderRepository = tacoOrderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setPlacedAt(new Date());
        tacoOrderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
