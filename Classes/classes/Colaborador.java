package projeto.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class Colaborador {
    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private String matricula;
    private String cpf;
    private String nome;
    private Date datanascimento;
    private boolean situacao;

    public Colaborador(String matricula, String cpf, String nome, Date datanascimento) {
        this.matricula = matricula;
        this.cpf = cpf;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.situacao=false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCpf() {
        return cpf;
    }


            public static boolean validaCPF(String CPF) {
                if (CPF.equals("00000000000") ||
                        CPF.equals("11111111111") ||
                        CPF.equals("22222222222") || CPF.equals("33333333333") ||
                        CPF.equals("44444444444") || CPF.equals("55555555555") ||
                        CPF.equals("66666666666") || CPF.equals("77777777777") ||
                        CPF.equals("88888888888") || CPF.equals("99999999999") ||
                        (CPF.length() != 11))
                    return(false);

                char dig10, dig11;
                int sm, i, r, num, peso;

                try {
                    sm = 0;
                    peso = 10;
                    for (i=0; i<9; i++) {
                        num = (int)(CPF.charAt(i) - 48);
                        sm = sm + (num * peso);
                        peso = peso - 1;
                    }

                    r = 11 - (sm % 11);
                    if ((r == 10) || (r == 11))
                        dig10 = '0';
                    else dig10 = (char)(r + 48);
                    sm = 0;
                    peso = 11;
                    for(i=0; i<10; i++) {
                        num = (int)(CPF.charAt(i) - 48);
                        sm = sm + (num * peso);
                        peso = peso - 1;
                    }

                    r = 11 - (sm % 11);
                    if ((r == 10) || (r == 11))
                        dig11 = '0';
                    else dig11 = (char)(r + 48);


                    if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                        return(true);
                    else return(false);
                } catch (InputMismatchException erro) {
                    return(false);
                }

            }

        public void ativar(){
            this.situacao=true;
        }

        public void desativar(){
        this.situacao=false;
        }

    @Override
    public String toString() {
        return "Colaborador{" +
                "matrícula='" + this.matricula + '\'' +
                ", CPF='" + this.cpf + '\'' +
                ", Nome='" + this.nome + '\'' +
                ", Data de nascimento=" + formato.format(this.datanascimento) +
                ", Situacao=" + this.situacao +
                '}';
    }
}

