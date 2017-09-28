package org.lsd.animdemos.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shaodongPC on 2017/9/28.
 */

public class StringUtils
{
    /**
     * 检查是否符合手机号码格式
     *
     * @param phoneNum
     * @return
     */
    public static boolean checkPhoneNum(String phoneNum)
    {
        /*
         * 手机号码段规则
         * 13段：130、131、132、133、134、135、136、137、138、139
         * 14段：145、147
         * 15段：150、151、152、153、155、156、157、158、159
         * 17段：170、176、177、178
         * 18段：180、181、182、183、184、185、186、187、188、189
         */
        final String regx = "^((13[0-9])|14[57]|(15[^4,\\D])|17[0678]|(18[0-9]))\\d{8}$";

        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(phoneNum);

        return m.matches();
    }

    /**
     * 验证Email
     *
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email)
    {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }
}
