package ProjetoContrato;

import java.text.SimpleDateFormat;
import java.util.*;
import projeto.classes.*;

public class AppProjeto {
    static int ID =1;
    static Scanner ler= new Scanner(System.in);
    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    static List<Colaborador> colaboradores=new ArrayList<>();
    static List<Contrato> contratos = new ArrayList<>();
    static List<VendaComissionada> vendas=new ArrayList<>();

    public static void main(String[] args) {
        while(true) {
            System.out.println("---**Seletor de opções**---");
            System.out.println("1 - inserir colaborador ");
            System.out.println("2 - Registrar contrato");
            System.out.println("3 - Consultar contrato");
            System.out.println("4 - Encerrar contrato");
            System.out.println("5 - Listar colaboradores ativos");
            System.out.println("6 - Consultar contratos do colaborador");
            System.out.println("7 - Lançar de venda comissionada");
            System.out.println("8 - Emitir folha de pagamento");
            System.out.println("0 - cancelar operação");

            int opcao = 19;
            try {
                System.out.println("Digite a opção escolhida");
                opcao = ler.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Ocorreu um erro na digitação");
                System.out.println("Tente de novo");
                ler.nextLine();

            } catch (Exception e) {
                System.out.println("Ocorreu um erro!");
                System.out.println("Tente de novo! ");
                ler.nextLine();
            }

            switch (opcao) {
                case 1:
                    insereColaborador();
                    break;
                case 2:
                    registraContrato();
                    break;
                case 3:
                    consultaContrato();
                    break;
                case 4:
                    encerraContrato();
                    break;
                case 5:
                    listaColaboradores();
                    break;
                case 6:
                    contratosColaborador();
                    break;
                case 7:
                    vendaComissao();
                    break;
                case 8:
                    emitirFolha();
                    break;
                case 0:
                    System.out.println("Processo encerrado");
                    return;
                default:
                    System.out.println("Opção incorreta");
            }
        }
    }
    public static Contrato pesquisaContrato(int id){
        for(Contrato contrato:contratos){
            if(contrato.getId()==id){
                return contrato;
            }
        }
        return null;
    }
    public static Colaborador pesquisaColaborador(String matricula){
        for(Colaborador colaborador: colaboradores){
            if(matricula.equals(colaborador.getMatricula())||matricula.equals(colaborador.getCpf())){
                return colaborador;
            }
        }
        return null;
    }
    public static boolean verificaMatricula(String matricula){

        if(matricula==null) {
            return false;

        }for(char letra:matricula.toCharArray()){
            if(letra<'0'||letra>'9'){
                return false;
            }
        }
        return true;
    }

    public static void insereColaborador(){
        ler.nextLine();
        while(true) {
            System.out.println("Digite a matrícula do colaborador/[só números são aceitos]");
            System.out.println("Digite 'sair' para retornar");
            String matricula = ler.nextLine();

            if(matricula.equals("sair") || matricula.equals("Sair")||matricula.equals("SAIR")){
                System.out.println("Processo finalizado");
                return;
            }

            boolean statusMatricula=verificaMatricula(matricula);

            if(!statusMatricula){
                System.out.println("A matrícula contém letras ou não foi digitada");
                continue;
            }

            Colaborador colaboradorEncontrado=pesquisaColaborador(matricula);

            if(colaboradorEncontrado!=null){
                System.out.println("A matrícula já está sendo usada");
                continue;
            }

            System.out.println("Qual o nome do colaborador");
            String nome= ler.nextLine();

                 Date dataformatada;
            while(true) {
                System.out.println("Digite a data de nascimento ex.:[28/02/2003]");
                String data=ler.next();

                try{
                    dataformatada=formato.parse(data);

                }catch (Exception e){
                    System.out.println("Erro no formato da data, tente novamente");
                    continue;
                }
                try {
                    Calendar dataNasc = Calendar.getInstance();
                    dataNasc.setTime(dataformatada);
                    dataNasc.add(Calendar.YEAR, 18);
                    Calendar dataAtual = Calendar.getInstance();

                    if (dataNasc.after(dataAtual)) {
                        System.out.println("O colaborador tem menos de 18 anos");
                        System.out.println("Operação cancelada");
                        continue;
                    }

                }catch (Exception e){
                    System.out.println("Erro ao formatar a data, tente de novo");
                    continue;

                }
            break;
            }
            String cpf;
            ler.nextLine();
            while (true){
                System.out.println("Digite o cpf do colaborador [só números]");
                 cpf=ler.nextLine();

                 Colaborador colaborador = pesquisaColaborador(cpf);

                 if(colaborador!=null){
                     System.out.println("O cpf já foi usado");
                     continue;
                 }

               boolean situacaoCpf = Colaborador.validaCPF(cpf);

               if(!situacaoCpf){
                   System.out.println("O cpf digitado não é válido, tente de novo [somente números]");
                   continue;
               }

               break;
            }

            Colaborador colaborador = new Colaborador(matricula,cpf,nome,dataformatada);
            colaboradores.add(colaborador);
            System.out.println("Colaborador Adicionado");
        }
    }
    public static int sequenciador(){
        return ID++;
    }

