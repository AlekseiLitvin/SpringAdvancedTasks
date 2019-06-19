package beans.models;

public class UserAccount {

    private long id;
    private int prepaidMoney;

    public UserAccount() {
    }

    public UserAccount(int prepaidMoney) {
        this.prepaidMoney = prepaidMoney;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getPrepaidMoney() {
        return prepaidMoney;
    }

    public void setPrepaidMoney(int prepaidMoney) {
        this.prepaidMoney = prepaidMoney;
    }
}
