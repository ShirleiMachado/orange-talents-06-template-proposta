package br.com.zupacademy.shirlei.proposta.cartao;


import br.com.zupacademy.shirlei.proposta.proposta.Proposta;
import br.com.zupacademy.shirlei.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociaCartaoProposta {

    private CartaoCliente cliente;
    private PropostaRepository propostaRepository;

    public AssociaCartaoProposta(CartaoCliente cliente,
                                 PropostaRepository propostaRepository) {
        this.cliente = cliente;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedDelayString = "20000")
    private void verificaNovosCartoes() {
      //  System.out.println("Verificando sistema de cartões");
        List<Proposta> propostasElegiveisSemCartao = propostaRepository.findByStatusAndCartaoNull(Proposta.StatusProposta.ELEGIVEL);
        if (propostasElegiveisSemCartao.isEmpty()) {
            return;
        }
        propostasElegiveisSemCartao.forEach(this::associaCartao);
    }

    private void associaCartao(Proposta proposta) {
        try {
            CartaoDTO cartaoResponse = cliente.pegaCartao(String.valueOf(proposta.getId()));
            proposta.associaCartao(cartaoResponse.toModel(proposta));
            propostaRepository.save(proposta);
        } catch (FeignException e) {
            System.out.println("Tentando novamente");
        }

    }
}
