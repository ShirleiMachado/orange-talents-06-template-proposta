package br.com.zupacademy.shirlei.proposta.analiseProposta;

import org.springframework.stereotype.Component;

@Component
    public class AnaliseClienteFallback implements AnaliseCliente {

    @Override
    public AnaliseResponse verificaStatusSolicitante(AnaliseRequest pedidoAvaliacao) {
        return new AnaliseResponse(pedidoAvaliacao.getDocumento(), pedidoAvaliacao.getNome(), StatusAnalise.COM_RESTRICAO, pedidoAvaliacao.getIdProposta());
    }
}

