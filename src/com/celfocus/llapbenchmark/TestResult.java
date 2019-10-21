package com.celfocus.llapbenchmark;

import java.sql.Timestamp;
import java.util.ArrayList;

class TestResult {

    private int measures;
    private double first_measure;
    private double last_measure;
    private double max_measure;
    private double min_measure;
    private double avg_measure;
    private Test related_test;
    private Timestamp test_ts;

    TestResult( ArrayList<Double> results, Test test ) {
        this.test_ts = new Timestamp(System.currentTimeMillis());
        this.related_test = test;
        this.measures = results.size();
        this.first_measure = results.get(0);
        this.last_measure = results.get(results.size()-1);

        double min_val = Double.MAX_VALUE, max_val = 0, arr_sum = 0;
        for (double reads: results) {
            if (reads > max_val) max_val = reads;
            if (reads < min_val) min_val = reads;
            arr_sum+=reads;
        }
        this.max_measure = max_val;
        this.min_measure = min_val;
        this.avg_measure = arr_sum/results.size();
    }

    int getMeasures() {
        return measures;
    }

    double getFirst_measure() {
        return first_measure;
    }

    double getLast_measure() {
        return last_measure;
    }

    double getMax_measure() {
        return max_measure;
    }

    double getMin_measure() {
        return min_measure;
    }

    double getAvg_measure() {
        return avg_measure;
    }

    String getResultCSVLine() {
        return test_ts + "," + related_test.getId() + "," + related_test.getTest_description() + "," + related_test.isLlap_enabled() + "," + measures + "," + first_measure + "," + last_measure + "," + max_measure + "," + min_measure + "," + avg_measure;
    }
}
