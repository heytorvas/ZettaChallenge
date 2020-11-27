package repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import model.Customer;

public class CustomerRepository extends Repository<Customer> {
	
	public List<Customer> findAll(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  c ");
		sql.append("FROM ");
		sql.append("  Customer  c ");
		sql.append("ORDER BY c.name ASC" );
		
		Query query = getEntityManager().createQuery(sql.toString());
		return query.getResultList();
	}
	
	public boolean containsCpf(String cpf) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  count(*) ");
		sql.append("FROM ");
		sql.append("  Customer  c ");
		sql.append("WHERE ");
		sql.append("  upper(c.cpf) = upper(?) ");
		
		Query query = getEntityManager().createNativeQuery(sql.toString());
		query.setParameter(1, cpf);
		
		BigInteger result = (BigInteger) query.getSingleResult();
		
		return (result == null || result.equals(BigInteger.ZERO)) ? false : true;
	}
}
