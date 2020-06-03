import edu.depaul.email.*;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class PageParserTest {

    @Test
    @DisplayName("Checks for no e-mails")
    void emptyTest() {
        PageParser pp = new PageParser();
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/testfiles/validlinktest.txt");
        assertEquals(0, pp.findEmails(doc).size());
    }

    @Test
    @DisplayName("Checks for one e-mail")
    void oneTest() {
        PageParser pp = new PageParser();
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/testfiles/goodlinktest.txt");
        assertEquals(1, pp.findEmails(doc).size());
    }

    @Test
    @DisplayName("Checks for multiple e-mails")
    void multTest() {
        PageParser pp = new PageParser();
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/testfiles/multEmails.txt");
        assertEquals(2, pp.findEmails(doc).size());
    }

    @Test
    @DisplayName("Checks to see if a tagless link is found")
    void tagTest() {
        PageParser pp = new PageParser();
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/notags.html");
        assertFalse(pp.findLinks(doc).size() == 1);
    }

    @Test
    @DisplayName("Checks to see if a tagged link is found")
    void tagTest2() {
        PageParser pp = new PageParser();
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/onetag.html");
        assertTrue(pp.findLinks(doc).size() == 1);
    }

    @Test
    @DisplayName("Checks to see if more than one tagged link is found")
    void tagTest3() {
        PageParser pp = new PageParser();
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/twotags.html");
        assertTrue(pp.findLinks(doc).size() == 2);
    }

    @Test
    @DisplayName("Checks relative tags")
    void tagTest4() {
        PageParser pp = new PageParser();
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/reltag.html");
        assertFalse(pp.findLinks(doc).size() == 1);
    }



}
