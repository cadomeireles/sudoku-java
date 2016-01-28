package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DAO {
	
	private static EntityManagerFactory factory;
	public static EntityManager manager;

	static {
		factory = Persistence.createEntityManagerFactory("sudoku");
		manager = factory.createEntityManager();
	}

	public static <ENTITY> ENTITY persist( ENTITY entity ) {
		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();
		manager.persist( entity );
		transaction.commit();

		return entity;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		manager.close();
		factory.close();
	}	

}
