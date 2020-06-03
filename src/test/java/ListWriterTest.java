import java.io.*;
import java.util.Arrays;
import java.util.List;

import edu.depaul.email.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.Query;


public class ListWriterTest {

    @Test
    @DisplayName("Checks if list was written to goodlinks, on individual lines.")
    void goodTest() {
        try {

            OutputStream stream = new FileOutputStream("good-links.txt");

            ListWriter y = new ListWriter(stream);
            List<String> list = Arrays.asList("a", "b", "c", "d", "e");
            y.writeList(list);

            BufferedReader z = new BufferedReader(new FileReader("good-links.txt"));
            int count = 0;
            while (z.readLine() != null) {
                count++;
            }
            assertEquals(5, count);

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Checks if list was written to badlinks, on individual lines.")
    void badTest() {
        try {

            OutputStream stream = new FileOutputStream("badlinks.txt");

            ListWriter y = new ListWriter(stream);
            List<String> list = Arrays.asList("a", "b", "c");
            y.writeList(list);

            BufferedReader z = new BufferedReader(new FileReader("badlinks.txt"));
            int count = 0;
            while (z.readLine() != null) {
                count++;
            }
            assertEquals(3, count);

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Checks if list was written to email, on individual lines.")
    void emailTest() {
        try {

            OutputStream stream = new FileOutputStream("email.txt");

            ListWriter y = new ListWriter(stream);
            List<String> list = Arrays.asList("this@that", "this@you", "this@them","this@not");
            y.writeList(list);

            BufferedReader z = new BufferedReader(new FileReader("email.txt"));
            int count = 0;
            while (z.readLine() != null) {
                count++;
            }
            assertEquals(4, count);

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    /*
    @Test
    @DisplayName("When calculation is successful, returns value produced by server(")
    void testSuccessfulAdd() throws IOException {

        OutputStream stream = new FileOutputStream("email.txt");

        List<String> list = Arrays.asList("this@that", "this@you", "this@them","this@not");

        ListWriter mockServer = mock(ListWriter.class);
        when(mockServer.writeList(list);).thenReturn(ListWriter(stream));
        ListWriter client = new ListWriter(stream);
        String result = client.writeList(list);  // just to prove client isn't adding for itself
        assertEquals(result, mockServer);
    }

     */


}
