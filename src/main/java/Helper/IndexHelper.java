package Helper;

import GerenciadorDeAcademiWeb.Models.Aluno;
import GerenciadorDeAcademiWeb.Models.Dados;
import java.util.List;
import java.util.ListIterator;

import GerenciadorDeAcademiWeb.Models.ResponseModels.ApiRetorno;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * IndexHelper
 */
public class IndexHelper {

    public Dados coletaDados(List<Aluno> alunos) {

        Dados dados = new Dados();

        int naoAvaliados = 0;
        int avaliados = 0;
        int mulheres = 0;
        int homens = 0;
        int inativados = 0;
        int ativados = 0;
        
        Iterator<Aluno> alu = alunos.listIterator();

        while (alu.hasNext()) {

            Aluno aluLoop = alu.next();
            if (aluLoop.getAtivo()) {
                ativados++;
            }
            if (!aluLoop.getAtivo()) {
                inativados++;
            }
            if (aluLoop.getUltimaAvaliacao() != null) {
                avaliados++;
            }

            if (aluLoop.getSexo() == 2) {
                mulheres++;
            }
            if (aluLoop.getSexo() == 1) {
                homens++;
            }

        }
        
        dados.setAtivos(ativados);
        dados.setInativados(inativados);
        dados.setPorcentagemNaoAvaliados(this.calculaPorcentagem(avaliados, alunos.size()));
        dados.setNumHomens(homens);
        dados.setNumMulherres(mulheres);
        return dados;
    }

    private Double calculaPorcentagem(int qtd, int total) {

        double porcentagem = (qtd * 100) / total;
        return porcentagem;
    }
}
