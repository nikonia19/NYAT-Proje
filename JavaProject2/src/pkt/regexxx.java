/**
*
* @author Ahmet Furkan Sogutcu / ahmet.sogutcu@ogr.sakarya.edu.tr
* @since 07/04/2024
* <p>
* regex kodlari ile analizlerin yapilmasi
* </p>
*/

package pkt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexxx {
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
    public static void dosyaGezme(List<File> files) {
    	
    	// dosyalar sirasi ile listeden okunup regex islemlerinin yapildigi fonksiyon calistiriliyor
        for (File file : files) {
        	StringBuilder contentBuilder = new StringBuilder();
        	try (BufferedReader reader = new BufferedReader(new FileReader(file))) {       	
                String line;
                // dosyalar sirasi ile okunarak stringe atilmasi saglanir ki regex islemleri yapilsin
                while ((line = reader.readLine()) != null) {
                	
                	contentBuilder.append(line).append("\n");
                }
                String packageContent = contentBuilder.toString();
                // package ile baslamayan dosyalari almaz
                Pattern packageP = Pattern.compile("package\\s+([a-zA-Z_]\\w*(?:\\.\\w+)*);");
            	Matcher packageM = packageP.matcher(packageContent);
                if (packageM.find()) {
            		dosyaOkuma(file);
                    System.out.println("-----------------------------------------");
            	}
        	} catch (IOException e) {
                e.printStackTrace();
            }
        	
        	
        }
    }

    private static void dosyaOkuma(File file) {
    	// analizi yapilacak degiskenler
    	int javadoc = 0; 	// javadoc yorum satiri
    	int yorum = 0;		// diger yorum satirlari
    	int kod = 0;		// kod satirlari (yorumlar ve bosluklar haric)
    	int LOC = 0;		// tum satir sayisi
    	int fonksiyon = 0;	// fonksiyon sayilari
    	
    	StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {       	
            String line;
            // dosyalar sirasi ile okunarak stringe atilmasi saglanir ki regex islemleri yapilsin
            while ((line = reader.readLine()) != null) {
            	LOC++;
            	contentBuilder.append(line).append("\n");
            }
            String content = contentBuilder.toString();
            
            // regex tasarimlari
        	Pattern javadocP = Pattern.compile("\\/\\*\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*\\/");	// javadoc satirlari
    		Pattern yorumP = Pattern.compile("(\\/\\/[^\\n\\r]+?(?:\\*\\)|[\\n\\r]))");	// 	// seklinde olan yorumlar
    		Pattern yorumPY = Pattern.compile("\\/\\*(?!\\*)(?:[^*]|\\*(?!\\/))*\\*\\/"); // /* */ seklinde olan yorumlar	
    		Pattern kodP = Pattern.compile("^(?!\\s*$)(?!\\s*\\/\\/)(?!\\s*\\/\\*)(?!\\s*\\*\\/)(?!\\s*\\*).+", Pattern.MULTILINE);
    		Pattern fonksiyonP = Pattern.compile("\\b(public|protected|private)?\\s+(\\w+\\s+)?\\w+\\s*\\([^)]*\\)\\s*(throws\\s+[\\w\\,]+\\s*)?\\{+\\{?");
    	
    		// matcher'lar ile regex ifadeleri kullanilarak istenilen yerler tespit ediliyor
    		// tespit islemi yapildiktan sonra da istenilen degerlerden ne kadar olduklari sayiliyor
    		Matcher javadocM = javadocP.matcher(content);
			while (javadocM.find()) {
				// sadece javadoc yorum satirinin aralari icin yazilmis kod
				String JDcommentBlock = javadocM.group();
    			String[] lines = JDcommentBlock.split("\\r?\\n");
    			for (String currentLine : lines) {
    	            if (currentLine.trim().startsWith("*") && !currentLine.trim().startsWith("*/")) {
    	            	javadoc++;
    	            }
    	        }
	        }
    		Matcher yorumM = yorumP.matcher(content);
    		while (yorumM.find()) {
    			yorum++;
            }
    		Matcher yorumYM = yorumPY.matcher(content);
    		while (yorumYM.find()) {
    			// regex ile bulunan satirlar arasinda tekrar gezerek *'li baslayan ifadeleri secer
    			String commentBlock = yorumYM.group();
    			String[] lines = commentBlock.split("\\r?\\n");
    			for (String currentLine : lines) {
    	            if (currentLine.trim().startsWith("*") && !currentLine.trim().startsWith("*/")) {
    	                yorum++;
    	            }
    	        }
            }
    		Matcher kodM = kodP.matcher(content);
    		while (kodM.find()) {
    			String kodCommentBlock = kodM.group();
    			String[] lines = kodCommentBlock.split("\\r?\\n");
    			for (String currentLine : lines) {
    				kod++;
    	        }
            }
    		Matcher fonksiyonM = fonksiyonP.matcher(content);
    		while (fonksiyonM.find()) {
    			fonksiyon++;
            }
    		
    		// degerlerin istenilen sekilde olmasi icin double casting yapildi
    		float kodF = kod;
    		double kodD = kodF;
    		float fonksiyonF = fonksiyon;
    		double fonksyonD = fonksiyonF;
    		
    		double yg = (javadoc + yorum)*0.8/fonksyonD;
    		double yh = (kodD/fonksyonD)*0.3;
    		
    		double yorumSapma = ((100*yg)/yh)-100;
    		
    		// istenilen ekran goruntusu
    		System.out.println("Sinif: "+ file.getName());
			System.out.println("Javadoc Satir Sayisi: "+ javadoc);
			System.out.println("Yorum Satir Sayisi: "+yorum);
			System.out.println("Kod Satir Sayisi: "+ kod);
			System.out.println("LOC: "+ LOC);
			System.out.println("Fonksiyon Sayisi: "+ fonksiyon);
			System.out.println("Yorum Sapma Yuzdesi: "+  df.format(yorumSapma));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
