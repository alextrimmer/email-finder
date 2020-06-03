import edu.depaul.email.*;

import static edu.depaul.email.StorageService.StorageType.EMAIL;
import static edu.depaul.email.StorageService.StorageType.GOODLINKS;
import static edu.depaul.email.StorageService.StorageType.BADLINKS;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StorageServiceTest {

    @Test
    @DisplayName("Tests to see if an error is thrown when a bad file path is visited")
    void storTest() {
        StorageService x = new StorageService();
        x.addLocation(GOODLINKS, "/badfolder/randomfile.txt ");

        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
        try {
            x.storeList(GOODLINKS, list);
        } catch (Exception e) {
            System.out.println("Sorry, this is not a valid file path.");
            assertThrows(EmailFinderException.class, () -> x.storeList(GOODLINKS, list));
        }

    }

    @Test
    @DisplayName("Tests if type EMAIL is functioning")
    void storTest2() throws IOException {
        StorageService x = new StorageService();
        x.addLocation(StorageService.StorageType.EMAIL, "email.txt");

        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
        x.storeList(StorageService.StorageType.EMAIL, list);

        BufferedReader z = new BufferedReader(new FileReader("email.txt"));
        int count = 0;
        while (z.readLine() != null) {
            count++;
        }
        assertEquals(6, count);

    }

    @Test
    @DisplayName("Tests if type GOODLINKS is functioning")
    void storTest3() throws IOException {
        StorageService x = new StorageService();
        x.addLocation(StorageService.StorageType.GOODLINKS, "good-links.txt");

        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
        x.storeList(StorageService.StorageType.GOODLINKS, list);

        BufferedReader z = new BufferedReader(new FileReader("good-links.txt"));
        int count = 0;
        while (z.readLine() != null) {
            count++;
        }
        assertEquals(6, count);

    }

    @Test
    @DisplayName("Tests if type GOODLINKS is functioning")
    void storTest4() throws IOException {
        StorageService x = new StorageService();
        x.addLocation(StorageService.StorageType.BADLINKS, "badlinks.txt");

        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
        x.storeList(StorageService.StorageType.BADLINKS, list);

        BufferedReader z = new BufferedReader(new FileReader("badlinks.txt"));
        int count = 0;
        while (z.readLine() != null) {
            count++;
        }
        assertEquals(6, count);

    }


}
