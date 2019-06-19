package beans.services;

import beans.models.UserAccount;

public interface UserAccountService {

    void save(UserAccount userAccount);

    UserAccount getById(long id);

    void update(UserAccount userAccount);

}
