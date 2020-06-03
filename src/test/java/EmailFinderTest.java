import java.io.*;
import java.util.Scanner;

import edu.depaul.email.*;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmailFinderTest {

    @Test
    @DisplayName("Should find up to 2 e-mails when crawling Google.")
    void limitTest() {
        EmailFinder b = new EmailFinder();
        String[] c = {"http://www.google.com","2"};
        b.run(c);
        try {
            BufferedReader x = new BufferedReader(new FileReader("email.txt"));
            int count = 0;
            while (x.readLine() != null) {
                count++;
            }
            assertTrue(count == 2);
        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Should find one e-mail inside goodlinktest.txt.")
    void limit2Test() {
        EmailFinder b = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/goodlinktest.txt","1"};
        b.run(c);
        try {
            BufferedReader x = new BufferedReader(new FileReader("email.txt"));
            int count = 0;
            while (x.readLine() != null) {
                count++;
            }
            assertTrue(count == 1);
        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Should find an e-mail when it searches through goodlinktest.txt.")
    void goodTest() {
        EmailFinder b = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/goodlinktest.txt"};
        b.run(c);
        try {
            File file = new File("email.txt");

            Scanner x = new Scanner(file);

            assertEquals(x.next(), "iworkhere@google.com");

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Should find no e-mails, but find a valid link in validlinktest.txt.")
    void goodTest2() {
        EmailFinder b = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/validlinktest.txt"};
        b.run(c);
        try {
            BufferedReader x = new BufferedReader(new FileReader("email.txt"));
            int count = 0;
            while (x.readLine() != null) {
                count++;
            }
            assertEquals(0, count);

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Should find multiple e-mails when it looks through multEmails.txt.")
    void multTest() {
        EmailFinder b = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/multEmails.txt"};
        b.run(c);
        try {
            BufferedReader x = new BufferedReader(new FileReader("email.txt"));
            int count = 0;
            while (x.readLine() != null) {
                 count++;
            }
            assertEquals(2, count);

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Checks bad path for an e-mail; should find nothing but the line of the path.")
    void badTest() {
        EmailFinder a = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/badfilepath.txt"};
        a.run(c);
        try {
            BufferedReader x = new BufferedReader(new FileReader("badlinks.txt"));
            int lines = 0;
            while (x.readLine() != null) lines++;
            assertEquals(1, lines);
        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
            assertThrows(EmailFinderException.class, () -> a.run(c));
        }

    }

    @Test
    @DisplayName("Should find an e-mail when it searches through goodlinktest.txt.")
    void goodMainyTest() {
        EmailFinder b = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/goodlinktest.txt"};
        b.main(c);
        try {
            File file = new File("email.txt");

            Scanner x = new Scanner(file);

            assertEquals(x.next(), "iworkhere@google.com");

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Should find no e-mails, but find a valid link in validlinktest.txt.")
    void goodMainyTest2() {
        EmailFinder b = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/validlinktest.txt"};
        b.run(c);
        try {
            BufferedReader x = new BufferedReader(new FileReader("email.txt"));
            int count = 0;
            while (x.readLine() != null) {
                count++;
            }
            assertEquals(0, count);

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Should find multiple e-mails when it looks through multEmails.txt.")
    void mainMultTest() {
        EmailFinder b = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/multEmails.txt"};
        b.run(c);
        try {
            BufferedReader x = new BufferedReader(new FileReader("email.txt"));
            int count = 0;
            while (x.readLine() != null) {
                count++;
            }
            assertEquals(2, count);

        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
        }

    }

    @Test
    @DisplayName("Checks bad path for an e-mail; should find nothing but the line of the path.")
    void badMainTest() {
        EmailFinder a = new EmailFinder();
        String[] c = {"/Users/a.trimmer/Downloads/email-finder-master/testfiles/badfilepath.txt"};
        a.run(c);
        try {
            BufferedReader x = new BufferedReader(new FileReader("badlinks.txt"));
            int lines = 0;
            while (x.readLine() != null) lines++;
            assertEquals(1, lines);
        } catch (Exception e) {
            System.out.println("Sorry, your file could not be found.");
            assertThrows(EmailFinderException.class, () -> a.run(c));
        }

    }




}
