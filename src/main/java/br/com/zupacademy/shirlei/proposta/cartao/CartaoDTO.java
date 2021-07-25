package br.com.zupacademy.shirlei.proposta.cartao;


import br.com.zupacademy.shirlei.proposta.proposta.Proposta;

import java.time.LocalDateTime;

public class CartaoDTO {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private String idProposta;

    public CartaoDTO(String id, LocalDateTime emitidoEm,
                     String titular, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(this.id, this.emitidoEm, this.titular, proposta);
    }

    @Override
    public String toString() {
        return "NovoCartaoResponse{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}
