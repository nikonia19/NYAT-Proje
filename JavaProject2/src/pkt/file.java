/**
*
* @author Ahmet Furkan Sogutcu / ahmet.sogutcu@ogr.sakarya.edu.tr
* @since 07/04/2024
* <p>
* dosya klonlama, dosyalarin okunup listelenmesi ve dosya silme
* </p>
*/

package pkt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class file {
	public static List<File> javaBul(File folder) {
        List<File> javaFiles = new ArrayList<>(); // liste olusturuldu
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
        	// dosyalar sirasi ile okunarak java uzantili dosyalarin sesilip listeye eklenmesi saglaniliyor
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".java") && !file.getName().startsWith("I")) {
                    javaFiles.add(file); // listeye ekleme
                } else if (file.isDirectory()) {
                    javaFiles.addAll(javaBul(file)); // ozyineleme
                }
            }
        }
        return javaFiles;
    }
	
	public static void repoKlon(String repositoryLink, String hedefKlasor) {
		// processBuilder ile input olarak alinan link burada kullanilarak klonlama islemi gerceklestiriliyor	
		try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("git", "clone", repositoryLink, hedefKlasor);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            // System.out.println("\nExit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void klasorSil(File directory) {
    	// klasor tekrardan olusturulup ayni isimli klasorlerinin ust uste binmesini engellemek icin
    	// daha onceden varolan klasor temizlenip yenisi olusturulmak icin bu islemler yapilir
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
            	klasorSil(file);
            }
        }
        directory.delete();
    }
}

