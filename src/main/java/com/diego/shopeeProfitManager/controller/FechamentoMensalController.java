package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.model.FechamentoMensal;
import com.diego.shopeeProfitManager.service.FechamentoMensalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fechamentos")
public class FechamentoMensalController {

    private final FechamentoMensalService fechamentoMensalService;

    public FechamentoMensalController(FechamentoMensalService fechamentoMensalService) {
        this.fechamentoMensalService = fechamentoMensalService;
    }

    @PostMapping("/{mes}/{ano}")
    public FechamentoMensal gerarFechamentoMensal(@PathVariable Integer mes, @PathVariable Integer ano) {

        return fechamentoMensalService.gerarFechamentoMensal(mes, ano);
    }

    @GetMapping("/{mes}/{ano}")
    public FechamentoMensal buscarFechamentoPorPeriodo(@PathVariable Integer mes, @PathVariable Integer ano) {

        return fechamentoMensalService.buscarFechamentoPorPeriodo(mes, ano);
    }

    @GetMapping("/historico")
    public List<FechamentoMensal> buscarHistoricoFechamentos() {

        return fechamentoMensalService.buscarHistoricoFechamentos();
    }

}
