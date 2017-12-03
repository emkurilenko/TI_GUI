package sample;

public class CeaserCipher {
    final private static String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    final private static String eng = "abcdefghijklmnopqrstuvwxyz";

    private static int rusKey(int step){
        int rusKey = step;
        if(step > 33){
            rusKey = rusKey%33;
        }else if(step<0){
            rusKey = (rusKey%33)+33;
        }
        return rusKey;
    }

    private static int engKey(int step){
        int engKey = step;
        if(step > 26){
            engKey = engKey%26;
        }else if(step < 0){
            engKey = (engKey%26)+26;
        }
        return engKey;
    }

    public static String encrypt(String str, int key){
        return crypt(str,key,true);
    }

    public static String decrypt(String str, int key){
        return crypt(str,key,false);
    }

    private static String crypt(String str, int key, boolean code){
       StringBuilder builder = new StringBuilder();
       for (int i = 0; i < str.length();i++){
           Character ch = str.charAt(i);
           if(Character.isLetter(ch)){
               if(code) {
                   if (rus.toUpperCase().contains(ch.toString()) || rus.contains(ch.toString())) {
                       builder.append(encrypt_shift(ch, rusKey(key), rus));
                   }
                   else if (eng.toUpperCase().contains(ch.toString()) || eng.contains(ch.toString())) {
                       builder.append(encrypt_shift(ch, engKey(key), eng));

                   }
               }else if(!code){
                   if (rus.toUpperCase().contains(ch.toString()) || rus.contains(ch.toString())) {
                       builder.append(decrypt_shift(ch, rusKey(key), rus));
                   }
                   else if (eng.toUpperCase().contains(ch.toString()) || eng.contains(ch.toString())) {
                       builder.append(decrypt_shift(ch, engKey(key), eng));
                   }
               }
           }else
               builder.append(ch);
       }
       return builder.toString();
    }

    private static Character encrypt_shift(Character ch,int key, String alp){
        if(Character.isUpperCase(ch)){
            return alp.toUpperCase().charAt((alp.toUpperCase().indexOf(ch)+key)%alp.length());
        }else {
            return alp.charAt((alp.indexOf(ch)+key)%alp.length());
        }
    }

    private static Character decrypt_shift(Character ch,int key, String alp){
        if(Character.isUpperCase(ch)){
           return Character.toUpperCase(alp.charAt((alp.toUpperCase().indexOf(ch)-key+alp.length())%alp.length()));
        }else {
            return alp.charAt((alp.indexOf(ch)-key+alp.length())%alp.length());
        }
    }
}
