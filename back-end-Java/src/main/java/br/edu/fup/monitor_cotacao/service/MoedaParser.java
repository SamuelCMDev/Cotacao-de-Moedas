package br.edu.fup.monitor_cotacao.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import br.edu.fup.monitor_cotacao.model.Moeda;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class MoedaParser {
        public Moeda mapear(String conteudo, Moeda moeda) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(conteudo);
            
            if (root.iterator().hasNext()) {
                JsonNode dadosMoeda = root.iterator().next();
                
                moeda.setVariacao(dadosMoeda.path("varBid").asDouble());
                moeda.setValor(dadosMoeda.path("bid").asDouble());
                moeda.setMoeda(dadosMoeda.path("name").asText());
            }
            
            return moeda; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public record  HistoricoDTO(String data, Double valor) {
    }
    public List<HistoricoDTO> mapearHistorico(String conteudo) {
    ObjectMapper mapper = new ObjectMapper();
    List<HistoricoDTO> listaParaOGrafico = new ArrayList<>();
    
    try {
        JsonNode root = mapper.readTree(conteudo);
        if (root.isArray()) {
            for (JsonNode node : root) {
                Double valor = node.path("bid").asDouble();
                long timestamp = node.path("timestamp").asLong();
                String dataFormatada = converterTimestamp(timestamp);
                listaParaOGrafico.add(new HistoricoDTO(dataFormatada, valor));
            }
        }
        
        Collections.reverse(listaParaOGrafico);

        return listaParaOGrafico;
    } catch (Exception e) {
        e.printStackTrace();
        return Collections.emptyList();
        }
    }

    private String converterTimestamp(long timestamp) {
        return java.time.Instant.ofEpochSecond(timestamp)
                .atZone(java.time.ZoneId.systemDefault())
                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM"));
    }
}

