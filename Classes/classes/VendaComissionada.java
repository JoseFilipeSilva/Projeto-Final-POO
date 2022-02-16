package projeto.classes;

public class VendaComissionada {
    private int id;
    private int mes;
    private int ano;
    private float valor;
    private ContratoComissionado contratrComissionado;

    public VendaComissionada(int id, int mes, int ano, float valor, ContratoComissionado contratrComissionado) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.contratrComissionado = contratrComissionado;

    }

    public int getId() {
        return id;
    }


    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Contrato getContratrComissionado() {
        return contratrComissionado;
    }

    public void setContratrComissionado(ContratoComissionado contratrComissionado) {
        this.contratrComissionado = contratrComissionado;
    }

}
