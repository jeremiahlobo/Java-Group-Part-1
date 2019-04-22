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

    public static boolean matchEmail(String input)
    {
        if(input.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean matchPhoneNumber(String input)
    {
        if(input.matches("/\\(([0-9]{3})\\)([ .-]?)([0-9]{3})\\2([0-9]{4})|([0-9]{3})([ .-]?)([0-9]{3})\\5([0-9]{4})/"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean matchProvince(String input)
    {
        if(input.matches("^[A-Z]{2}$"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean matchPostalCode(String input)
    {
        if(input.matches("^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
