package top.youlanqiang.simplespring.strategy;

import top.youlanqiang.simplespring.strategy.pay.AliPay;
import top.youlanqiang.simplespring.strategy.pay.WechatPay;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author youlanqiang
 * created in 2021/11/18 7:43 下午
 */
public final class PayStrategy {


    public static final String ALI = "支付宝";

    public static final String WECHAT = "微信";

    public static final String DEFAULT_PAY = ALI;

    private static final Map<String, Payment> payStrategy = new ConcurrentHashMap<>();

    static{
        payStrategy.put(ALI, new AliPay());
        payStrategy.put(WECHAT, new WechatPay());
    }

    public static Payment get(String payKey){
        if(!payStrategy.containsKey(payKey)){
            return payStrategy.get(DEFAULT_PAY);
        }
        return payStrategy.get(payKey);
    }

}
