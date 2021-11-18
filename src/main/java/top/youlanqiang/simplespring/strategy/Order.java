package top.youlanqiang.simplespring.strategy;

/**
 * @author youlanqiang
 * created in 2021/11/18 8:03 下午
 */
public class Order {


    private String uid;

    private double amount;

    public Order(String uid, double amount) {
        this.uid = uid;
        this.amount = amount;
    }

    public PayState pay(String payKey){
        Payment payment = PayStrategy.get(payKey);
        return payment.pay(uid, amount);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
