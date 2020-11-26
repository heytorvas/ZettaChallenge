package repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import model.Profile;

public class ProfileRepository extends Repository<Profile> {
	
	public List<Profile> findAll(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  p ");
		sql.append("FROM ");
		sql.append("  Profile p ");
		sql.append("ORDER BY p.name ASC" );
		
		Query query = getEntityManager().createQuery(sql.toString());
		return query.getResultList();
	}
	
	public boolean containsName(String name) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  count(*) ");
		sql.append("FROM ");
		sql.append("  Profile p ");
		sql.append("WHERE ");
		sql.append("  upper(p.name) = upper(?) ");
		
		Query query = getEntityManager().createNativeQuery(sql.toString());
		query.setParameter(1, name);
		
		BigInteger result = (BigInteger) query.getSingleResult();
		
		return (result == null || result.equals(BigInteger.ZERO)) ? false : true;
	}
}