    public static void registraContrato(){
        ler.nextLine();
        while(true) {

            System.out.println("Digite a matrícula do colaborador");
            System.out.println("Digite 'sair' para finalizar o processo");
            String matricula = ler.nextLine();


            if(matricula.equals("sair")||matricula.equals("SAIR")||matricula.equals("Sair")){
                System.out.println("Processo finalizado");
                return;
            }

            Colaborador colaboradorEncontrado = pesquisaColaborador(matricula);

            if (colaboradorEncontrado == null) {
                System.out.println("A matrícula encontrada não pertence a nenhum dos colaboradores cadastrados");
                continue;
            }
            if(colaboradorEncontrado.isSituacao()){
                System.out.println("O contrato deve possuir um colaborador com a situação 'desativada'");
                continue;
            }
            int id = sequenciador();
            Date dataformatada;
            while(true) {
                System.out.println("Digite a data do início do contrato ex.:[28/02/2003]");
                String data = ler.next();


                try {
                    dataformatada = formato.parse(data);
                } catch (Exception e) {
                    System.out.println("Erro no formato da data, tente novamente");
                    continue;
                }

                if (dataformatada.before(new Date())) {
                    System.out.println("A data não pode ser anterior à data atual");
                    continue;
                }

                break;
            }
            while(true) {
                System.out.println("Digite o tipo do contrato");
                System.out.println("1 - Assalariado");
                System.out.println("2 - Comissionado");
                System.out.println("3 - Horista");
                System.out.println("0 - cancelar operação");
                try{
                    int opcao=0;
                 opcao= ler.nextInt();

                switch (opcao){
                    case 1:
                        System.out.println("Qual o valor do salário mensal");
                        float salarioMensal= ler.nextFloat();

                        if(salarioMensal<0){
                            System.out.println("Só são permitidos valores acima de 0");
                            continue;
                        }
                        System.out.println("Qual o percentual de insalubridade");
                        float percInsalubridade=ler.nextFloat();

                        if(percInsalubridade<0){
                            System.out.println("Só são permitidos valores acima de 0");
                            continue;
                        }

                        System.out.println("Qual o percentual de periculosidade");
                        float percPericulosidade=ler.nextFloat();

                        if(percPericulosidade<0){
                            System.out.println("Só são permitidos valores acima de zero");
                            continue;
                        }


                       ContratoAssalariado contrato = new ContratoAssalariado(id,dataformatada,colaboradorEncontrado,salarioMensal,percInsalubridade,percPericulosidade);

                       contratos.add(contrato);
                       contrato.getColaborador().ativar();
                       boolean ativado = true;

                       contrato.setAtivo(true);
                        System.out.println("---**Contrato Registrado**---");
                        System.out.println("id do contrato: " +  contrato.getId());
                        System.out.println("Data de início do contrato: " + formato.format(contrato.getDataInicio() ));
                        System.out.println("Colaborador: " + contrato.getColaborador().toString());
                        System.out.println("salário mensal: " +salarioMensal);
                        System.out.println("Percentual de insalubridade: " + percInsalubridade);
                        System.out.println("Percentual de Periculosidade: " + percPericulosidade);

                        return;
                    case 2:

                        System.out.println("Digite o percetual de comissão");
                        float percComissao=ler.nextFloat();

                        if(percComissao<0){
                        System.out.println("Só são permitidos valores acima de 0");
                        continue;
                        }

                        System.out.println("Digite o valor da ajuda de custo");
                        float ajudaCusto=ler.nextFloat();

                        if(ajudaCusto<0){
                            System.out.println("Só são permitidos valores acima de 0");
                            continue;
                        }

                        ContratoComissionado contrato1 = new ContratoComissionado(id,dataformatada,colaboradorEncontrado,percComissao,ajudaCusto);

                        contratos.add(contrato1);

                        //ativando o colaborador e o contrato
                        contrato1.getColaborador().ativar();
                        ativado=true;
                        contrato1.setAtivo(ativado);

                        System.out.println("---**Contrato Registrado**---");
                        System.out.println("id do contrato: " +  contrato1.getId());
                        System.out.println("Data de início do contrato: " + formato.format(contrato1.getDataInicio() ));
                        System.out.println("Colaborador: " + contrato1.getColaborador().toString());
                        System.out.println("Percentual de comissão: " + contrato1.getPercComissao());
                        System.out.println("Ajuda de custo: " + contrato1.getAjudaCusto());
                        return;
                    case 3:

                        System.out.println("Digite a quantidade de horas trabalhadas");
                        int horasMes=ler.nextInt();

                        if(horasMes<0){
                            System.out.println("O valor deve ser maior que zero");
                            continue;
                        }

                        System.out.println("Digite o valor da hora de trabalho");
                        float valorHora=ler.nextFloat();

                        if(valorHora<0){
                            System.out.println("O valor deve ser maior que zero");
                            continue;
                        }

                        ContratoHorista contrato2=new ContratoHorista(id,dataformatada,colaboradorEncontrado,horasMes,valorHora);
                        contratos.add(contrato2);

                        //ativando o colaborador e o contrato
                        contrato2.getColaborador().ativar();
                        ativado = true;
                        contrato2.setAtivo(ativado);

                        System.out.println("---**Contrato Registrado**---");
                        System.out.println("id do contrato: " +  contrato2.getId());
                        System.out.println("Data de início do contrato: " + formato.format(contrato2.getDataInicio() ));
                        System.out.println("Colaborador: " + contrato2.getColaborador().toString());
                        System.out.println("Horas trabalhadas no mês: " + contrato2.getHorasMes());
                        System.out.println("Valor da hora trabalhada: " + contrato2.getValorHora());
                        return;

                    case 0:
                        System.out.println("Operação cancelada");
                        return;

                    default:
                        System.out.println("Opção incorreta");

                }
            }catch(InputMismatchException e){
                    System.out.println("Ocorreu um erro na digitação");
                    ler.nextLine();
                }
                catch (Exception e){
                    System.out.println("Ocorreu este erro: "+ e);
                    System.out.println("Tente de novo");
                    ler.nextLine();
                }
            }
        }
    }

