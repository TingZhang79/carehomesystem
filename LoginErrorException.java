public class LoginErrorException extends  Exception{
    @Override
    public String getMessage() {
        return "Login Error";
    }
}
