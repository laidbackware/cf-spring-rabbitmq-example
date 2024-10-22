package com.laidbackware.rabbitmq_spring_example;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeControllerUI {

    @Autowired
	private AmqpTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute(new Message());
        return "index";
    }

    @RequestMapping(value = "/publish-ui", method=RequestMethod.POST)
    public String publishUI(Model model, Message message) {
        // Send a message to the "messages" queue
        rabbitTemplate.convertAndSend(queue.getName(), message.getValue());
        model.addAttribute("published", true);
        return index(model);
    }

    @RequestMapping(value = "/get-ui", method=RequestMethod.POST)
    public String getUI(Model model) {
        // Receive a message from the "messages" queue
        String message = (String)rabbitTemplate.receiveAndConvert(queue.getName());
        if (message != null)
            model.addAttribute("got", message);
        else
            model.addAttribute("got_queue_empty", true);

        return index(model);
    }

}
