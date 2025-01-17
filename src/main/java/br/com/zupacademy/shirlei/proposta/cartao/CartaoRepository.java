package br.com.zupacademy.shirlei.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8888", name = "cartao")
public interface CartaoRepository {

    @GetMapping("/api/cartoes")
    CartaoDTO consultaCartao(@RequestParam String idProposta);
}
