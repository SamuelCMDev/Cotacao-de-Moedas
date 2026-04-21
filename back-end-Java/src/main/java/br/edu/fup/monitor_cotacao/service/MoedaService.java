package br.edu.fup.monitor_cotacao.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fup.monitor_cotacao.model.Moeda;
import br.edu.fup.monitor_cotacao.service.MoedaParser.HistoricoDTO;

@Service
public class MoedaService {
    @Autowired
    public ConexaoApi conexao;
    @Autowired
    public MoedaParser moedaParser;

    public Moeda obterMoeda(String name){
        try {
            String jsonBruto = conexao.buscarCotacaoAtual(name);
            Moeda moeda = new Moeda();
            return moedaParser.mapear(jsonBruto,moeda );
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
            
        }
    }
    public List<HistoricoDTO> obterHistorico(String name){
        try {
            String jsonBruto = conexao.buscarHistoricoCotacao(name);
            
            return moedaParser.mapearHistorico(jsonBruto);
            
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    

    }

}
