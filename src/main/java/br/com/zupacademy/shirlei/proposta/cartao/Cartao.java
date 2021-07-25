package br.com.zupacademy.shirlei.proposta.cartao;

import br.com.zupacademy.shirlei.proposta.proposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String numeroCartao;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataEmissao;

    @NotBlank
    @Column(nullable = false)
    private String nomeTitular;

    @NotNull
    @OneToOne
    private Proposta proposta;


    @Deprecated
    public Cartao() {
    }

    public Cartao(String numeroCartao,
                  LocalDateTime dataEmissao,
                  String nomeTitular, Proposta proposta) {
        this.numeroCartao = numeroCartao;
        this.dataEmissao = dataEmissao;
        this.nomeTitular = nomeTitular;
        this.proposta = proposta;
    }
}
