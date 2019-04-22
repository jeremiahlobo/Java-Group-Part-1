package Base;

public class Validator {

    /*
    private boolean isInt(TextField input, String message){
        try{
            int age = Integer.parseInt(input.getText());
            return true;
        }
        catch(NumberFormatException e)
        {
            System.out.println("Error: "+message + "is not a number");
            return false;
        }
    }
    private boolean isProvinceCode
            */

//your input, has to match province code


   /* private boolean isPostalCode(TextField input, String message) {
        try {
            var regex = /[A-Za-z]\d[A-Za-z]?\d[A-Za-z]\d/;
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + message + "is not valid postal code format. Please use format A1A 1A1");
            return false;
        }
    }*/

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
