package com.example.credit.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@AllArgsConstructor
public class NotifyKafkaAspect {
    private static final Logger LOGGER = LogManager.getLogger(NotifyKafkaAspect.class);

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void afterGetRequestApi(JoinPoint joinPoint) {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("consultas", objectMapper.writeValueAsString(extractRequestContent()));

            future.whenComplete((stringStringSendResult, throwable) -> {
                if (throwable != null)
                    LOGGER.error(throwable.getMessage());
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private Map<String, Object> extractRequestContent() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();

        Map<String, Object> content = new HashMap<>();
        content.put("method", request.getMethod());
        content.put("uri", request.getRequestURI());
        return content;
    }
}
