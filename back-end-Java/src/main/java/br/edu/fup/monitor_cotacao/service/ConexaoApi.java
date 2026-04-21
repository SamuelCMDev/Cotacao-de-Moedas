package br.edu.fup.monitor_cotacao.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class ConexaoApi {
    @Autowired
    public RestClient restClient;   

   public String buscarCotacaoAtual( String moeda) throws IOException{
        String caminho = "https://economia.awesomeapi.com.br/json/last/"+moeda+"-BRL";
        
        String conteudo = restClient.get().uri(caminho).retrieve().body(String.class);
        return conteudo;
   }
   public String buscarHistoricoCotacao(String moeda) throws IOException{
      String caminhoHistorico = "https://economia.awesomeapi.com.br/json/daily/"+moeda+"-BRL/7";
        String conteudoHistorico = restClient.get().uri(caminhoHistorico).retrieve().body(String.class);
       return conteudoHistorico;
   }

      
}


