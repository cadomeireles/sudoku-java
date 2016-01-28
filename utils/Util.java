package utils;

import java.util.List;
import java.util.Scanner;

import com.mysema.query.jpa.impl.JPAQuery;

import entities.QResultado;
import entities.QSudoku;
import entities.Resultado;
import entities.Sudoku;

public class Util {

	static int tamanho = 9;

	/*
	 * Receber os valores:
	 * 
	 * @see utils.Util#receberValores(Integer[][] sudoku)
	 * 
	 * Cria um sudoku que fica com os dados de entrada e outro que terá os dados
	 * cauculados.
	 * 
	 * Calcula o resultado:
	 * 
	 * @see utils.Util#calularResultado(Integer[][] sudoku)
	 * 
	 * Persisti o resultado:
	 * 
	 * @see utils.Util#persistirSudoku(Integer[][] sudoku_inicial, Integer[][]
	 * sudoku_final)
	 * 
	 * Exibe o resultado
	 * 
	 * @see utils.Util#exibirResultado(Integer[][] sudoku_inicial, Integer[][]
	 * sudoku_final)
	 * 
	 * @return void
	 */
	public static void jogar() {

		Integer[][] sudoku_inicial = new Integer[tamanho][tamanho];
		Integer[][] sudoku_final = new Integer[tamanho][tamanho];

		receberValores(sudoku_inicial);

		for (int linha = 0; linha < tamanho; linha++) {
			for (int coluna = 0; coluna < tamanho; coluna++) {
				sudoku_final[linha][coluna] = sudoku_inicial[linha][coluna];
			}
		}

		calularResultado(sudoku_final);

		persistirSudoku(sudoku_inicial, sudoku_final);

		exibirResultado(sudoku_inicial, sudoku_final);

	}

	/*
	 * Recebe uma matriz bidimensional, lê os valores do teclado e os atribue à
	 * matriz
	 * 
	 * @param sudoku Matriz que vai receber os valores
	 * 
	 * @return retorna a matriz que foi enviada preenchida
	 */
	public static Integer[][] receberValores(Integer[][] sudoku) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o jogo: ");
		for (int linha = 0; linha < tamanho; linha++) {
			for (int coluna = 0; coluna < tamanho; coluna++) {
				sudoku[linha][coluna] = scanner.nextInt();
			}
		}

