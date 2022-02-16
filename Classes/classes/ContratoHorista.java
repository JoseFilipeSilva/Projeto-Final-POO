package projeto.classes;

import java.util.Date;

public class ContratoHorista extends Contrato {
    private int horasMes;
    private float valorHora;

    public ContratoHorista(int id, Date dataInicio, Colaborador colaborador, int horasMes, float valorHora) {
        super(id, dataInicio, colaborador);
        this.horasMes = horasMes;
        this.valorHora = valorHora;
    }

    public int getHorasMes() {
        return horasMes;
    }

    public void setHorasMes(int horasMes) {
        this.horasMes = horasMes;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public void calcVencimento(){
        float horista=valorHora*horasMes;
        super.setVencimento(horista);
    }

    public float calcSeguro(){
        float seguro=0;

        if(getVencimento()<=5000){
            seguro=getVencimento()*0.02F;
        }
        if(getVencimento()>5000){
            seguro=getVencimento()*0.025F;
        }

        return seguro;
    }

    @Override
    public String toString() {
        return "ContratoHorista{" +
                "horasMes=" + horasMes +
                ", valorHora=" + valorHora +
                '}';
    }
}
