package projeto.classes;

import java.util.Date;

public class ContratoComissionado extends Contrato {
    private float percComissao;
    private float ajudaCusto;

    public ContratoComissionado(int id, Date dataInicio, Colaborador colaborador, float percComissao, float ajudaCusto) {
        super(id, dataInicio, colaborador);
        this.percComissao = percComissao;
        this.ajudaCusto = ajudaCusto;
    }

    public float getPercComissao() {
        return percComissao;
    }

    public void setPercComissao(float percComissao) {
        this.percComissao = percComissao;
    }

    public float getAjudaCusto() {
        return ajudaCusto;
    }

    public void setAjudaCusto(float ajudaCusto) {
        this.ajudaCusto = ajudaCusto;
    }

    public float calcVencimento(float vlFaturam){
        return (vlFaturam * percComissao/100) + ajudaCusto;
    }

    public float calcSeguro(){
        float seguro=(getAjudaCusto()*0.005F) + 0.001F*(getVencimento() - this.ajudaCusto);

        if(seguro<25){
            seguro =25;
        }

        return seguro;
    }


    @Override
    public String toString() {
        return "ContratoComissionado{" +
                "percComissao=" + this.percComissao +
                ", ajudaCusto=" + this.ajudaCusto +
                '}';
    }

}
