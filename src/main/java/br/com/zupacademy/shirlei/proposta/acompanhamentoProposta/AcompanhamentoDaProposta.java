package br.com.zupacademy.shirlei.proposta.acompanhamentoProposta;

import br.com.zupacademy.shirlei.proposta.cartao.CartaoDetalhe;
import br.com.zupacademy.shirlei.proposta.proposta.Proposta;

import java.math.BigDecimal;

public class AcompanhamentoDaProposta {

    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private Proposta.StatusProposta statusProposta;
    private CartaoDetalhe cartao;

    public AcompanhamentoDaProposta(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.statusProposta = proposta.getStatus();
        if(proposta.getCartao() != null) {
            this.cartao = new CartaoDetalhe(proposta.getCartao());
        }
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta.StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public CartaoDetalhe getCartao() {
        return cartao;
    }
}
