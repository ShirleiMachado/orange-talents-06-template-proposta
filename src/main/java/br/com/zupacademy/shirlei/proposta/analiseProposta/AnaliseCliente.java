package br.com.zupacademy.shirlei.proposta.analiseProposta;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://localhost:9999", name = "analise")//, fallback = AnaliseClienteFallback.class)
public interface AnaliseCliente {

    @RequestMapping(value = "/api/solicitacao", method = RequestMethod.GET, consumes = "application/json")
    AnaliseResponse verificaStatusSolicitante(AnaliseRequest pedidoAvaliacao);
}


