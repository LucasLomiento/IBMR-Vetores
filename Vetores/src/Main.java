import java.util.Scanner;

class Main {

    static Scanner console = new Scanner(System.in);

    static final int TOTAL_AVALIACOES = 3;
    static final String[] NOMES_AVALIACOES = {"A1", "A2", "A3"};
    static final double[] NOTA_MAX_AVALIACOES = {30.00, 30.00, 40.00};
    static double[] notas = new double[TOTAL_AVALIACOES];


    /**
     * Ler uma nota do usuário
     *
     * @param mensagem O texto que aparecerá na tela
     * @return um número double representando a nota.
     */

    static double lerNota(String mensagem, double notaMaxima) {

        double nota;

        do {

            System.out.printf("%s = ", mensagem);
            nota = console.nextDouble();

        } while (nota < 0.00 || nota > notaMaxima);

        return nota;
    }


    /**
     * Atualiza o valor da respectiva nota do estudante
     *
     * @param indiceNota um número inteiro representando o índice (posição) da nota no vetor
     */
    static void atualizarNota(int indiceNota) {

        System.out.println();
        notas[indiceNota] = lerNota(NOMES_AVALIACOES[indiceNota], NOTA_MAX_AVALIACOES[indiceNota]);

    } // Fim do método atualizarNota


    /**
     * @param notaFinal A soma de todas as avalições feita pelo estudante ao longo do semestre
     * @return uma string representando o status final do estudante, são eles: APROVADO, REPROVADO, EM RECUPERAÇÃO.
     */
    static String avaliarSituacao(double notaFinal) {

        if (notaFinal < 30)
            return "REPROVADO";
        else if (notaFinal < 70)
            return "EM RECUPERAÇÃO";
        else
            return "APROVADO";

    } // Fim do método avaliarSituacao()


    /**
     * Calular a média das notas do estudante
     */
    static double calcularMedia(double[] notas) {

        double media = 0;
        for (int i = TOTAL_AVALIACOES - 1; i >= 0; i--) {
            media += notas[i];
        }
        media /= notas.length;

        return media;
    } // Fim do método calcularMedia()


    /**
     * Mostra a maior nota do estudante
     */
    static String maiorNota(double[] notas) {

        String maiorNota = "";
        double verificarMaior = 0;
        for (int i = TOTAL_AVALIACOES - 1; i >= 0; i--) {
            if (notas[i] > verificarMaior) {
                verificarMaior = notas[i];
                maiorNota = NOMES_AVALIACOES[i];
            }
        }

        return maiorNota;
    } // Fim do método maiorNota()


    /**
     * vale 30 pontos
     * substitui a menor nota entre A1 e A2
     */
    static void provaAI(double[] notas) {
        double notaAi = 0;
        int piorProvaIndex = notas[0] <= notas[1] ? 0 : 1;

        System.out.println("Digite A Nota Da Prova AI");
        notaAi = lerNota("Prova AI", NOTA_MAX_AVALIACOES[piorProvaIndex]);

        if (notaAi > notas[piorProvaIndex]) {
            notas[piorProvaIndex] = notaAi;
            System.out.printf("A Avaliação %s Foi Substituida Pela Avalição AI\n", NOMES_AVALIACOES[piorProvaIndex]);
        } else {
            System.out.println("A Nota Da Prova AI Não Foi Suficiente Para Alterar A ");
        }


    }

    /**
     * Verifica se deseja fazer a prova AI
     */
    static void opcaoProvaAI(double[] notas) {

        if (calcularMedia(notas) <= 23.0 && calcularMedia(notas) >= 10.0 && (notas[0] + notas[1]) < 60) {
            System.out.print("""
                    \nDeseja Cadastrar Nota Prova AI?
                    Digite SIM ou NÃO:\s""");
            String opcao = console.next();

            boolean checkOpcao = false;
            while (!checkOpcao) {
                if (opcao.equalsIgnoreCase("sim")) {
                    provaAI(notas);
                    mostrarNotas();
                    checkOpcao = true;
                } else if (opcao.equalsIgnoreCase("não") || opcao.equalsIgnoreCase("nao")) {
                    System.out.println("Opção Digitada: Não");
                    checkOpcao = true;
                } else {
                    System.out.println("Opção Invalida");
                    System.out.print("Digite SIM ou NÃO: ");
                    opcao = console.next();
                }
            }

        }

    }

    /**
     * Mostra na tela um relatório das notas do estudante
     */
    static void mostrarNotas() {

        double notaFinal = 0.0;

        System.out.println("""
                                NOTAS
                        """);

        for (int i = 0; i < TOTAL_AVALIACOES; i++) {

            System.out.printf("Avaliação %s = %.2f pts", NOMES_AVALIACOES[i], notas[i]);
            System.out.println();
            notaFinal += notas[i];

        }

        System.out.printf("""
                        Nota Final = %.2f pts
                        Situação = %s
                        Media = %.2f
                        Melhor Prova = %s
                        
                        """, notaFinal, avaliarSituacao(notaFinal),calcularMedia(notas),maiorNota(notas));
        System.out.print("Aperte Enter Para Continuar\n");

        console.nextLine();
        console.nextLine();



    } // Fim do método mostrarNotas()


    /**
     * Exibe o menu principal da aplicação
     */
    static void mostrarMenu() {

        System.out.println("""
                        Menu
                
                [1] Cadastrar Nota A1
                [2] Cadastrar Nota A2
                [3] Cadastrar Nota A3
                [4] Mostar Notas
                [0] Sair""");

        System.out.print("\nDigite uma opção:  ");
        byte opcao = console.nextByte();

        switch (opcao) {

            case 0:
                System.exit(0);
                break;

            case 1:
                atualizarNota(0);
                break;

            case 2:
                atualizarNota(1);
                break;

            case 3:
                atualizarNota(2);
                break;

            case 4:
                mostrarNotas();
                opcaoProvaAI(notas);
                break;

            default:
                mostrarMenu();
                break;

        }

        mostrarMenu();

    } // Fim do método mostrarMenu()


    public static void main(String[] args) {

        mostrarMenu();

    } // Fim do método main();

} // Fim da classe Main