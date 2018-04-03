package br.com.duoli.sr4j.fluent.common;

public class Embed {

    private  Embed() {
    }

    public enum Games {

        LEVELS {
            @Override
            public String toString() {
                return "levels";
            }
        },
        CATEGORIES {
            @Override
            public String toString() {
                return "categories";
            }
        }
    }
}
