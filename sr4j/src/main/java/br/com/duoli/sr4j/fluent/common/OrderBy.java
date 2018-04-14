package br.com.duoli.sr4j.fluent.common;

public class OrderBy {

    private OrderBy() {
    }

    public enum Games {
        NAME_INT {
            @Override
            public String toString() {
                return "name.int";
            }
        },
        NAME_JAP {
            @Override
            public String toString() {
                return "name.jap";
            }
        },
        ABBREVIATION {
            @Override
            public String toString() {
                return "abbreviation";
            }
        },
        RELEASED {
            @Override
            public String toString() {
                return "released";
            }
        },
        CREATED {
            @Override
            public String toString() {
                return "created";
            }
        },
        SIMILARITY {
            @Override
            public String toString() {
                return "similarity";
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
        PLATFORM {
            @Override
            public String toString() {
                return "platform";
            }
        },
        REGION {
            @Override
            public String toString() {
                return "region";
            }
        },
        EMULATED {
            @Override
            public String toString() {
                return "emulated";
            }
        },
        DATE {
            @Override
            public String toString() {
                return "date";
            }
        },
        SUBMITED {
            @Override
            public String toString() {
                return "submitted";
            }
        },
        STATUS {
            @Override
            public String toString() {
                return "status";
            }
        },
        VERIFY_DATE {
            @Override
            public String toString() {
                return "verify-date";
            }
        }
    }
}
