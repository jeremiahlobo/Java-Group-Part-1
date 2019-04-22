package Base;

public class Validator {

    public static boolean matchString(String input)
    {
        if(input.matches("^[a-zA-Z]+$"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
