
package GerenciadorDeAcademiWeb.Models;

/**
 *
 * @author matheusvieira
 */
public class Dados {
    
    private int qtdNaoAvaliados;
    private double porcentagemNaoAvaliados;
    private int numMulherres;
    private int numHomens;
    private int inativados;
    private int ativos;
    
    public int getQtdNaoAvaliados() {
        return qtdNaoAvaliados;
    }

    public void setQtdNaoAvaliados(int qtdNaoAvaliados) {
        this.qtdNaoAvaliados = qtdNaoAvaliados;
    }

    public double getPorcentagemNaoAvaliados() {
        return porcentagemNaoAvaliados;
    }

    public void setPorcentagemNaoAvaliados(double porcentagemNaoAvaliados) {
        this.porcentagemNaoAvaliados = porcentagemNaoAvaliados;
    }


    public int getNumMulherres() {
        return numMulherres;
    }

    public void setNumMulherres(int numMulherres) {
        this.numMulherres = numMulherres;
    }

    public int getNumHomens() {
        return numHomens;
    }

    public void setNumHomens(int numHomens) {
        this.numHomens = numHomens;
    }

    public int getInativados() {
        return inativados;
    }

    public void setInativados(int inativados) {
        this.inativados = inativados;
    }

    public int getAtivos() {
        return ativos;
    }

    public void setAtivos(int ativos) {
        this.ativos = ativos;
    }
    
    
}
