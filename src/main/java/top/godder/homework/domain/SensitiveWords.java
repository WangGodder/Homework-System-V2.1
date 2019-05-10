package top.godder.homework.domain;

import javax.persistence.Id;

public class SensitiveWords {
    @Id
    private String word;

    /**
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word
     */
    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }
}