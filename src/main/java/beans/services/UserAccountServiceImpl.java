package beans.services;

import beans.daos.UserAccountDao;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountDao userAccountDao;

    @Autowired
    public UserAccountServiceImpl(@Qualifier("userAccountDao") UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public void save(UserAccount userAccount) {
        userAccountDao.save(userAccount);
    }

    @Override
    public UserAccount getById(long id) {
        return userAccountDao.getById(id);
    }

    @Override
    public void update(UserAccount userAccount) {
        userAccountDao.update(userAccount);
    }
}
