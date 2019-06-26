package GerenciadorDeAcademiWeb.Models;

import java.time.LocalDate;
import java.util.List;

import GerenciadorDeAcademiWeb.Enums.SexoEnum;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Gabriel
 */

public class Aluno {

    private UUID id;

    private String nome;
    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate dataNascimento;

    private LocalDate ultimaAvaliacao;

    private SexoEnum sexo;

    private String email;

    private String cpf;

    private boolean ativo;

    private List<Avaliacao> historicoDeAvaliacao;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        if (id == null) {
            throw new NullPointerException();
        } 
        this.id = id;
    }

    public String getNome() {        
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome.trim().isEmpty()) {
            throw new NullPointerException();
        }

        nome = nome.trim();
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }
    
    public String getDataNascimentoString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String data = this.dataNascimento.format(formatter);
        return data;
    }

    public void setDataNascimento(String dataNascimento) {
        if (dataNascimento.trim().isEmpty()) {
            throw new NullPointerException();
        } 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
    }

    public LocalDate getUltimaAvaliacao() {
        return ultimaAvaliacao;
    }

    public void setUltimaAvaliacao(LocalDate ultimaAvaliacao) {
        if (ultimaAvaliacao == null) {
            throw new NullPointerException();
        } 
        this.ultimaAvaliacao = ultimaAvaliacao;               
    }

    public int getSexo() {
        return this.sexo.getInt();        
    }
    
    public void setSexoEnum(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public void setSexoEnum(int sexo) {
        switch (sexo) {
            case 1:
                this.sexo = SexoEnum.MASCULINO;
                break;
            case 2:
                this.sexo = SexoEnum.FEMININO;
                break;
        }
    }

    public void setSexoEnum(String sexo) {
        switch (sexo) {
            case "MASCULINO":
                this.sexo = SexoEnum.MASCULINO;
                break;
            case "FEMININO":
                this.sexo = SexoEnum.FEMININO;
                break;
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email.trim().isEmpty()) {
            throw new NullPointerException();
        } 
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        if (cpf.trim().isEmpty()) {
            throw new NullPointerException();
        } 
        this.cpf = cpf;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Avaliacao> getHistoricoDeAvaliacao() {
        return this.historicoDeAvaliacao;
    }

    public void setHistoricoDeAvaliacao(List<Avaliacao> historicoDeAvaliacao) {
        this.historicoDeAvaliacao = historicoDeAvaliacao;
    }

    public static Aluno obterAlunoDeUmaLista(List<Aluno> alunos){
        return alunos.stream().findFirst().get();
    }
    
    public String getDataNascimentotoString(){
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public  String getUltimaAvaliacaotoString(){
        return ultimaAvaliacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
