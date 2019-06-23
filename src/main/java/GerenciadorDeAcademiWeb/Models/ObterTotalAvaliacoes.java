package GerenciadorDeAcademiWeb.Models;

import java.util.List;

import GerenciadorDeAcademiWeb.Enums.AutorEnum;

public class ObterTotalAvaliacoes{

    private String iD;

    private int idade;
    
    private List<PorcentagemDeGordura> porcentagemDeGordura;

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }   

    public List<PorcentagemDeGordura> getPorcentagemDeGordura() {
        return porcentagemDeGordura;
    }

    public void setPorcentagemDeGordura(List<PorcentagemDeGordura> porcentagemDeGordura) {
        this.porcentagemDeGordura = porcentagemDeGordura;
    }    
}