package pw.lumm.utils.duanxin;

import com.montnets.mwgate.common.GlobalParams;
import com.montnets.mwgate.common.Message;
import com.montnets.mwgate.common.MultiMt;
import com.montnets.mwgate.common.Remains;
import com.montnets.mwgate.smsutil.ConfigManager;
import com.montnets.mwgate.smsutil.SmsSendConn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SendUtil {
    public static void main(String[] args) {
        // 用户账号

        // 创建全局参数


        // 单条发送



    }
public  SmsSendConn mainFun(){
    GlobalParams globalParams = new GlobalParams();
    // 设置请求路径
    globalParams.setRequestPath("/sms/v2/std/");
    globalParams.setNeedLog(1);
    // 设置全局参数
    ConfigManager.setGlobalParams(globalParams);

    // 设置用户账号信息
    setAccountInfo();

    // 是否保持长连接
    boolean isKeepAlive = false;
    // 实例化短信处理对象
    SmsSendConn smsSendConn = new SmsSendConn(isKeepAlive);
    return smsSendConn;
}
    /**
     * @description 设置用户账号信息
     */
    public static void setAccountInfo() {

        // 用户账号
        String userid = "E103LW";
        // 密码
        String password = "5ImCi9";
        // 发送优先级
        int priority = 1;
        // 主IP信息
        String ipAddress1 = "api01.monyun.cn:7901";

        // 备用IP1信息
        String ipAddress2 = "192.169.1.189:8086";
        // 备用IP2信息
        String ipAddress3 = null;
        // 备用IP3信息
        String ipAddress4 = null;
        // 返回值
        int result = -310007;
        try {
            // 设置用户账号信息
            result = ConfigManager.setAccountInfo(userid, password, priority,
                    ipAddress1, ipAddress2, ipAddress3, ipAddress4);
            // 判断返回结果，0设置成功，否则失败
            if (result == 0) {
                System.out.println("设置用户账号信息成功！");
            } else {
                System.out.println("设置用户账号信息失败，错误码：" + result);
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
        }
    }

    /**
     * 移除用户账号
     *
     * @param userid
     *            用户账号
     */
    public static void removeAccount(String userid) {
        try {
            // 调用移除用户账号的方法
            int result = ConfigManager.removeAccount(userid);

            // 返回0，代表移除成功
            if (result == 0) {
                System.out.println("移除用户账号[" + userid + "]成功！");
            }
            // 返回非0，代表移除失败
            else {
                System.out.println("移除用户账号[" + userid + "]失败！错误码：" + result);
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
        }
    }

    public String getThree(){
        Random rad=new Random();
        return rad.nextInt(1000000)+"";
    }
    /**
     *
     * @description 单条发送
     * @param
     *

     */
    public String singleSend(String num) {
        String code = getThree();
        try {

            SmsSendConn smsSendConn = mainFun();
            String userid = "E103LW";
            // 参数类
            Message message = new Message();
            // 设置用户账号 指定用户账号发送，需要填写用户账号，不指定用户账号发送，无需填写用户账号
            message.setUserid(userid);
            // 设置手机号码 此处只能设置一个手机号码
            message.setMobile(num);
            //18946655315
            // 设置内容
            message.setContent("您的验证码是验证码："+code+"，打死都不要告诉别人哦！请于10分钟内正确输入，如非本人操作，请忽略此短信。");
            // 设置扩展号
            //message.setExno("11");
            // 用户自定义流水编号
            message.setCustid("20160929194950100001");
            // 自定义扩展数据
            //message.setExdata("abcdef");
            // 业务类型
            message.setSvrtype("SMS001");

            // 返回的流水号
            StringBuffer returnValue = new StringBuffer();
            // 返回值
            int result = -310099;
            // 发送短信
            result = smsSendConn.singleSend(message, returnValue);
            // result为0:成功
            if (result == 0) {
                System.out.println("单条发送提交成功！");
                System.out.println(returnValue.toString());
            }
            // result为非0：失败
            else {
                System.out.println("单条发送提交失败,错误码：" + result);
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
        }
        return code;
    }


    /**
     *
     * @description 查询余额
     * @param smsSendConn
     *            短信处理对象,在这个方法中调用查询余额功能
     * @param userid
     *            用户账号
     */
    public static void getBalance(SmsSendConn smsSendConn, String userid) {
        // 返回值
        int result = -310099;
        try {
            // 调用查询余额的方法查询余额
            result = smsSendConn.getBalance(userid);
            // 返回值大于等于0，则查询成功， 否则失败
            if (result >= 0) {
                System.out.println("查询余额成功，余额为：" + result);
            } else {
                System.out.println("查询余额失败，错误码为：" + result);
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
        }

    }

    /**
     * 查询剩余金额或条数接口
     */
    public static void getRemains(SmsSendConn smsSendConn,String userid)
    {
        try
        {
            // 查询余额接口。
            Remains remains = smsSendConn.getRemains(userid);

            //remains不为空
            if(remains!=null)
            {
                //查询成功
                if(remains.getResult()==0)
                {
                    //计费类型为0，条数计费
                    if(remains.getChargetype()==0)
                    {
                        System.out.println("查询成功,剩余条数为：" + remains.getBalance());
                    }else if(remains.getChargetype()==1)
                    {
                        //计费类型为1，金额计费
                        System.out.println("查询成功,剩余金额为：" + remains.getMoney());
                    }else
                    {
                        System.out.println("未知的计费类型,计费类型:"+remains.getChargetype());
                    }
                }
                else
                {
                    //查询失败
                    System.out.println("查询失败,错误码为：" + remains.getResult());
                }
            }else
            {
                System.out.println("查询失败。");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
