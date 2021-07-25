package br.com.zupacademy.shirlei.proposta.proposta;

import br.com.zupacademy.shirlei.proposta.validacao.CpfCnpj;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaDTO {

    @CpfCnpj
    @NotBlank
    private String documento;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull @PositiveOrZero
    private BigDecimal salario;

    public PropostaDTO(@NotBlank String documento,
                       @Email @NotBlank String email,
                       @NotBlank String nome,
                       @NotBlank String endereco,
                       @NotNull @PositiveOrZero BigDecimal salario){
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

//    public PropostaDTO(Proposta proposta) {
//        this.documento = proposta.getDocumento();
//        this.email = email;
//        this.nome = proposta.getNome();
//        this.endereco = endereco;
//        this.salario = salario;
//    }

    public Proposta converter() {
        return new Proposta(
                this.documento,
                this.email,
                this.nome,
                this.endereco,
                this.salario);
    }

    public String getDocumento() {

        return documento;
    }

    public String getNome() {

        return nome;
    }


}
