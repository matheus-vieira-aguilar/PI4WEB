package Helper;

import GerenciadorDeAcademiWeb.Enums.SexoEnum;
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

    private int[] qtdGenero = new int[2];
    private int avaliados = 0;
    private int inativados = 0;
    private int ativados = 0;
    private int qtdAlunos = 0;

    public Dados coletaDados(List<Aluno> alunos) {

        Iterator<Aluno> alu = alunos.listIterator();
        this.qtdAlunos = alunos.size();

        while (alu.hasNext()) {

            Aluno aluLoop = alu.next();

            this.countSexo(aluLoop.getSexo());
            this.countInativadosEAtivados(aluLoop.isAtivo());

            if (aluLoop.getUltimaAvaliacao() != null) {
                avaliados++;
            }

            System.out.println(aluLoop.getSexo());
        }

        Dados dados = this.fillObject();

        return dados;
    }

    private Double calculaPorcentagem(int qtd, int total) {

        double porcentagem = (qtd * 100) / total;
        return porcentagem;
    }

    private void countSexo(int sexo) {

        switch (sexo) {
            case 1:
                this.qtdGenero[0]++;
                break;
            case 2:
                this.qtdGenero[1]++;
                break;
        }
    }

    private void countInativadosEAtivados(boolean ativo) {

        if (ativo) {
            this.ativados++;
        }
        if (!ativo) {
            this.inativados++;
        }
    }

    private Dados fillObject() {

        Dados dados = new Dados();

        dados.setAtivos(this.ativados);
        dados.setInativados(this.inativados);
        dados.setPorcentagemNaoAvaliados(this.calculaPorcentagem(this.avaliados, this.qtdAlunos));
        dados.setNumHomens(this.qtdGenero[0]);
        dados.setNumMulherres(this.qtdGenero[1]);

        return dados;
    }
}
