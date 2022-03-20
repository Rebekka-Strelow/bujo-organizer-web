package bujo.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bujo.web.data.dao.AccountDAO;
import bujo.web.data.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDAO accountDao;

	@Override
	@Transactional
	public boolean accountExists(String username, String password) {
		return accountDao.accountExists(username, password);
	}

	@Override
	@Transactional
	public boolean accountNameExists(String username) {
		return accountDao.accountNameExists(username);
	}

	@Override
	@Transactional
	public List<Account> getAllAccounts() {
		return accountDao.getAllAccounts();
	}

	@Override
	@Transactional
	public void saveAccount(Account account) {
		accountDao.saveAccount(account);
	}

	@Override
	@Transactional
	public void deleteAccount(Account account) {
		accountDao.deleteAccount(account);
	}

	@Override
	@Transactional
	public Account getAccountByPK(int id) {
		return accountDao.getAccountByPK(id);
	}

}
