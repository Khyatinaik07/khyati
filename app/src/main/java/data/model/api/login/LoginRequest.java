package data.model.api.login;

public class LoginRequest {

    private String mobilenumber = "";
    private String password = "";

    public String getPhoneno()
    {
        return mobilenumber;
    }
    public void setPhoneno(String phoneno)
    {
        this.mobilenumber= phoneno;
    }

    public String getPssword()
    {
        return password;
    }
    public void setPssword(String pssword)
    {
        this.password = pssword;
    }

}
