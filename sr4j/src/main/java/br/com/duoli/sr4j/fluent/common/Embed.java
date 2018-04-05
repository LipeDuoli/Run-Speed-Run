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

    public enum LeaderBoards {

        PLAYERS {
            @Override
            public String toString() {
                return "players";
            }
        },
        VARIABLES {
            @Override
            public String toString() {
                return "variables";
            }
        },
        PLATAFORMS {
            @Override
            public String toString() {
                return "platforms";
            }
        },
        REGIONS {
            @Override
            public String toString() {
                return "regions";
            }
        }
    }
}
