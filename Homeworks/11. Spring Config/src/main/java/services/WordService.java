package services;

import helpers.WordChecker;
import helpers.WordReader;
import helpers.WordWriter;

public class WordService {

    private final WordChecker wordChecker;
    private final WordWriter wordWriter;
    private final WordReader wordReader;

    public WordService(final WordChecker wordChecker, final WordWriter wordWriter, final WordReader wordReader) {
        this.wordChecker = wordChecker;
        this.wordWriter = wordWriter;
        this.wordReader = wordReader;
    }

    public String getStatus() {
        return String.format("WordService: %s + %s + %s",
                this.wordReader.ping(), this.wordChecker.ping(), this.wordWriter.ping());
    }
}
