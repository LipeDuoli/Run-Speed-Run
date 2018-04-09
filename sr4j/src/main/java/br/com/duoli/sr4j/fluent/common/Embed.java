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
        },
        GENRES {
            @Override
            public String toString() {
                return "genres";
            }
        },
        DEVELOPERS {
            @Override
            public String toString() {
                return "developers";
            }
        },
        PUBLISHERS {
            @Override
            public String toString() {
                return "publishers";
            }
        },
        VARIABLES {
            @Override
            public String toString() {
                return "variables";
            }
        },
        ALL {
            @Override
            public String toString() {
                return LEVELS + "," +
                        CATEGORIES + "," +
                        PLATAFORMS + "," +
                        REGIONS + "," +
                        GENRES + "," +
                        DEVELOPERS + "," +
                        PUBLISHERS + "," +
                        VARIABLES;
            }
        }
    }

    public enum LeaderBoards {

        GAME {
            @Override
            public String toString() {
                return "game";
            }
        },
        CATEGORY {
            @Override
            public String toString() {
                return "category";
            }
        },
        LEVEL {
            @Override
            public String toString() {
                return "level";
            }
        },
        PLAYERS {
            @Override
            public String toString() {
                return "players";
            }
        },
        REGIONS {
            @Override
            public String toString() {
                return "regions";
            }
        },
        PLATAFORMS {
            @Override
            public String toString() {
                return "platforms";
            }
        },
        VARIABLES {
            @Override
            public String toString() {
                return "variables";
            }
        },
        ALL {
            @Override
            public String toString() {
                return GAME + "," +
                        CATEGORY + "," +
                        LEVEL + "," +
                        PLAYERS + "," +
                        REGIONS + "," +
                        PLATAFORMS + "," +
                        VARIABLES;
            }
        }
    }

    public enum Runs {
        GAME {
            @Override
            public String toString() {
                return "game";
            }
        },
        CATEGORY {
            @Override
            public String toString() {
                return "category";
            }
        },
        LEVEL {
            @Override
            public String toString() {
                return "level";
            }
        },
        REGION {
            @Override
            public String toString() {
                return "region";
            }
        },
        PLATAFORM {
            @Override
            public String toString() {
                return "platform";
            }
        },
        All {
            @Override
            public String toString() {
                return GAME + "," +
                        CATEGORY + "," +
                        LEVEL + "," +
                        REGION + "," +
                        PLATAFORM;
            }
        }
    }
}
