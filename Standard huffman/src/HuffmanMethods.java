import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanMethods {
    void code(String text,String encodeFileName){
        HashMap<Character, Integer> characterInText = new HashMap();
        for (int i = 0; i < text.length(); i++) {
            if (characterInText.get(text.charAt(i)) != null) {
                characterInText.replace(text.charAt(i), characterInText.get(text.charAt(i)), characterInText.get(text.charAt(i)) + 1);
            } else {
                characterInText.put(text.charAt(i), 1);
            }
        }
        HashMap<String, Float> probability = new HashMap();
        for (char name : characterInText.keySet()) {
            probability.put(String.valueOf(name), ((float) (characterInText.get(name)) / text.length()));
        }
        HashMap<Character, String> table = new HashMap();
        for (String name: probability.keySet()) {
            table.put(name.charAt(0), "");
        }
        while(probability.size()>1) {
            String BeforeLastSymbol = null;
            double probabilityBeforeLastSymbol = 1.0;
            String LastSymbol = null;
            double probabilityLastSymbol = 1.0;
            for (String name : probability.keySet()) {
                if (probability.get(name) < probabilityLastSymbol) {
                    LastSymbol = name;
                    probabilityLastSymbol = probability.get(name);
                }
            }
            for (String name : probability.keySet()) {
                if (probability.get(name) < probabilityBeforeLastSymbol && LastSymbol != String.valueOf(name)) {
                    BeforeLastSymbol = name;
                    probabilityBeforeLastSymbol = probability.get(name);
                }
            }
            for (int i = 0; i < BeforeLastSymbol.length(); i++) {
                table.replace(BeforeLastSymbol.charAt(i),table.get(BeforeLastSymbol.charAt(i)),"0"+table.get(BeforeLastSymbol.charAt(i)));
            }
            for (int i = 0; i < LastSymbol.length(); i++) {
                table.replace(LastSymbol.charAt(i),table.get(LastSymbol.charAt(i)), "1"+table.get(LastSymbol.charAt(i)));
            }
            probability.remove(LastSymbol);
            probability.remove(BeforeLastSymbol);
            probability.put(LastSymbol + BeforeLastSymbol, (float) (probabilityLastSymbol + probabilityBeforeLastSymbol));
        }
        String compressionResult="";
        for (int i = 0; i < text.length(); i++) {
            compressionResult+=table.get(text.charAt(i));
        }
        System.out.println("###          -------------        encode        -------------           ###");
        System.out.println(compressionResult);
        for (char name: table.keySet()) {
            System.out.println(name +" "+ table.get(name));
        }
        int theOriginalSize = text.length()*8;
        int compressionSize = compressionResult.length();
        System.out.println("the original size is :" + theOriginalSize);
        System.out.println("the compression size is :"+compressionSize);
        //------------------------------------//
        try {
            FileWriter theFile = new FileWriter(encodeFileName);
            theFile.write(compressionResult+"\n");
            for (char name: table.keySet()) {
                theFile.write(table.get(name) +" "+ name+"\n");
            }
            GUI.setOrginal(  String.valueOf(theOriginalSize));
            GUI.setCompression(  String.valueOf(compressionSize));
            theFile.close();
            System.out.println("Successfully wrote to the file of encode.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //--------------------------------//

    }

    void decode(String readFileName ,String writeFileName) {
        System.out.println();
        System.out.println("###     --------------------             START DECODE          --------------------          ###");
        HashMap< String, String> table = new HashMap< String, String>();
        String code="";
        //read from file --------------------------------------------------------------------------
        try {
            File myObj = new File(readFileName);
            Scanner input = new Scanner(myObj);
            //first line have code
            code = input.next();
            System.out.println(code);
            while (input.hasNext()) {
                // read the table
                String bits = input.next();
                String symbol = input.next();
                table.put(bits,symbol);
                System.out.println(bits+"  "+symbol);
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //---------------------------------------------------------------------------------------
        int size =code.length();
        String s="",text="";
        for (int i=0;i<size;i++){
            s+=code.charAt(i);
            if(table.containsKey(s)){
                text+=table.get(s);
                s="";//to start new symbol
            }
        }
        System.out.println(text);
        //write the results in the txt file
        try {
            FileWriter theFile = new FileWriter(writeFileName);
            theFile.write("the text after decompression : \n");
            theFile.write("   -------->   "+text+ "  \n");
            //theFile.write("The original Size = "+theOriginal+ "  \n");
            //theFile.write("The compressed Size = "+theCompressSize+ "  \n");
            theFile.close();
            System.out.println("Successfully wrote to the file of decode.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
