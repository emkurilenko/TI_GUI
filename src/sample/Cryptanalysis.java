package sample;

public class Cryptanalysis {
    final private static String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    final private static String eng = "abcdefghijklmnopqrstuvwxyz";

    public static int cryptKeyRus(String text){
        return cryptKey(text,rus);
    }

    public static int cryptKeyEng(String text){
        return cryptKey(text,eng);
    }

    public static String methodICKeyRus(String text){
        return methodIC(text,rus);
    }

    public static String methodICKeyEng(String text){
        return methodIC(text,eng);
    }

    private static int cryptKey(String text, String alph){
        int[] freequency = new int[alph.length()];
        int posMax = 0;
        int key = 0;

        freequency = letterFrequency(text,alph);
        posMax = searchMaxPos(freequency);

        if(alph.contains("а")) //rus
            key = Math.abs((rus.length()+posMax-15)%rus.length()); //rukeys

        if(alph.contains("a")) //eng
            key = Math.abs((eng.length()+posMax-4)%eng.length());
        return key;
    }

    private static String methodIC(String encrypt, String alph){
        final double rusConstIS = 0.0553;
        final double engCountIS = 0.0644;
        encrypt = getOnlyLetters(encrypt,alph);
        int lengthPass = 0;
      /*Лучше начать цикл с 2, т.к. с 1 это криптоанализ Цезаря*/
        for(int i = 1; i< alph.length(); i++){
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < encrypt.length();j+=i)
                stringBuilder.append(encrypt.charAt(j));
            if(alph.contains("а")){ //rus
              //  System.out.println(calcIC(stringBuilder.toString(),alph));
                if(Math.abs(calcIC(stringBuilder.toString(),alph))>=rusConstIS) {
                    lengthPass = i;
                    break;
                }
            }

            if(alph.contains("a")){ //eng
              //  System.out.println(calcIC(stringBuilder.toString(),alph));
                if(Math.abs(calcIC(stringBuilder.toString(),alph))>=engCountIS) {
                    lengthPass = i;
                    break;
                }
            }

        }
        String[] str = new String[lengthPass];

        for (int i = 0; i <lengthPass; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = i; j < encrypt.length();j+=lengthPass){
                stringBuilder.append(encrypt.charAt(j));
            }
            str[i]=stringBuilder.toString();
        }

        String password = "";
        for (int i = 0; i <lengthPass; i++){
            if(alph.contains("а")) //rus
                password+=rus.charAt(cryptKeyRus(str[i]));
            if(alph.contains("a")) //eng
                password+=eng.charAt(cryptKeyEng(str[i]));
        }
        return password;
    }

    private static String getOnlyLetters(String s,String alph){
        StringBuilder textWithoutOnlyLettert = new StringBuilder();
        for (Character c:
             s.toCharArray()) {
            if(alph.contains(c.toString().toLowerCase()))
                textWithoutOnlyLettert.append(c);
        }
        return textWithoutOnlyLettert.toString();
    }

    private static int[] letterFrequency(String text,String alphablet){
        int[] frequency = new int[alphablet.length()];

        for(int i = 0; i < text.length(); i++){
             for (int j = 0; j < frequency.length;j++)
                if(alphablet.toUpperCase().charAt(j) == text.charAt(i) || alphablet.charAt(j) == text.charAt(i))
                    frequency[j]++;
        }
        return frequency;
    }

    private static double calcIC(String text,String alph){
        return calcIC(letterFrequency(text,alph));
    }

    private static double calcIC(int[] freequencies){
        double ic = 0;
        int sum = 0;
        for(int i=0; i < freequencies.length; i++){
            sum+=freequencies[i];
        }

        for (int i = 0; i < freequencies.length;i++){
            double top = freequencies[i]*(freequencies[i]-1);
            double bottom = sum * (sum-1);
            ic += top/bottom;
        }
        return ic;
    }

    private static int searchMaxPos(int[] arr){
        int max = 0;
        int pos = 0;
        for(int i = 0; i<arr.length;i++){
            if(max < arr[i]){
                max = arr[i];
                pos = i;
            }
        }
        return pos;
    }
}
