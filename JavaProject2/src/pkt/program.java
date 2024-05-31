/**
*
* @author Ahmet Furkan Sogutcu / ahmet.sogutcu@ogr.sakarya.edu.tr
* @since 07/04/2024
* <p>
* repository alinip klonlama islemlerinin yapilmasi
* </p>
*/

package pkt;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class program {

	public static void main(String[] args) {
		
		// Repository linki alinarak repositoryUrl string'ine tanimlaniyor
		Scanner scanner = new Scanner(System.in);
        System.out.print("Github Repository Linki Giriniz: ");
        String repositoryLink = scanner.nextLine();
	
        // src klasorunun hemen yanina repositorynin klonlanacagi yer olan clone isimli klasor olusturulur
        String dir = System.getProperty("user.dir");
        String hedefKlasor = Paths.get(dir, "clone").toString();
        File cloneDir = new File(hedefKlasor);
        file.klasorSil(cloneDir);  // dosya olusturma islemi yapilirken daha onceden bulunan klasor siliniyor
        cloneDir.mkdirs();

        // input olarak alinan repository linki ile hedeflenen klasore klonlama islemi gerceklestiriliyor
        file.repoKlon(repositoryLink, hedefKlasor);

        // okunacak dosyalara rahat bir sekilde ulasabilmek icin liste yapisi kullanildi
        List<File> javaDosya = file.javaBul(new File(hedefKlasor));

        // FileProcessor sinifini kullanarak dosyalari isle
        regexxx.dosyaGezme(javaDosya);
        
        // https://github.com/mfadak/Odev1Ornek.git
	}
}

