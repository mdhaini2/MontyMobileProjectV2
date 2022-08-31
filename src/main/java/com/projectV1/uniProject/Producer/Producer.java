package com.projectV1.uniProject.Producer;

import com.projectV1.uniProject.Entities.StudentEnrollCourse;
import com.projectV1.uniProject.Entities.TransferStudentGrade;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class Producer {

    @Value("${grade.exchange.name}")
    private String exchange;

    @Value("${grade.routing.key}")
    private String routingKey;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendJsonMessage(TransferStudentGrade transferStudentGrade) {
        log.info(String.format("Json message sent -> %s", transferStudentGrade));
        amqpTemplate.convertAndSend(exchange, routingKey, transferStudentGrade);
    }




}