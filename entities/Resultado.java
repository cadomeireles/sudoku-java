package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Resultado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_id")
	@SequenceGenerator(name = "seq_id", initialValue=1, sequenceName="seq_id")
	private Long id;
	
	private int valor;
	
	@ManyToOne
	private Sudoku sudoku;

	public Resultado() {
		super();
	}

	public Resultado(Long id, int valor, Sudoku sudoku) {
		super();
		this.id = id;
		this.valor = valor;
		this.sudoku = sudoku;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	@Override
	public String toString() {
		return "" + this.valor;
	}

}
