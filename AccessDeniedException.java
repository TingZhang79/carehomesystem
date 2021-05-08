public class AccessDeniedException extends Exception{
    @Override
    public String getMessage() {
        return ("You have not authorized.");
    }
}
