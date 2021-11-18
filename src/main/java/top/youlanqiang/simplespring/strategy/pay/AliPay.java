package top.youlanqiang.simplespring.strategy.pay;

import top.youlanqiang.simplespring.strategy.Payment;

/**
 * @author youlanqiang
 * created in 2021/11/18 7:40 下午
 */
public class AliPay extends Payment {

    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    protected double queryBalance(String uid) {
        return 900;
    }
}