    public static VendaComissionada pesquisaVenda( int idVenda){
        for(VendaComissionada vendaEncontrada: vendas){
            if(idVenda==vendaEncontrada.getId()){
                return vendaEncontrada;
            }
        }
        return null;
    }
    public static void consultaContrato(){
        while(true) {
            try {
                System.out.println("digite o ID do contrato que deseja consultar [0 para cancelar] ");
                int id = ler.nextInt();
                if(id==0){
                    System.out.println("Processo encerrado");
                    return;
                }

                Contrato contratoEncontrado = pesquisaContrato(id);

                if(contratoEncontrado==null){
                    System.out.println("O contrato não foi encontrado");
                    continue;
                }
                if(contratoEncontrado instanceof ContratoAssalariado){
                    System.out.println("Data de início do contrato: " + formato.format(contratoEncontrado.getDataInicio()));

                    if(contratoEncontrado.getDataEncerramento()!=null) {
                        System.out.println("Data de encerramento: " + formato.format(contratoEncontrado.getDataEncerramento()));
                    }

                    if(contratoEncontrado.getDataEncerramento()==null){
                        System.out.println("Data de encerramento: " + contratoEncontrado.getDataEncerramento());
                    }

                    System.out.println("situação do contrato: "+ contratoEncontrado.isAtivo());
                    System.out.println("Tipo: Assalariado");
                    System.out.println("Matrícula do colaborador: "+ contratoEncontrado.getColaborador().getMatricula());
                    System.out.println("Nome do colaborador: "+ contratoEncontrado.getColaborador().getNome());
                    System.out.println("cpf do colaborador: " +contratoEncontrado.getColaborador().getCpf());
                    System.out.println("Colaborador Ativo: " + contratoEncontrado.getColaborador().isSituacao());
                    continue;
                }
                if(contratoEncontrado instanceof ContratoComissionado){
                    System.out.println("Data de início do contrato: " + formato.format(contratoEncontrado.getDataInicio()));

                    if(contratoEncontrado.getDataEncerramento()!=null) {
                        System.out.println("Data de encerramento: " + formato.format(contratoEncontrado.getDataEncerramento()));
                    }

                    if(contratoEncontrado.getDataEncerramento()==null){
                        System.out.println("Data de encerramento: "+ contratoEncontrado.getDataEncerramento());
                    }

                    System.out.println("situação do contrato: "+ contratoEncontrado.isAtivo());
                    System.out.println("Tipo: Assalariado");
                    System.out.println("Matrícula do colaborador: "+ contratoEncontrado.getColaborador().getMatricula());
                    System.out.println("Nome do colaborador: "+ contratoEncontrado.getColaborador().getNome());
                    System.out.println("cpf do colaborador: " +contratoEncontrado.getColaborador().getCpf());
                    System.out.println("Colaborador Ativo: " + contratoEncontrado.getColaborador().isSituacao());
                    System.out.println("--------------------------------------------------------------------------");
                    continue;
                }

                if(contratoEncontrado instanceof ContratoHorista){
                    System.out.println("Data de início do contrato: " + formato.format(contratoEncontrado.getDataInicio()));

                    if(contratoEncontrado.getDataEncerramento()!=null) {
                        System.out.println("Data de encerramento: " + formato.format(contratoEncontrado.getDataEncerramento()));
                    }
                    if(contratoEncontrado.getDataEncerramento()==null){
                        System.out.println("Data de encerramento: "+ contratoEncontrado.getDataEncerramento());
                    }

                    System.out.println("situação do contrato: "+ contratoEncontrado.isAtivo());
                    System.out.println("Tipo do contrato: Horista ");
                    System.out.println("Matrícula do colaborador: "+ contratoEncontrado.getColaborador().getMatricula());
                    System.out.println("Nome do colaborador: "+ contratoEncontrado.getColaborador().getNome());
                    System.out.println("cpf do colaborador: " +contratoEncontrado.getColaborador().getCpf());
                    System.out.println("Colaborador Ativo: " + contratoEncontrado.getColaborador().isSituacao());
                   continue;
                }

            }catch(Exception e){
                System.out.println("Ocorreu um erro"+ e);
                ler.nextLine();
            }

        }
    }

