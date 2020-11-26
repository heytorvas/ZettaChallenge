package repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import model.Role;

public class RoleRepository extends Repository<Role> {
	
	public List<Role> findAll(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  r ");
		sql.append("FROM ");
		sql.append("  Role r ");
		sql.append("ORDER BY r.name ASC" );
		
		Query query = getEntityManager().createQuery(sql.toString());
		return query.getResultList();
	}
	
	public boolean containsName(String name) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  count(*) ");
		sql.append("FROM ");
		sql.append("  role r ");
		sql.append("WHERE ");
		sql.append("  upper(r.name) = upper(?) ");
		
		Query query = getEntityManager().createNativeQuery(sql.toString());
		query.setParameter(1, name);
		
		BigInteger result = (BigInteger) query.getSingleResult();
		
		return (result == null || result.equals(BigInteger.ZERO)) ? false : true;
	}
}
