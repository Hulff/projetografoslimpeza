package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File myObj = new File("src/prompt.txt");
            Scanner myReader = new Scanner(myObj);

            int i = 0; // Contador de linhas
            int nPontos = 0; // Número de pontos de coleta
            int[][] matrizPesos = null; // Armazenar a matriz de pesos

            while (myReader.hasNextLine()) {
                i++;
                String data = myReader.nextLine().trim(); // Remove espaços extras

                // Pula linhas de comentário ou vazias
                if (data.startsWith("//") || data.isEmpty()) {
                    continue;
                }

                // Identifica o número de pontos de coleta
                if (i == 2) {
                    nPontos = Integer.parseInt(data);
                    matrizPesos = new int[nPontos][nPontos]; // Inicializa a matriz
                    System.out.println("Número de pontos de coleta: " + nPontos);
                }

                // Lê a matriz de pesos
                if (i > 3 && i <= 3 + nPontos) {
                    String[] valores = data.split(","); // Divide a linha em valores
                    int linha = i - 4; // Índice da linha na matriz
                    for (int coluna = 0; coluna < valores.length; coluna++) {
                        matrizPesos[linha][coluna] = Integer.parseInt(valores[coluna].trim());
                        System.out.println("matriz[" + linha + "][" + coluna + "] = " + matrizPesos[linha][coluna]);
                    }
                }
               //Lê a lista de adjacências
                if (i > 4 + nPontos && i <= 4 + (2 * nPontos)) {
                    System.out.println("Lista de adjacência para o vértice " + data.charAt(0) + ":");
                    String[] neighbors = data.substring(2).split(","); // Divide os vizinhos
                    for (String neighbor : neighbors) {
                        if (!neighbor.isEmpty()) {
                            System.out.println("Aresta: v" + data.charAt(0) + " -> v" + neighbor);
                        }
                    }
                }
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro: O arquivo não foi encontrado.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema durante a leitura do arquivo.");
            e.printStackTrace();
        }
    }
}
