package sample;

public class VigenereCipher {
    final static String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    final static String eng = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String text, String key){
        return crypt(text,key,true);
    }

    public static String decrypt(String text, String key){
        return crypt(text,key,false);
    }


    private static String crypt(String text, String key,boolean code){
        StringBuilder builder = new StringBuilder();
        int keyArray[] = key(text.length(),key);
        int count = 0;
        for (int i = 0; i <text.length();i++){
            Character ch = text.charAt(i);
            if (Character.isLetter(ch)){
                if(code){
                    if (rus.toUpperCase().contains(ch.toString()) || rus.contains(ch.toString())) {
                        builder.append(encrypt_shift(ch, keyArray[i-count], rus));
                    }
                    else if (eng.toUpperCase().contains(ch.toString()) || eng.contains(ch.toString())) {
                        builder.append(encrypt_shift(ch, keyArray[i-count], eng));
                    }
                }else if(!code){
                    if (rus.toUpperCase().contains(ch.toString()) || rus.contains(ch.toString())) {
                        builder.append(decrypt_shift(ch, keyArray[i-count], rus));
                    }
                    else if (eng.toUpperCase().contains(ch.toString()) || eng.contains(ch.toString())) {
                        builder.append(decrypt_shift(ch, keyArray[i-count], eng));
                    }
                }
            }else{
                builder.append(ch);
                count++;
            }
        }
        return builder.toString();
    }

    private static int[] key(int length, String key){
        key = key.toLowerCase();
        int keys[] = new int[length];
        int k =0;
        if(rus.contains(key.charAt(0)+"")){
            do{
                Character ch = key.charAt(k%key.length());
                keys[k] = rus.indexOf(ch);
                k++;
            }while (k!=length);
        }else if(eng.contains(key.charAt(0)+"")){
            do{
                Character ch = key.charAt(k%key.length());
                keys[k] = eng.indexOf(ch);
                k++;
            }while (k!=length);
        }
        return keys;
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
