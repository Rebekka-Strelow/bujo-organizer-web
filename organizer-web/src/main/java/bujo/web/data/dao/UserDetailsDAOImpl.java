package bujo.web.data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bujo.web.data.entity.UserDetails;

@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserDetails> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from UserDetails", UserDetails.class).getResultList();
	}


	
}
