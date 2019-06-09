package GerenciadorDeAcademiWeb.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Gabriel
 */
public class Avaliacao {

    private UUID iD;

    private UUID idAluno;

    private double massa;
    
    private double estatura;

    private double imc;

    private double pccg;

    private double pesoIdeal;
    
    private double pesoEmExcesso;
    
    private double massaMagra;
    
    private double massaDeGordura;

    private AvaliacaoDobras dobrasAvaliacao;

    private AvaliacaoPerimetros perimetrosAvaliacao;

    private List<PorcentagemDeGordura> porcentagemDeGordura;

    public UUID getID() {
        return this.iD;
    }

    public void setID(UUID id) {        
        this.iD = id;

        if(dobrasAvaliacao != null){
            dobrasAvaliacao.setIdAvaliacao(id);
        }

        if(perimetrosAvaliacao != null){
            perimetrosAvaliacao.setIdAvaliacao(id);
        }

        if(porcentagemDeGordura == null){
            return;
        }

        List<PorcentagemDeGordura> porcentagemDeGorduras = new ArrayList<>();
        for (PorcentagemDeGordura item : porcentagemDeGordura) {
            item.setIdAvaliacao(id);
            porcentagemDeGorduras.add(item);
        }      
        
        porcentagemDeGordura = porcentagemDeGorduras;
    }

    public UUID getIdAluno() {
        return this.idAluno;
    }

    public void setIdAluno(UUID idAluno) {
        this.idAluno = idAluno;
    }

    public double getMassa() {
        return this.massa;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public double getEstatura() {
        return this.estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getImc() {
        return this.imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getPccg() {
        return this.pccg;
    }

    public void setPccg(double pccg) {
        this.pccg = pccg;
    }

    public double getPesoIdeal() {
        return this.pesoIdeal;
    }

    public void setPesoIdeal(double pesoIdeal) {
        this.pesoIdeal = pesoIdeal;
    }

    public double getPesoEmExcesso() {
        return this.pesoEmExcesso;
    }

    public void setPesoEmExcesso(double pesoEmExcesso) {
        this.pesoEmExcesso = pesoEmExcesso;
    }

    public double getMassaMagra() {
        return this.massaMagra;
    }

    public void setMassaMagra(double massaMagra) {
        this.massaMagra = massaMagra;
    }

    public double getMassaDeGordura() {
        return this.massaDeGordura;
    }

    public void setMassaDeGordura(double massaDeGordura) {
        this.massaDeGordura = massaDeGordura;
    }

    public List<PorcentagemDeGordura> getPorcentagemDeGordura() {
        return this.porcentagemDeGordura;
    }

    public void setPorcentagemDeGordura(List<PorcentagemDeGordura> porcentagemDeGordura) {
        this.porcentagemDeGordura = porcentagemDeGordura;
    }

    public AvaliacaoDobras getDobrasAvaliacao() {
        return this.dobrasAvaliacao;
    }

    public void setDobrasAvaliacao(AvaliacaoDobras dobrasAvaliacao) {
        this.dobrasAvaliacao = dobrasAvaliacao;
    }

    public AvaliacaoPerimetros getPerimetrosAvaliacao() {
        return this.perimetrosAvaliacao;
    }

    public void setPerimetrosAvaliacao(AvaliacaoPerimetros perimetrosAvaliacao) {
        this.perimetrosAvaliacao = perimetrosAvaliacao;
    }
}