    public static void encerraContrato(){
        while(true) {
            try {
                System.out.println("Digite o id do contrato que deseja encerrar [0 para cancelar]");
                int id=ler.nextInt();
                if(id==0){
                    System.out.println("Processor encerrado");
                    continue;
                }
                Contrato contratoEncontrado=pesquisaContrato(id);

                if(contratoEncontrado==null){
                    System.out.println("O contrato não foi encontrado");
                    continue;
                }

                if(!contratoEncontrado.isAtivo()){
                    System.out.println("O contrato já foi encerrado");
                    continue;
                }
                ler.nextLine();
                System.out.println("Digite a data do encerramento");
                String data=ler.nextLine();
                Date dataFormatada;

                dataFormatada=formato.parse(data);

                contratoEncontrado.setDataEncerramento(dataFormatada);

                contratoEncontrado.encerrarContrato();

                if(contratoEncontrado instanceof ContratoAssalariado){
                    System.out.println("Data de início do contrato: " + formato.format(contratoEncontrado.getDataInicio()));
                    System.out.println("Data de encerramento do contrato: " + formato.format(contratoEncontrado.getDataEncerramento()));
                    System.out.println("situação do contrato: "+ contratoEncontrado.isAtivo());
                    System.out.println("Tipo: Assalariado");
                    System.out.println("Matrícula do colaborador: "+ contratoEncontrado.getColaborador().getMatricula());
                    System.out.println("Nome do colaborador: "+ contratoEncontrado.getColaborador().getNome());
                    System.out.println("cpf do colaborador: " +contratoEncontrado.getColaborador().getCpf());
                    System.out.println("Colaborador Ativo: " + contratoEncontrado.getColaborador().isSituacao());
                    return;
                }
                if(contratoEncontrado instanceof ContratoComissionado){
                    System.out.println("Data de início do contrato: " + formato.format(contratoEncontrado.getDataInicio()));
                    System.out.println("Data de encerramento: " + formato.format(contratoEncontrado.getDataEncerramento()));
                    System.out.println("situação do contrato: "+ contratoEncontrado.isAtivo());
                    System.out.println("Tipo: Assalariado");
                    System.out.println("Matrícula do colaborador: "+ contratoEncontrado.getColaborador().getMatricula());
                    System.out.println("Nome do colaborador: "+ contratoEncontrado.getColaborador().getNome());
                    System.out.println("cpf do colaborador: " +contratoEncontrado.getColaborador().getCpf());
                    System.out.println("Colaborador Ativo: " + contratoEncontrado.getColaborador().isSituacao());
                    return;
                }

                if(contratoEncontrado instanceof ContratoHorista){
                    System.out.println("Data de início do contrato: " + formato.format(contratoEncontrado.getDataInicio()));
                    System.out.println("Data de encerramento: " + formato.format(contratoEncontrado.getDataEncerramento()));
                    System.out.println("situação do contrato: "+ contratoEncontrado.isAtivo());
                    System.out.println("Tipo do contrato: Horista ");
                    System.out.println("Matrícula do colaborador: "+ contratoEncontrado.getColaborador().getMatricula());
                    System.out.println("Nome do colaborador: "+ contratoEncontrado.getColaborador().getNome());
                    System.out.println("cpf do colaborador: " +contratoEncontrado.getColaborador().getCpf());
                    System.out.println("Colaborador Ativo: " + contratoEncontrado.getColaborador().isSituacao());
                    return;
                }

            }catch (Exception e){
                System.out.println("Ocorreu tal erro: " + e);
                System.out.println("Tente de novo");
                ler.nextLine();
            }
        }
    }
    public static void listaColaboradores(){
        int cont = 0;
        System.out.println("Mostrando os colaboradores ativos: ");

        for(Colaborador colaboradorEncontrado:colaboradores){
            if(colaboradorEncontrado.isSituacao()){
                cont++;
                System.out.println("Matrícula: " + colaboradorEncontrado.getMatricula());
                System.out.println("Nome do Colaborador: "+ colaboradorEncontrado.getNome());
                System.out.println("CPF do colaborador: "+ colaboradorEncontrado.getCpf());
                System.out.println("----------------------------------------------------------------");
            }
        }
        if(cont==0){
            System.out.println("Nenhum colaborador está ativo");
        }
    }
    public static void contratosColaborador(){
        ler.nextLine();
        while(true){
            try {
                System.out.println("Digite a matrícula ou o cpf do Colaborador para consulta ");
                System.out.println("Digite 'sair' para retornar");
                String matricula = ler.nextLine();

                if(matricula.equals("sair")){
                    System.out.println("Processo finalizado");
                    return;
                }
                if(matricula.equals("SAIR")){
                    System.out.println("Processo finalizado");
                    return;
                }
                if(matricula.equals("Sair")){
                    System.out.println("Processo finalizado");
                    return;
                }

                Colaborador colaboradorEncontrado=pesquisaColaborador(matricula);

                if(colaboradorEncontrado==null){
                    System.out.println("Matrícula ou cpf não encontrada");
                    System.out.println("Tente de novo");
                    continue;
                }
                System.out.println("Matrícula: "+colaboradorEncontrado.getMatricula());
                System.out.println("Nome: " + colaboradorEncontrado.getNome());
                System.out.println("CPF : " + colaboradorEncontrado.getCpf());
                System.out.println("Colaborador Ativo: " + colaboradorEncontrado.isSituacao());

                for(Contrato contrato:contratos){
                    if(contrato.getColaborador().getMatricula().equals(colaboradorEncontrado.getMatricula())){
                        System.out.println("Identificador do Contrato:" + contrato.getId());
                        if(contrato instanceof ContratoHorista){
                            System.out.println("Tipo do Contrato: Horista");
                        }
                        if(contrato instanceof ContratoComissionado){
                            System.out.println("Tipo do Contrato: Comissionado");
                        }
                        if(contrato instanceof ContratoAssalariado){
                            System.out.println("Tipo do Contrato: Assalariado");
                        }
                        System.out.println("Data de início do contrato: "+ formato.format(contrato.getDataInicio()));
                       if(contrato.getDataEncerramento()!=null) {
                           System.out.println("Data de Encerramento do  contrato: " + formato.format(contrato.getDataEncerramento()));
                       }
                       if(contrato.isAtivo()){
                           System.out.println("Contrato ativo!");
                       }
                        if(!contrato.isAtivo()){
                            System.out.println("Contrato inativo!");
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("Ocorreu tal erro: "+ e);
                System.out.println("Tente de novo");
                continue;
            }
        }
    }
    public static void vendaComissao(){
        while(true) {
            try {
                System.out.println("Qual o identificador do contrato que receberá o pagamento [0 para cancelar]");
                int id=ler.nextInt();


                if(id==0){
                    System.out.println("Processo encerrado");
                    return;
                }

                 Contrato contratoEncontrado = pesquisaContrato(id);

                if(contratoEncontrado==null){
                    System.out.println("Contrato não encontrado");
                    continue;
                }

                if (!contratoEncontrado.isAtivo()){
                    System.out.println("O contrato está inativo");
                    continue;
                }

                if(contratoEncontrado instanceof ContratoComissionado==false){
                    System.out.println("O contrato não é comissionado");
                    continue;
                }

                System.out.println("Digite o id da venda que deseja realizar");
                int idVenda= ler.nextInt();

                VendaComissionada vendaEncontrada=pesquisaVenda(idVenda);

                if(vendaEncontrada!=null){
                    System.out.println("O Id já foi usado anteriormente");
                    continue;
                }



                int mes=0;
                while (true) {
                    System.out.println("Digite número do mês refente a essas vendas [1 - 12]");
                    mes = ler.nextInt();

                    if (mes > 12 || mes < 1) {
                        System.out.println("O número digitado não corresponde a nenhum mês");
                        continue;
                    }
                    break;
                }

                System.out.println("Digite o ano referente às vendas comissionadas");
                int ano = ler.nextInt();

                System.out.println("Digite o valor total das vendas deste mês");
                float valor=ler.nextFloat();

                if(valor<0){
                    System.out.println("Só são aceitos números maiores que 0");
                    continue;
                }

                VendaComissionada venda = new VendaComissionada(idVenda,mes,ano,valor,(ContratoComissionado)contratoEncontrado);
                vendas.add(venda);

                System.out.println("Venda Comissionada registrada!");
                System.out.println("Mês da venda: " + venda.getMes());
                System.out.println("Ano da venda: " + venda.getAno());
                System.out.println("Valor da venda: " + venda.getValor());
                System.out.println("ID do contrato da venda: " +venda.getContratrComissionado().getId());

            }catch (Exception e){
                System.out.println("Ocorreu tal erro: " + e);
                System.out.println("tente npvamente");
                ler.nextLine();
            }
        }

    }
    public static void emitirFolha(){
        while(true){
            try{
                System.out.println("Digite o ano da folha a ser emitida[0 para cancelar]");
                int ano= ler.nextInt();

                if(ano==0) {
                    System.out.println("Processo cancelado");
                    return;
                }

                System.out.println("Digite o mês da folha a ser emitida[0 para cancelar]");
                int mes=ler.nextInt();

                if(mes == 0){
                    System.out.println("Processo cancelado");
                    return;
                }

                if(mes<1||mes>12){
                    System.out.println("O número digitado não corresponde a nenhum Mês");
                    continue;
                }

                System.out.println("Folha de pagamento dos contratos ativos");

                int cont = 0;
                for(Contrato contratoEncontrado:contratos){
                    if(contratoEncontrado.isAtivo()){
                        cont++;
                        System.out.println("Id do contrato: "+ contratoEncontrado.getId());
                        System.out.println("Matrícula do colaborador: "+ contratoEncontrado.getColaborador().getMatricula());
                        System.out.println("Nome do colaborador: "+contratoEncontrado.getColaborador().getNome());

                        if(contratoEncontrado instanceof ContratoComissionado){
                            System.out.println("Tipo do contrato: Comissionado");
                            for(VendaComissionada venda:vendas){
                                if(venda.getContratrComissionado().getId()==contratoEncontrado.getId()) {
                                    float vencimento = ((ContratoComissionado) contratoEncontrado).calcVencimento(venda.getValor());
                                    contratoEncontrado.setVencimento(vencimento);
                                    System.out.println("O valor do salário(vencimento) é de: " + vencimento);
                                    float seguro=((ContratoComissionado) contratoEncontrado).calcSeguro();
                                    System.out.println("O valor do seguro do contrato é de: "+ seguro);
                                    System.out.println("O valor do vencimento descontado é de: "+ (vencimento-seguro) );
                                    System.out.println("--------------------------------------------------------------------------");
                                }
                            }
                        }
                        if(contratoEncontrado instanceof ContratoHorista){
                            System.out.println("Tipo do contrato: Horista");
                             ((ContratoHorista) contratoEncontrado).calcVencimento();
                            System.out.println("O valor do salário(vencimento) é de: "+ contratoEncontrado.getVencimento());
                            float seguro=((ContratoHorista) contratoEncontrado).calcSeguro();
                            System.out.println("O valor do seguro do contrato é de: "+ seguro);
                            System.out.println("O valor do vencimento descontado do seguro é de: "+ (seguro - contratoEncontrado.getVencimento()));
                            System.out.println("--------------------------------------------------------------------------");
                        }
                        if(contratoEncontrado instanceof ContratoAssalariado){
                            System.out.println("Tipo do contrato: Assalariado");
                            ((ContratoAssalariado) contratoEncontrado).calcVencimento();
                            System.out.println("O valor do salário(vencimento) é de: "+ contratoEncontrado.getVencimento());
                            float seguro=((ContratoAssalariado) contratoEncontrado).calcSeguro();
                            System.out.println("O valor do seguro é de:" + seguro);
                            System.out.println("O valor do vencimento descontado do seguro é de: "+ (seguro-contratoEncontrado.getVencimento()));
                            System.out.println("--------------------------------------------------------------------------");
                        }
                    }
                }
                if(cont==0){
                    System.out.println("Não existem contratos ativos");
                }

            }catch(Exception e){
                System.out.println("Ocorreu esse erro: " + e.getMessage());
                System.out.println("Tente novamente");
                ler.nextLine();
            }
        }
    }

}
