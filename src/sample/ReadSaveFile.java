package sample;

import java.io.*;

public class ReadSaveFile {
    public static String readFile(File namefile){
        StringBuilder reader = new StringBuilder();
        try (FileReader file = new FileReader(namefile)){
            int c;
            while ((c=file.read())!=-1){
                reader.append((char) c);
            }
            return reader.toString();
        }catch (FileNotFoundException e) {
            System.out.println("Такой файл не найден!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader.toString();
    }

    public static void saveFile(File nameFile, String string){
        try (FileWriter writer = new FileWriter(nameFile)){
            writer.write(string);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Такая файл не найден!");
        }
    }
}
