package com.laidbackware.rabbitmq_spring_example;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
	private AmqpTemplate rabbitTemplate;

    @Autowired
    private Queue queue;


    @GetMapping(value = "/get")
    public String get(Model model) {
         // Receive a message from the "messages" queue
        String message = (String)rabbitTemplate.receiveAndConvert(queue.getName());
        if (message != null)
            return "Message: " + message;
        else
            return "queue empty!";
    }

    @PostMapping(value = "/publish")
    public String publish(Model model, Message message) {
        rabbitTemplate.convertAndSend(queue.getName(), message.getValue());
        return "published";
    }

}
