import edu.depaul.email.*;

import static edu.depaul.email.StorageService.StorageType.EMAIL;
import static edu.depaul.email.StorageService.StorageType.GOODLINKS;
import static edu.depaul.email.StorageService.StorageType.BADLINKS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class PageCrawlerTest {

    @Test
    @DisplayName("Crawls a good link with one e-mail.")
    void emailTest() {
        StorageService storage = new StorageService();
        storage
                .addLocation(EMAIL, "email.txt")
                .addLocation(GOODLINKS, "good-links.txt")
                .addLocation(BADLINKS, "badlinks.txt");

        PageCrawler x = new PageCrawler(storage, 5);
        x.crawl("/Users/a.trimmer/Downloads/email-finder-master/testfiles/goodlinktest.txt");

        assertEquals(x.getEmails().size(), 1);

    }

    @Test
    @DisplayName("Crawls a good link with multiple e-mails.")
    void multEmailTest() {
        StorageService storage = new StorageService();
        storage
                .addLocation(EMAIL, "email.txt")
                .addLocation(GOODLINKS, "good-links.txt")
                .addLocation(BADLINKS, "badlinks.txt");

        PageCrawler x = new PageCrawler(storage, 5);
        x.crawl("/Users/a.trimmer/Downloads/email-finder-master/testfiles/multemails.txt");

        assertEquals(x.getEmails().size(), 2);

    }

    @Test
    @DisplayName("Crawls a good link with no e-mail.")
    void noEmailTest() {
        StorageService storage = new StorageService();
        storage
                .addLocation(EMAIL, "email.txt")
                .addLocation(GOODLINKS, "good-links.txt")
                .addLocation(BADLINKS, "badlinks.txt");

        PageCrawler x = new PageCrawler(storage, 5);
        x.crawl("/Users/a.trimmer/Downloads/email-finder-master/testfiles/validlinktest.txt");

        assertEquals(x.getEmails().size(), 0);

    }

    @Test
    @DisplayName("Goes to a bad link.")
    void badLinkTest() {
        StorageService storage = new StorageService();
        storage
                .addLocation(EMAIL, "email.txt")
                .addLocation(GOODLINKS, "good-links.txt")
                .addLocation(BADLINKS, "badlinks.txt");

        PageCrawler x = new PageCrawler(storage, 5);
        x.crawl("/Users/a.trimmer/Downloads/email-finder-master/testfiles/randombadname.txt");

        assertEquals(x.getBadLinks().size(), 1);

    }

    @Test
    @DisplayName("Crawls a good link with 6 e-mails when the max limit is 5 - currently fails.")
    void maxTest() {
        StorageService storage = new StorageService();
        storage
                .addLocation(EMAIL, "email.txt")
                .addLocation(GOODLINKS, "good-links.txt")
                .addLocation(BADLINKS, "badlinks.txt");

        PageCrawler x = new PageCrawler(storage, 5);
        x.crawl("/Users/a.trimmer/Downloads/email-finder-master/testfiles/sixemails.txt");

        assertFalse(x.getEmails().size() == 5);

    }

    @Test
    @DisplayName("Tests if loops infinitely when two urls lead back and forth to each other. If true, no loop.")
    void loopTest() {
        StorageService storage = new StorageService();
        storage
                .addLocation(EMAIL, "email.txt")
                .addLocation(GOODLINKS, "good-links.txt")
                .addLocation(BADLINKS, "badlinks.txt");

        PageCrawler x = new PageCrawler(storage, 5);
        x.crawl("/Users/a.trimmer/Downloads/email-finder-master/testfiles/loop1.txt");

        assertTrue(x.getGoodLinks().size() == 1);

    }

}
