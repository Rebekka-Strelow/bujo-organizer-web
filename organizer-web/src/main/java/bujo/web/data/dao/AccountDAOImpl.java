package bujo.web.data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bujo.web.config.util.HibernateUtil;
import bujo.web.data.entity.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory factory;// = HibernateUtil.getSessionFactory();

	
	@Override
	public boolean accountExists(String username, String password) {
		Session session = factory.getCurrentSession();	
		
		Query<Account> query = session.createQuery(
				"select from Account where username=:username AND password=:password", 
				Account.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		Account account = query.getSingleResult();		
		return account != null;
	}

	@Override
	public boolean accountNameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> getAllAccounts() {
		Session session = factory.getCurrentSession();	
		return session.createQuery("from Account a", Account.class).getResultList();
	}

	@Override
	public void saveAccount(Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public Account getAccountByPK(int id) {
		Session session = factory.getCurrentSession();	
		return session.get(Account.class, id);
	}

}
