package com.celfocus.llapbenchmark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HiveJDBC {

    private static ArrayList<Test> loadTestPlan(String filepath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filepath));
        ArrayList<Test> testList = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] testString = line.split("\\|");
            testList.add(new Test(Integer.valueOf(testString[0]),testString[1],testString[2], testString[3], Boolean.parseBoolean(testString[4])));
        }
        return testList;
    }

    private static ArrayList<String> loadHiveConfigs(String filepath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filepath));
        ArrayList<String> configList = new ArrayList<>();
        while (s.hasNextLine()) configList.add(s.nextLine());
        s.close();
        return configList;
    }

    private static void configureHiveSession(Statement stmt, ArrayList<String> configs) throws SQLException {
        for (String config : configs) {
            String result_print;
            String get_stmt = config.split("=")[0];
            ResultSet rs;

            //Get previous config
            rs = stmt.executeQuery(get_stmt);
            rs.next();
            result_print = String.valueOf(rs.getString(1));

            //Set new config
            stmt.execute(config);

            //Get new config
            rs = stmt.executeQuery(get_stmt);
            rs.next();
            String result = String.valueOf(rs.getString(1));
            if (result_print.equals(result))
                result_print = "[EQUAL]" + result_print;
            else
                result_print = "[CHANGED]" + result_print;
            result_print = result_print + " | " + result;

            System.out.println(result_print);
        }
    }

    private static TestResult runBenchmark(Test test, Statement stmt) throws SQLException {
        int test_iterations = 10;
        long startTime;
        double executionTime;
        int nbr_records = 0;
        ArrayList<Double> results = new ArrayList<>();
        ResultSet res;

        System.out.println("START TEST #"+test.getId()+": " + test.getTest_description());
        System.out.println("ITERATIONS: " + test_iterations + " LLAP_STATUS: "+test.isLlap_enabled()+": Query ("+test.getSql_query()+")");

        if (test.isLlap_enabled())
            stmt.execute("set hive.llap.execution.mode=all");
        else
            stmt.execute("set hive.llap.execution.mode=none");

        for (int i = test_iterations; i > 0; i--) {
            startTime = System.nanoTime();
            res = stmt.executeQuery(test.getSql_query());
            if (res.next()) {
                executionTime = (System.nanoTime() - startTime)*0.000001;
                System.out.println("Test #"+test.getId()+" LLAP_STATUS: "+test.isLlap_enabled()+" ("+ i + "/" + test_iterations + ") | Execution time (ms): " + executionTime);
                results.add(executionTime);
            }
        }
        res = stmt.executeQuery(test.getSql_count());
        if (res.next()) {
            nbr_records = Integer.valueOf(res.getString(1));
        }

        TestResult newTestObej = new TestResult(results, test);

        System.out.println("RESULT #"+test.getId()+" LLAP_STATUS: "+test.isLlap_enabled()+": Records ["+ nbr_records +"] Measures: ["+ newTestObej.getMeasures() +"] First: ["+newTestObej.getFirst_measure()+"] Last: ["+newTestObej.getLast_measure()+"] Min: ["+newTestObej.getMin_measure()+"] Avg: ["+newTestObej.getAvg_measure()+"] Max: ["+newTestObej.getMax_measure()+"]\n");
        return newTestObej;
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException, UnsupportedEncodingException {

        String driverName = "com.amazon.hive.jdbc41.HS2Driver";
        String jdbc_url = "jdbc:hive2://localhost:10000/default";
        String jdbc_user = "hadoop";
        String result_file = "results.csv";
        String LLAPTestsFile = "com/celfocus/llapbenchmark/hivetests";
        String hiveConfigFile = "com/celfocus/llapbenchmark/hiveconfig";

        //Try to load Hive JDBC Driver from Classpath
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //Load hive configurations from a file
        System.out.println("STAGE #1: Get Hive session parameters.");
        ArrayList<String> hiveConfigs = loadHiveConfigs(hiveConfigFile);
        System.out.println("RESULT #1: Got a number of configurations: " + hiveConfigs.size() + "\n");

        //Get Connection Driver and Statement handler for Hive
        System.out.println("STAGE #2: Connect to Hive using JDBC driver ("+ driverName +") and URL (" + jdbc_url + ")");
        Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, "");
        Statement stmt = con.createStatement();
        System.out.println("RESULT #2: Connection is OK\n");

        //Configure hive client
        System.out.println("STAGE #3: Setting Hive session parameters.");
        configureHiveSession(stmt, hiveConfigs);
        System.out.println("RESULT #3: Hive Session parameters SET.\n\n");

        //Load testPlan
        System.out.println("STAGE #4: Loading Test Plans.");
        ArrayList<Test> testPlan = loadTestPlan(LLAPTestsFile);
        System.out.println("RESULT #4: Loaded a total of " + testPlan.size() + " tests.\n");

        //Create results file
        PrintWriter writer = new PrintWriter(result_file, "UTF-8");

        //Run testPlan
        TestResult test_result_handler;
        for (Test test: testPlan) {
            test_result_handler = runBenchmark(test,stmt);
            writer.println(test_result_handler.getResultCSVLine());
        }

        //Close handlers
        stmt.close();
        con.close();
        writer.close();
    }
}
