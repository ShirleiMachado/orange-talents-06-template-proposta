package br.com.zupacademy.shirlei.proposta.proposta;


import br.com.zupacademy.shirlei.proposta.analiseProposta.AnaliseCliente;
import br.com.zupacademy.shirlei.proposta.analiseProposta.AnaliseRequest;
import br.com.zupacademy.shirlei.proposta.analiseProposta.AnaliseResponse;
import br.com.zupacademy.shirlei.proposta.analiseProposta.StatusAnalise;
import br.com.zupacademy.shirlei.proposta.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;

@RestController
@RequestMapping("/proposta")
public class PropostaController {


    @Autowired
    private PropostaRepository propostaRepository;
    private AnaliseCliente cliente;


    public PropostaController(PropostaRepository propostaRepository, AnaliseCliente cliente) {
        this.propostaRepository = propostaRepository;
        this.cliente = cliente;
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaPropostas", allEntries = true)
    public ResponseEntity <?>cria(@RequestBody @Valid PropostaDTO request, UriComponentsBuilder uriComponentsBuilder){

        if (documentoJaExiste(request.getDocumento())) {
            ErrorResponse errorResponse = new ErrorResponse(Arrays.asList("Não é permitido mais de uma proposta para um mesmo solicitante"));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
        }

        Proposta proposta = request.converter();
        salvaProposta(proposta);

        AnaliseRequest pedidoAvaliacao = new AnaliseRequest(request.getDocumento(), request.getNome(), String.valueOf(proposta.getId()));
        AnaliseResponse analiseResponse = new AnaliseResponse();
        try {
            analiseResponse = cliente.verificaStatusSolicitante(pedidoAvaliacao);

        }catch (Throwable t){
            analiseResponse = new AnaliseResponse(pedidoAvaliacao.getDocumento(), pedidoAvaliacao.getNome(), StatusAnalise.COM_RESTRICAO, pedidoAvaliacao.getIdProposta());
        }
        proposta.setStatus(analiseResponse.getResultadoSolicitacao());
        salvaProposta(proposta);

        URI uri = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    private boolean documentoJaExiste(String documento){
        return propostaRepository.findByDocumento(documento).isPresent();
    }
    @Transactional
    private void salvaProposta(Proposta proposta) {
        propostaRepository.save(proposta);
    }
    }


