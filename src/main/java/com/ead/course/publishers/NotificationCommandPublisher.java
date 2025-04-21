package com.ead.course.publishers;

import com.ead.course.dtos.NotificationRecordCommandDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The Class NotificationCommandPublisher
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 12/02/2025
 */
@Component
public class NotificationCommandPublisher {

    final RabbitTemplate rabbitTemplate;

    public NotificationCommandPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${ead.broker.exchange.notificationCommandExchange}")
    private String notificationCommandExchange;

    @Value(value = "${ead.broker.key.notificationCommandKey}")
    private String notificationCommandKey;

    public void publishNotificationCommand(NotificationRecordCommandDto notificationRecordCommandDto) {
        rabbitTemplate.convertAndSend(notificationCommandExchange, notificationCommandKey, notificationRecordCommandDto);
    }
}
