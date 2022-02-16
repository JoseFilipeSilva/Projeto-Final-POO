package projeto.classes;

import java.util.Date;

public  abstract class Contrato {
    private int id;
    private Date dataInicio;
    private Date dataEncerramento;
    private Colaborador colaborador;
    private boolean ativo;
    private float vencimento;



    public Contrato(int id, Date dataInicio, Colaborador colaborador) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataEncerramento = null;
        this.colaborador = colaborador;
        this.vencimento = 0;

    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Colaborador  getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public float getVencimento() {
        return vencimento;
    }

    public void setVencimento(float vencimento) {
        this.vencimento = vencimento;
    }

    public void encerrarContrato(){
        this.ativo = false;
        this.getColaborador().desativar();
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", dataInicio=" + dataInicio +
                ", dataEncerramento=" + dataEncerramento +
                ", colaborador=" + colaborador +
                ", ativo=" + ativo +
                ", vencimento=" + vencimento +
                '}';
    }
}
