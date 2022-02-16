package projeto.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContratoAssalariado extends Contrato {
    static SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
    private  float salarioMensal;
    private  float percInsalubridade;
    private  float perPericulosidade;

    public ContratoAssalariado(int id, Date dataInicio, Colaborador colaborador, float salarioMensal, float percInsalubridade, float perPericulosidade) {
        super(id, dataInicio,colaborador);
        this.salarioMensal = salarioMensal;
        this.percInsalubridade = percInsalubridade;
        this.perPericulosidade = perPericulosidade;

    }

    public float getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(float salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public float getPercInsalubridade() {
        return percInsalubridade;
    }

    public void setPercInsalubridade(float percInsalubridade) {
        this.percInsalubridade = percInsalubridade;
    }

    public float getPerPericulosidade() {
        return perPericulosidade;
    }

    public void setPerPericulosidade(float perPericulosidade) {
        this.perPericulosidade = perPericulosidade;
    }

    public  void calcVencimento(){
        float vencimento = this.salarioMensal+ this.percInsalubridade + this.perPericulosidade;
        super.setVencimento(vencimento);
    }

    public float calcSeguro(){
        float seguro = (this.getVencimento() * 0.02F);
        if(seguro > 150){
            seguro = 150;
        }
        if(seguro<25){
            seguro =25;
        }
        return seguro;
    }


    @Override
    public String toString() {
        return "\nContrato Assalariado:{" +
                "\nsalário Mensal=" + this.salarioMensal +
                ", \npercentual de insalubridade=" + percInsalubridade +
                ", \npercentual de periculosidade=" + perPericulosidade +
                "\n Id do contrato: "+this.getId()+
                "\n Data de Início: "+formato.format(this.getDataInicio())+
                "\n Data encerramento: " + formato.format(this.getDataEncerramento())+
                "\nColaborador: "+this.getColaborador().toString() +
                "\n Venvimento: "+this.getVencimento()+
                '}';
    }


}
