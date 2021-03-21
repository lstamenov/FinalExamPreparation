import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String barcode = scanner.nextLine();
            Pattern pattern = Pattern.compile("^@#+(?=[A-Z])([A-Za-z0-9]{6,})(?<=[A-Z])@#+$");
            Matcher matcher = pattern.matcher(barcode);

            if (matcher.find()){
                String validBarcode = matcher.group(1);
                String barcodeGroup = getProductGroup(validBarcode);
                System.out.println("Product group: " + barcodeGroup);
            }else {
                System.out.println("Invalid barcode");
            }
        }
    }

    private static String getProductGroup(String validBarcode) {
        if (containsDigit(validBarcode)){
            String group = "";
            for (int i = 0; i < validBarcode.length(); i++) {
                if (Character.isDigit(validBarcode.charAt(i))){
                    group += validBarcode.charAt(i);
                }
            }
            return group;
        }else {
            return "00";
        }
    }

    private static boolean containsDigit(String validBarcode) {
        for (int i = 0; i < validBarcode.length(); i++) {
            if (Character.isDigit(validBarcode.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
