package Base;

import java.awt.*;

public class Validator {

    TextField i=null;

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
        if(input.matches("^[a-zA-Z0-9_ ]*$"))
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
        if(input.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Boolean textFieldnotEmpty(javafx.scene.control.TextField i)
    {
        boolean r = false;
        if(i.getText() != null && !i.getText().isEmpty())
        {
           r = true;
        }
        return r;
    }

    public static Boolean textFieldnotEmpty(javafx.scene.control.TextField i, javafx.scene.control.Label l, String sValidationText) {
        boolean r = true;
        String c = null;
        if(!textFieldnotEmpty(i)){
            r = false;
            c = sValidationText;

        }
        l.setText(c);
        return r;
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
