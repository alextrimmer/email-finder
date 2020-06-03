import edu.depaul.email.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

public class PageFetcherTest {

    @Test
    @DisplayName("Get returns the contents of a valid file.")
    void goodGetTest() {
        PageFetcher pf = new PageFetcher();
        assertTrue(pf.get("/Users/a.trimmer/Downloads/email-finder-master/testfiles/goodlinktest.txt") != null);
    }

    @Test
    @DisplayName("Throws error when link is bad.")
    void badGetTest() {
        PageFetcher pf = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> pf.get("/Users/a.trimmer/Downloads/email-finder-master/testfiles/naughty.txt"));
    }

    @Test
    @DisplayName("Get returns the contents of a valid URL.")
    void getURLTest() {
        PageFetcher pf = new PageFetcher();
        assertTrue(pf.get("http://www.google.com") != null);
    }

    @Test
    @DisplayName("Get returns the contents of an invalid URL.")
    void badURLTest() {
        PageFetcher pf = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> pf.get("http://www.sadklfa24239adsfa.com"));
    }

    @Test
    @DisplayName("GetString returns the contents of a valid URL.")
    void goodGetStringTest() {
        PageFetcher pf = new PageFetcher();
        assertTrue(pf.getString("http://www.google.com") != null);
    }

    @Test
    @DisplayName("Throws error when getString reaches an invalid URL.")
    void badGetStringTest() {
        PageFetcher pf = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> pf.getString("http://www.sadklfa24239adsfa.com"));
    }

    @ParameterizedTest
    @MethodSource("provideInput")
    @DisplayName("A parameterized test with multiple valid URLs")
    void paramTest(String url) {
        PageFetcher pf = new PageFetcher();
        assertTrue(pf.getString(url) != null);
    }
    private static Stream<Arguments> provideInput() {
        return Stream.of(
                    //           tot  method   state  expected
                    Arguments.of("http://www.google.com"),
                    Arguments.of("http://www.gmail.com")
            );
    }

    @ParameterizedTest
    @MethodSource("provideInput2")
    @DisplayName("A parameterized test with multiple valid URLs")
    void paramTest2(String url) {
        PageFetcher pf = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> pf.getString(url));
    }
    private static Stream<Arguments> provideInput2() {
        return Stream.of(
                //           tot  method   state  expected
                Arguments.of("http://www.this1is2not3a4web5site6.com"),
                Arguments.of("http://www.KappavsPogChampvsKreygasm.com")
        );
    }

    @Test
    @DisplayName("A timed test that should pass.")
    void timeoutTest() {
        PageFetcher pf = new PageFetcher();
        assertTimeout(Duration.ofMillis(10000000), () -> pf.getString("http://www.google.com"));
    }

    @Test
    @DisplayName("A timed test that should fail.")
    void timeoutTest2() {
        PageFetcher pf = new PageFetcher();
        assertTimeout(Duration.ofMillis(10), () -> pf.getString("http://www.google.com"));
    }

}

