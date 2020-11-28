package repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import model.Customer;
import model.Profile;

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
	
	public List<Profile> findAllProfileByCustomerId(int id){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id, p.name  ");
		sql.append(" FROM customer as c, profile as p, customer_profile as cp  ");
		sql.append(" WHERE cp.customer_id = ?  ");
		sql.append(" AND cp.profile_id = p.id  ");
		sql.append("AND cp.customer_id = c.id  " );
		
		Query query = getEntityManager().createNativeQuery(sql.toString());
		query.setParameter(1, id);
		
		List<Object[]> res = query.getResultList();
		List<Profile> list= new ArrayList<Profile>();

		Iterator it = res.iterator();
		while(it.hasNext()){
		     Object[] line = (Object[]) it.next();
		     Profile p = new Profile();
		     p.setId(Integer.parseInt(line[0].toString()));
		     p.setName((String) line[1]);

		     list.add(p);
		}
		
		return list;
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
