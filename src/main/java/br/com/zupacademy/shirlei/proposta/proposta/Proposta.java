package br.com.zupacademy.shirlei.proposta.proposta;

import br.com.zupacademy.shirlei.proposta.cartao.Cartao;
import br.com.zupacademy.shirlei.proposta.validacao.CpfCnpj;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;


@Entity
public class Proposta {

    public enum StatusProposta {
        ELEGIVEL, NAO_ELEGIVEL
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CpfCnpj
    @NotBlank
    private String documento;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @PositiveOrZero
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusProposta status;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Proposta(){}

    public Proposta(
            @NotBlank String documento,
            @Email @NotBlank String email,
            @NotBlank String nome,
            @NotBlank String endereco,
            @NotNull @PositiveOrZero BigDecimal salario
    ) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {

        return id;
    }

    public String getDocumento() {

        return documento;
    }

    public String getNome() {

        return nome;
    }

    public void setStatus(StatusProposta status){

        this.status = status;
    }

    public void associaCartao(Cartao cartao) {
        Assert.isTrue(this.aprovada(), "Não se pode associar um cartão a uma proposta não aprovada.");
        Assert.isTrue(this.cartao == null, "Esta proposta já possui um cartão associado a ela");
        this.cartao = cartao;
    }

    public boolean aprovada() {
        return this.status.equals(StatusProposta.ELEGIVEL);
    }
}
