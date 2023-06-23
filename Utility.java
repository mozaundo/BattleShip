public class Utility {
    public static boolean CheckCords(String cords) {
        if (cords.length() != 2)
            return false;
            
        int letterNum;
        int num;
        
        try {
            char letter = cords.charAt(0);
            letterNum = Character.toLowerCase(letter) - 'a' + 1;
            num = Character.getNumericValue(cords.charAt(1));
        }
        catch(Exception e) {
            return false;
        }
        
        if (letterNum < 0 || num < 0) {
            return false;
        }
        else if (letterNum > 8 || num > 8) {
            return false;
        }
        
        return true;
    }
    
    public static String ConvertTime(String time) {
        String temp = time.substring(2);
        temp = temp.substring(0, temp.indexOf(".") + 1);
        return temp;
    }
}