package top.youlanqiang.simplespring.strategy.pay;

import top.youlanqiang.simplespring.strategy.Payment;

/**
 * @author youlanqiang
 * created in 2021/11/18 7:42 下午
 */
public class WechatPay extends Payment {

    @Override
    public String getName() {
        return "微信";
    }

    @Override
    protected double queryBalance(String uid) {
        return 500;
    }
}
