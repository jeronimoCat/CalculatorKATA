import java.util.Scanner;

public class Main {
    public static int RomanToArab(String str) throws Exception {
        int res = 0;
        char[] chars = str.toCharArray();
        int buf = 0;
        int i = 0;
        for (int c = chars.length-1; c >= 0; c--)
        {
            switch (chars[c]) {
                case 'I' -> i = 1;
                case 'V' -> i = 5;
                case 'X' -> i = 10;
                case 'L' -> i = 50;
                case 'C' -> i = 100;
                case 'D' -> i = 500;
                case 'M' -> i = 1000;
                default -> throw new Exception("несуразица");
            }
            if (i > buf) res += i;
            else res -= i;
            buf = i;
        }

        return res;
    }

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            String expression = input.nextLine();

            int sign = expression.indexOf("-") + expression.indexOf("+") + expression.indexOf("/") + expression.indexOf("*") + 3;
            boolean flagRoman = (expression.contains("I") || expression.contains("V") || expression.contains("X") || expression.contains("C"));
            boolean flagArab = (expression.matches(".*\\d.*"));

            if (sign > 0) {
                if (flagArab && flagRoman) throw new Exception("несуразица");
                int operand1 = 0;
                int operand2 = 0;
                if (flagArab) {
                    operand1 = Integer.parseInt(expression.substring(0, sign - 1).strip());
                    operand2 = Integer.parseInt(expression.substring(sign + 2).strip());
                }
                if (flagRoman) {
                    operand1 = RomanToArab(expression.substring(0, sign - 1).strip());
                    operand2 = RomanToArab(expression.substring(sign + 2).strip());
                }
                if (expression.charAt(sign) == '-')
                    System.out.println(operand1 - operand2);
                if (expression.charAt(sign) == '+')
                    System.out.println(operand1 + operand2);
                if (expression.charAt(sign) == '/')
                    System.out.println(operand1 / operand2);
                if (expression.charAt(sign) == '*')
                    System.out.println(operand1 * operand2);
            } else System.out.println("строка не является математической операцией");
        }
        catch (NumberFormatException exception)
        {
            System.out.println("не суразица");
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }
    }
}