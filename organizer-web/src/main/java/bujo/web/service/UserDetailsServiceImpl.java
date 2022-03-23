package bujo.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bujo.web.data.dao.UserDetailsDAO;
import bujo.web.data.entity.UserDetails;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsDAO dao;
	
	@Override
	public List<UserDetails> getAll() {
		return dao.getAll();
	}

}
