package br.edu.fup.monitor_cotacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fup.monitor_cotacao.model.Moeda;
import br.edu.fup.monitor_cotacao.service.MoedaParser.HistoricoDTO;
import br.edu.fup.monitor_cotacao.service.MoedaService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MoedaController {
    @Autowired
    public MoedaService moedaService;
    @GetMapping("/cotacao/{moeda}")
    public Moeda buscar(@PathVariable String moeda){
        return moedaService.obterMoeda(moeda);
    }
    @GetMapping("/historico/{moeda}")
    public List<HistoricoDTO> buscarHistorico(@PathVariable String moeda){
        return moedaService.obterHistorico(moeda);
    }
        
    
    }
     

