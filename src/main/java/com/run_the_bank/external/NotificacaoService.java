package com.run_the_bank.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacaoService {

    private final RestTemplate restTemplate;

    public NotificacaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarNotificacao(String endpoint) {
        // Lógica para enviar notificação
        try {
            restTemplate.postForObject(endpoint, null, Void.class);
        } catch (Exception e) {
            // Lidar com falha na notificação
            e.printStackTrace();
        }
    }
}