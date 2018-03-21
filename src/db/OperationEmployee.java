package db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class OperationEmployee {




	public static void main( String[ ] args ) {

		//		ordering();
		//		find();
		//      namedQuery();
				apiCriteria();
	}

	public static void find() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPAExsample" );
		EntityManager entitymanager = emfactory.createEntityManager(); 
		List<Employee> employee = new ArrayList<Employee>();
		Query q=  entitymanager.createQuery("select e FROM Employee e");
		employee=q.getResultList();
		for (Employee a:employee) {
			System.out.println("employee ID = "+a.getEid( ) + " Name "+ a.getEname( )+" Salary "+ a.getSalary() +" Dest "+ a.getDeg());

		}

	}

	public static void ordering() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPAExsample" );
		EntityManager entitymanager = emfactory.createEntityManager();

		Query query = entitymanager.createQuery( "Select e " +"from Employee e " + "ORDER BY e.ename ASC" );
		List<Employee> list=(List<Employee>)query.getResultList( );
		for( Employee e:list )	{
			System.out.print("Employee ID :"+e.getEid( )); 
			System.out.println("Employee Name :"+e.getEname( ));
		}
	}

	public static void namedQuery() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPAExsample" );
		EntityManager entitymanager = emfactory.createEntityManager();
		Query query = entitymanager.createNamedQuery("find employee by id");
		query.setParameter("id", 2);
		List<Employee> list = query.getResultList( );
		for( Employee e:list ){
			System.out.print("Employee ID :"+e.getEid( ));
			System.out.println("\t Employee Name :"+e.getEname( ));
		}
	}


	public static void apiCriteria() {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPAExsample" );
		EntityManager entitymanager = emfactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		Root<Employee> from = criteriaQuery.from(Employee.class);

		//select all records
		System.out.println("Select all records");
		CriteriaQuery<Object> select =criteriaQuery.select(from);
		TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
		List<Object> resultlist= typedQuery.getResultList();
		for(Object o:resultlist){
			Employee e=(Employee)o;
			System.out.println("EID : "+e.getEid()+" Ename : "+e.getEname());
		}


	}

}



