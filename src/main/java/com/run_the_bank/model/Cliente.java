package com.run_the_bank.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Table(name = "tb_cliente")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf_cnpj", unique = true) // Garante unicidade no banco de dados
    private String cpfCnpj; // Pode ser CPF ou CNPJ

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "senha")
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas;

    @Column(name = "status")
    private boolean status;

    public enum TipoCliente {
        PESSOA_FISICA,
        PESSOA_JURIDICA
    }

}
