package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Sudoku {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_id")
	@SequenceGenerator(name = "seq_id", initialValue=1, sequenceName="seq_id")
	private Long id;

	@OneToOne
	private Sudoku estadoInicial;
	
	public Sudoku() {
		super();
	}

	public Sudoku(Long id) {
		super();
		this.id = id;
	}

	public Sudoku(Long id, Sudoku estadoInicial) {
		super();
		this.id = id;
		this.estadoInicial = estadoInicial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sudoku getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(Sudoku estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	@Override
	public String toString() {
		return "Sudoku " + id;
	}
	
}
