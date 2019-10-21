package com.celfocus.llapbenchmark;

class Test {

    private int id;
    private String test_description;
    private String sql_query;
    private String sql_count;
    private boolean llap_enabled;

    Test(int id, String test_description, String sql_query, String sql_count, boolean llap_enabled) {
        this.id = id;
        this.test_description = test_description;
        this.sql_query = sql_query;
        this.sql_count = sql_count;
        this.llap_enabled = llap_enabled;
    }

    int getId() {
        return id;
    }

    String getTest_description() {
        return test_description;
    }

    String getSql_query() {
        return sql_query;
    }

    String getSql_count() {
        return sql_count;
    }

    boolean isLlap_enabled() {
        return llap_enabled;
    }
}
