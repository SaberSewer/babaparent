package online.cangjie.core.service.cob;

public interface SessionProvider {
    //把用户名入到 Redis中   30分钟
    public void setAttributeForUserName(String name,String value);

    //把验证码放到Redis中  10分钟
    public void setAttributeForCode(String name,String value);

    //取用户名
    public String getAttibuteForUserName(String name);

    //取验证码
    public String getAttibuteForCode(String name);
}