		return sudoku;

	}

	/*
	 * Calcula o resultado do jogo fornecido pelo usuário
	 * 
	 * @see utils.Util#alteravel(Integer[][] sudoku, int linha, int coluna)
	 * 
	 * @see utils.Util#temNaLinha(Integer[][] sudoku, int linha, int valor)
	 * 
	 * @see utils.Util#temNaColuna(Integer[][] sudoku, int coluna, int valor)
	 * 
	 * @see utils.Util#temNoQuadrante(Integer[][] sudoku, int linha, int coluna,
	 * int valor)
	 * 
	 * @param sudoku Jogo para ser resolvido
	 * 
	 * @return retorna o jogo enviado resolvido
	 */
	public static Integer[][] calularResultado(Integer[][] sudoku) {
		System.out.println("Calculando...");
		Integer[][] sudoku_entrada = new Integer[9][9];

		for (int linha = 0; linha < tamanho; linha++) {
			for (int coluna = 0; coluna < tamanho; coluna++) {
				sudoku_entrada[linha][coluna] = sudoku[linha][coluna];
			}
		}

		int linha = 0;
		int coluna = 0;
		int valor = 1;
		boolean preenchido = true;
		while (linha < tamanho && coluna < tamanho) {
				if (alteravel(sudoku_entrada, linha, coluna)) {
					preenchido = false;
					
					for (int i = valor; i <= tamanho; i++) {
						if ((!temNaColuna(sudoku, coluna, i))
								&& (!temNaLinha(sudoku, linha, i))
								&& (!temNoQuadrante(sudoku, linha, coluna, i))) {
							sudoku[linha][coluna] = i;
							preenchido = true;
							break;
						}
					}
					if (preenchido) {
						if (coluna == tamanho - 1) {
							coluna = 0;
							linha++;
						} else {
							coluna++;
						}
						valor = 1;
					} else {
						sudoku[linha][coluna] = 0;
						if (coluna == 0) {
							coluna = tamanho - 1;
							linha--;
						} else {
							coluna--;
						}
					}
					if (linha != 9) {
						valor = sudoku[linha][coluna] + 1;
						
					}
				} else {
					if (preenchido) {
						if (coluna == tamanho - 1) {
							coluna = 0;
							linha++;
						} else {
							coluna++;
						}
						valor = 1;
					} else {
						if (coluna == 0) {
							coluna = 0;
							linha--;
						} else {
							coluna--;
						}
						valor = sudoku[linha][coluna] + 1;
					}

				}


		}
		return sudoku;
	}

	/*
	 * Torna o cartesiano linha X coluna alterável, ou seja, igual a zero
	 * 
	 * @param sudoku Jogo para ser resolvido
	 * 
	 * @param linha Linha que deve ser checada
	 * 
	 * @param valor Valor que será checado
	 * 
	 * @return void
	 */
	public static boolean alteravel(Integer[][] sudoku, int linha, int coluna) {
		return sudoku[linha][coluna] == 0;
	}

	/*
	 * Verifica se o valor existe na linha
	 * 
	 * @param sudoku Jogo para ser resolvido
	 * 
	 * @param linha Linha que deve ser checada
	 * 
	 * @param valor Valor que será checado
	 * 
	 * @return retorna true se o valor estiver na linha e false se não estiver
	 */
	private static boolean temNaLinha(Integer[][] sudoku, int linha, int valor) {
		for (int i = 0; i < tamanho; i++) {
			if (sudoku[linha][i] == valor) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Verifica se o valor existe na coluna
	 * 
	 * @param sudoku Jogo para ser resolvido
	 * 
	 * @param coluna Coluna que deve ser checada
	 * 
	 * @param valor Valor que será checado
	 * 
	 * @return retorna true se o valor estiver na coluna e false se não estiver
	 */
	private static boolean temNaColuna(Integer[][] sudoku, int coluna, int valor) {
		for (int i = 0; i < tamanho; i++) {
			if (sudoku[i][coluna] == valor) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Verifica se o valor existe na coluna
	 * 
	 * @param sudoku Jogo para ser resolvido
	 * 
	 * @param linha Linha que serve de base para montar o quadrante
	 * 
	 * @param coluna Coluna que serve de base para montar o quadrante
	 * 
	 * @param valor Valor que será checado
	 * 
	 * @return retorna true se o valor estiver na coluna e false se não estiver
	 */
	private static boolean temNoQuadrante(Integer[][] sudoku, int linha,
			int coluna, int valor) {
		linha = linha / 3;
		linha = (linha == 0) ? 0 : (linha == 1) ? 3 : 6;
		coluna = coluna / 3;
		coluna = (coluna == 0) ? 0 : (coluna == 1) ? 3 : 6;
		for (int i = linha; i < (linha + 3); i++) {
			for (int j = coluna; j < (coluna + 3); j++) {
				if (sudoku[i][j] == valor) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Persiste no banco o sudoku com os dados de entrada e persiste o sudoku
	 * com os resultados calculados, o sudoku com os dados de entrada são os
	 * valores iniciais do qual foi gerado o sudoku com os resultados calculados
	 * 
	 * @param sudoku_inicial Matriz com a entrada do usuário
	 * 
	 * @param sudoku_final Matriz que foi calculada
	 * 
	 * @return void
	 */
	public static void persistirSudoku(Integer[][] sudoku_inicial,
			Integer[][] sudoku_final) {

		Sudoku sudoku_entrada = new Sudoku(null);

		DAO.persist(sudoku_entrada);
		for (Integer[] valores : sudoku_inicial) {
			for (Integer valor : valores) {
				DAO.persist(new Resultado(null, valor, sudoku_entrada));
			}
		}

		Sudoku sudoku_saida = new Sudoku(null, sudoku_entrada);

		DAO.persist(sudoku_saida);
		for (Integer[] valores : sudoku_final) {
			for (Integer valor : valores) {
				DAO.persist(new Resultado(null, valor, sudoku_saida));
			}
		}

	}

	/*
	 * Exibe o sudoku com os dados de entrada e o o sudoku com os resultados
	 * calculados
	 * 
	 * @param sudoku_inicial Matriz com a entrada do usuário
	 * 
	 * @param sudoku_final Matriz que foi calculada
	 * 
	 * @return void
	 */
	public static void exibirResultado(Integer[][] sudoku_inicial,
			Integer[][] sudoku_final) {

		System.out.println("Entrada: ");
		for (int linha = 0; linha < tamanho; linha++) {
			for (int coluna = 0; coluna < tamanho; coluna++) {
				System.out.print(String.format("[%d]",
						sudoku_inicial[linha][coluna]));
			}
			System.out.println("\n");
		}
		System.out.println("Saída: ");
		for (int linha = 0; linha < tamanho; linha++) {
			for (int coluna = 0; coluna < tamanho; coluna++) {
				System.out.print(String.format("[%d]",
						sudoku_final[linha][coluna]));
			}
			System.out.println("\n");
		}

	}

	/*
	 * Exibe os identificadores dos jogos resolvidos, recebe um valor do
	 * teclado, busca e exibe o jogo de entrada e o jogo resolvido de acordo com
	 * o identificador digitado
	 */
	public static void buscarJogo() {

		Scanner scanner = new Scanner(System.in);

		QSudoku sudoku = QSudoku.sudoku;
		QResultado resultado = QResultado.resultado;

		JPAQuery sudoku_exibir = new JPAQuery(DAO.manager);
		JPAQuery sudoku_busca = new JPAQuery(DAO.manager);

		List<Sudoku> sudokus = sudoku_exibir.from(sudoku)
				.where(sudoku.estadoInicial.isNotNull()).list(sudoku);

		System.out.print("ID dos jogos realizados: ");

		for (Sudoku jogo : sudokus) {
			System.out.print(jogo.getId() + " ");
		}

		System.out.print("\nSelecione um para ver o jogo:\n");

		Long busca = scanner.nextLong();

		List<Resultado> resultados = sudoku_busca.from(resultado)
				.where(resultado.sudoku.id.eq(busca)).list(resultado);

		int cont = 1;
		for (Resultado valor : resultados) {
			System.out.print("[" + valor.getValor() + "]");
			if (cont % 9 == 0) {
				System.out.print("\n");
			}
			cont++;
		}

	}

}
