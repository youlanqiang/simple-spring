package top.youlanqiang.simplespring.strategy;

/**
 * @author youlanqiang
 * created in 2021/11/18 7:04 下午
 *
 * 表示支付状态类
 */
public class PayState {

    private int code;

    private Object data;

    private String msg;

    public PayState(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
