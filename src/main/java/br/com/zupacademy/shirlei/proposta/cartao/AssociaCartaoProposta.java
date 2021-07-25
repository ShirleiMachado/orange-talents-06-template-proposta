package br.com.zupacademy.shirlei.proposta.cartao;


import br.com.zupacademy.shirlei.proposta.proposta.Proposta;
import br.com.zupacademy.shirlei.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociaCartaoProposta {

    private CartaoRepository cliente;
    private PropostaRepository propostaRepository;

    public AssociaCartaoProposta(CartaoRepository cliente,
                                 PropostaRepository propostaRepository) {
        this.cliente = cliente;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedDelayString = "20000")
    private void verificaNovosCartoes() {
        //System.out.println("Verificando cartões");
        List<Proposta> propostasElegiveisSemCartao = propostaRepository.findByStatusAndCartaoNull(Proposta.StatusProposta.ELEGIVEL);
        if (propostasElegiveisSemCartao.isEmpty()) {
            return;
        }
        propostasElegiveisSemCartao.forEach(this::associaCartao);
    }

    private void associaCartao(Proposta proposta) {
        try {
            CartaoDTO cartaoDTO = cliente.consultaCartao(String.valueOf(proposta.getId()));
            proposta.associaCartao(cartaoDTO.converter(proposta));
            propostaRepository.save(proposta);
        } catch (FeignException e) {
            System.out.println("Tentando novamente");
        }

    }
}
