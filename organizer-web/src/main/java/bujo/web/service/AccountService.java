package bujo.web.service;

import java.util.List;

import bujo.web.data.entity.Account;

public interface AccountService {
	public boolean accountExists(String username, String password);

	public boolean accountNameExists(String username);

	public List<Account> getAllAccounts();

	public void saveAccount(Account account);

	public void deleteAccount(Account account);

	public Account getAccountByPK(int id);
}
