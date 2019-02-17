package cryptographytask1;
/**
 *
 * @author Rob
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CryptographyTask1{

    //only works with ascii files, when £ or € are used, the file becomes unreadible
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Performs modulo on numbers in a text file, each line may contain two numbers only, "
                            + "\nanything else typed on any line will cause sed line to be ignored! "
                            + "\n\nPlease enter file name below...");
        String filename = keyboard.nextLine();

        try{
            Scanner myFile = new Scanner(new File(filename));
            int lineCount = 0;
            while (myFile.hasNextLine()){
                lineCount++;
                String line = myFile.nextLine();
                String[] data = line.split("\\s+");
                Pattern p = Pattern.compile("[a-zA-Z,.!?@#^&%£€_'~¬`\\=|+*/()<>{}]");
                Matcher m = p.matcher(line);
                    if (line.trim().length() == 0)continue;
                    if (m.find())continue;
                    if (data.length != 2)continue;
                    if (data.length == 2){
                        try{
                            long a = Long.parseLong(data[0]);
                            long b = Long.parseLong(data[1]);
                            System.out.println(getModulo(a, b));

                        }catch(NumberFormatException e){
                            System.out.println("An exception occurred while parsing numbers from line " + lineCount + " : " + line);
                        }
                    }else{
                        System.out.println("Line " + lineCount + " was not formatted properly: \"" + line + "\"");
                    }
                }
            }catch(FileNotFoundException fe){
                System.out.println("That file was not found in the system, sorry.");
            }
        }
    public static long getModulo(long a, long b){ 
        long remainder1 = a - ((a / b)* b);
        //remainder1 = Math.abs(remainder1);
        remainder1 = (remainder1 < 0 ? -remainder1 : remainder1);
        System.out.print(a + " mod " + b + " = ");
        return remainder1;
    }
//    public static boolean skipText(String[] data){
//        for (int i = 0; i < data.length; i++){
//            String check = data[i];
//            for (int j = 0; j < check.length(); j++){
//                if (check.charAt(i) == 'a') return true;
//            }
//        }
//        return false;
//   }
}
