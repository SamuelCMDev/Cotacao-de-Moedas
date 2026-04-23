package br.edu.fup.monitor_cotacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public RestClient restClient() {
        // Cria um cliente padrão sem URL fixa, já que você passa a URL no método
        return RestClient.create();
    }
}