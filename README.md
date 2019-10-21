
## Run EMR Slider LLAP Daemon

  Download pre-packaged AWS EMR LLAP Daemon

    aws s3 cp s3://aws-bigdata-blog/artifacts/Turbocharge_Apache_Hive_on_EMR/configure-Hive-LLAP.sh .
    ./configure-Hive-LLAP.sh


You can check the Slider App logs here:

    /tmp/llap_configure_script.log



## Configure benchmark

Setup your tests in file

    com/celfocus/llapbenchmark/hivetests

Setup your hive variables in file

    com/celfocus/llapbenchmark/hiveconfig


  See example schema definition in the provided files and adapt to your needs.
## Get Hive Driver for your environment


    wget http://awssupportdatasvcs.com/bootstrap-actions/Simba/AmazonHiveJDBC-1.0.9.1060.zip
    unzip AmazonHiveJDBC41-1.0.9.1060.zip


## Compile & Run benchmark app


    javac -classpath . com/celfocus/llapbenchmark/HiveJDBC.java
    java -classpath :* com/celfocus/llapbenchmark/HiveJDBC


## Example output


    STAGE #1: Get Hive session parameters.
    RESULT #1: Got a number of configurations: 57

    STAGE #2: Connect to Hive using JDBC driver (com.amazon.hive.jdbc41.HS2Driver) and URL (jdbc:hive2://localhost:10000/default)
    log4j:WARN No appenders could be found for logger (org.apache.thrift.transport.TSaslTransport).
    log4j:WARN Please initialize the log4j system properly.
    RESULT #2: Connection is OK

    STAGE #3: Setting Hive session parameters.
    [CHANGED]ambari.hive.db.schema.name is undefined | ambari.hive.db.schema.name=hive
    [CHANGED]fs.file.impl.disable.cache is undefined | fs.file.impl.disable.cache=true
    [CHANGED]fs.hdfs.impl.disable.cache is undefined | fs.hdfs.impl.disable.cache=true
    [EQUAL]hive.auto.convert.join.noconditionaltask=true | hive.auto.convert.join.noconditionaltask=true
    [EQUAL]hive.auto.convert.join=true | hive.auto.convert.join=true
    [CHANGED]hive.auto.convert.sortmerge.join=false | hive.auto.convert.sortmerge.join=true
    [EQUAL]hive.compactor.abortedtxn.threshold=1000 | hive.compactor.abortedtxn.threshold=1000
    [CHANGED]hive.compactor.check.interval=300s | hive.compactor.check.interval=300
    [EQUAL]hive.compactor.delta.num.threshold=10 | hive.compactor.delta.num.threshold=10
    [CHANGED]hive.compactor.delta.pct.threshold=0.1 | hive.compactor.delta.pct.threshold=0.1f
    [EQUAL]hive.compactor.initiator.on=false | hive.compactor.initiator.on=false
    [EQUAL]hive.compactor.worker.threads=0 | hive.compactor.worker.threads=0
    [CHANGED]hive.compactor.worker.timeout=86400s | hive.compactor.worker.timeout=86400
    [EQUAL]hive.compute.query.using.stats=true | hive.compute.query.using.stats=true
    [CHANGED]hive.enforce.bucketing is undefined | hive.enforce.bucketing=true
    [CHANGED]hive.enforce.sorting is undefined | hive.enforce.sorting=true
    [CHANGED]hive.enforce.sortmergebucketmapjoin=false | hive.enforce.sortmergebucketmapjoin=true
    [CHANGED]hive.limit.pushdown.memory.usage=0.1 | hive.limit.pushdown.memory.usage=0.04
    [EQUAL]hive.map.aggr=true | hive.map.aggr=true
    [CHANGED]hive.mapjoin.bucket.cache.size=100 | hive.mapjoin.bucket.cache.size=10000
    [CHANGED]hive.mapred.reduce.tasks.speculative.execution=true | hive.mapred.reduce.tasks.speculative.execution=false
    [CHANGED]hive.metastore.cache.pinobjtypes=Table,StorageDescriptor,SerDeInfo,Partition,Database,Type,FieldSchema,Order | hive.metastore.cache.pinobjtypes=Table,Database,Type,FieldSchema,Order
    [CHANGED]hive.metastore.client.socket.timeout=600s | hive.metastore.client.socket.timeout=60
    [EQUAL]hive.metastore.execute.setugi=true | hive.metastore.execute.setugi=true
    [CHANGED]hive.metastore.warehouse.dir=s3://capemrbucket/hive_emr/ | hive.metastore.warehouse.dir=/apps/hive/warehouse
    [EQUAL]hive.optimize.bucketmapjoin.sortedmerge=false | hive.optimize.bucketmapjoin.sortedmerge=false
    [CHANGED]hive.optimize.bucketmapjoin=false | hive.optimize.bucketmapjoin=true
    [CHANGED]hive.optimize.index.filter=false | hive.optimize.index.filter=true
    [EQUAL]hive.optimize.reducededuplication.min.reducer=4 | hive.optimize.reducededuplication.min.reducer=4
    [EQUAL]hive.optimize.reducededuplication=true | hive.optimize.reducededuplication=true
    [EQUAL]hive.orc.splits.include.file.footer=false | hive.orc.splits.include.file.footer=false
    [CHANGED]hive.server2.enable.doAs=true | hive.server2.enable.doAs=false
    [CHANGED]hive.server2.tez.default.queues is undefined | hive.server2.tez.default.queues=default
    [EQUAL]hive.server2.tez.initialize.default.sessions=false | hive.server2.tez.initialize.default.sessions=false
    [EQUAL]hive.server2.tez.sessions.per.default.queue=1 | hive.server2.tez.sessions.per.default.queue=1
    [EQUAL]hive.stats.autogather=true | hive.stats.autogather=true
    [EQUAL]hive.tez.input.format=org.apache.hadoop.hive.ql.io.HiveInputFormat | hive.tez.input.format=org.apache.hadoop.hive.ql.io.HiveInputFormat
    [EQUAL]hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DummyTxnManager | hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DummyTxnManager
    [EQUAL]hive.txn.max.open.batch=1000 | hive.txn.max.open.batch=1000
    [CHANGED]hive.txn.timeout=300s | hive.txn.timeout=300
    [CHANGED]hive.vectorized.execution.enabled=false | hive.vectorized.execution.enabled=true
    [CHANGED]hive.vectorized.groupby.checkinterval=100000 | hive.vectorized.groupby.checkinterval=1024
    [CHANGED]hive.vectorized.groupby.flush.percent=0.1 | hive.vectorized.groupby.flush.percent=1
    [CHANGED]hive.vectorized.groupby.maxentries=1000000 | hive.vectorized.groupby.maxentries=1024
    [EQUAL]hive.execution.engine=tez | hive.execution.engine=tez
    [CHANGED]hive.default.fileformat=TextFile | hive.default.fileformat=orc
    [EQUAL]hive.cbo.enable=true | hive.cbo.enable=true
    [CHANGED]hive.stats.fetch.column.stats=false | hive.stats.fetch.column.stats=true
    [CHANGED]hive.exec.dynamic.partition.mode=strict | hive.exec.dynamic.partition.mode=nonstrict
    [CHANGED]hive.tez.auto.reducer.parallelism=false | hive.tez.auto.reducer.parallelism=true
    [CHANGED]hive.exec.reducers.bytes.per.reducer=256000000 | hive.exec.reducers.bytes.per.reducer=100000000
    [EQUAL]hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DummyTxnManager | hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DummyTxnManager
    [EQUAL]hive.support.concurrency=false | hive.support.concurrency=false
    [CHANGED]hive.tez.exec.print.summary=false | hive.tez.exec.print.summary=true
    [CHANGED]hive.llap.io.enabled is undefined | hive.llap.io.enabled=true
    [CHANGED]hbase.scan.cache is undefined | hbase.scan.cache=10000
    [CHANGED]hbase.client.scanner.cache is undefined | hbase.client.scanner.cache=10000
    RESULT #3: Hive Session parameters SET.

    STAGE #4: Loading Test Plans.
    RESULT #4: Loaded a total of 6 tests.

    START TEST #1: Query Hbase table by rowkey (S3 backed).
    ITERATIONS: 10 LLAP_STATUS: false: Query (SELECT * FROM noc_operational_pt.hbs_alrm where id = 'ff7eaf717d6523a4307280a05fcba894')
    Test #1 LLAP_STATUS: false (10/10) | Execution time (ms): 718.121085
    Test #1 LLAP_STATUS: false (9/10) | Execution time (ms): 374.58678299999997
    Test #1 LLAP_STATUS: false (8/10) | Execution time (ms): 415.343321
    Test #1 LLAP_STATUS: false (7/10) | Execution time (ms): 436.52743799999996
    Test #1 LLAP_STATUS: false (6/10) | Execution time (ms): 436.71797799999996
    Test #1 LLAP_STATUS: false (5/10) | Execution time (ms): 403.772017
    Test #1 LLAP_STATUS: false (4/10) | Execution time (ms): 438.984698
    Test #1 LLAP_STATUS: false (3/10) | Execution time (ms): 402.620002
    Test #1 LLAP_STATUS: false (2/10) | Execution time (ms): 380.849951
    Test #1 LLAP_STATUS: false (1/10) | Execution time (ms): 375.711097
    RESULT #1 LLAP_STATUS: false: Records [1] Measures: [10] First: [718.121085] Last: [375.711097] Min: [374.58678299999997] Avg: [438.323437] Max: [718.121085]

    START TEST #2: Query Hbase table by rowkey (S3 backed).
    ITERATIONS: 10 LLAP_STATUS: true: Query (SELECT * FROM noc_operational_pt.hbs_alrm where id = 'ff7eaf717d6523a4307280a05fcba894')
    Test #2 LLAP_STATUS: true (10/10) | Execution time (ms): 342.57255499999997
    Test #2 LLAP_STATUS: true (9/10) | Execution time (ms): 361.01772
    Test #2 LLAP_STATUS: true (8/10) | Execution time (ms): 369.614009
    Test #2 LLAP_STATUS: true (7/10) | Execution time (ms): 412.460316
    Test #2 LLAP_STATUS: true (6/10) | Execution time (ms): 400.25768999999997
    Test #2 LLAP_STATUS: true (5/10) | Execution time (ms): 413.30156999999997
    Test #2 LLAP_STATUS: true (4/10) | Execution time (ms): 383.65590299999997
    Test #2 LLAP_STATUS: true (3/10) | Execution time (ms): 407.32393099999996
    Test #2 LLAP_STATUS: true (2/10) | Execution time (ms): 404.068738
    Test #2 LLAP_STATUS: true (1/10) | Execution time (ms): 403.963646
    RESULT #2 LLAP_STATUS: true: Records [1] Measures: [10] First: [342.57255499999997] Last: [403.963646] Min: [342.57255499999997] Avg: [389.82360779999993] Max: [413.30156999999997]

    START TEST #3: Full scan of small table (Hive native table located in s3).
    ITERATIONS: 10 LLAP_STATUS: false: Query (select * from date_dim)
    Test #3 LLAP_STATUS: false (10/10) | Execution time (ms): 3988.676126
    Test #3 LLAP_STATUS: false (9/10) | Execution time (ms): 3940.5351619999997
    Test #3 LLAP_STATUS: false (8/10) | Execution time (ms): 4241.338737
    Test #3 LLAP_STATUS: false (7/10) | Execution time (ms): 3729.610629
    Test #3 LLAP_STATUS: false (6/10) | Execution time (ms): 3607.016705
    Test #3 LLAP_STATUS: false (5/10) | Execution time (ms): 3944.9515699999997
    Test #3 LLAP_STATUS: false (4/10) | Execution time (ms): 3683.626213
    Test #3 LLAP_STATUS: false (3/10) | Execution time (ms): 3808.5531619999997
    Test #3 LLAP_STATUS: false (2/10) | Execution time (ms): 4009.7750579999997
    Test #3 LLAP_STATUS: false (1/10) | Execution time (ms): 4123.275693
    RESULT #3 LLAP_STATUS: false: Records [73049] Measures: [10] First: [3988.676126] Last: [4123.275693] Min: [3607.016705] Avg: [3907.7359055000006] Max: [4241.338737]

    START TEST #4: Full scan of small table (Hive native table located in s3).
    ITERATIONS: 10 LLAP_STATUS: true: Query (select * from date_dim)
    Test #4 LLAP_STATUS: true (10/10) | Execution time (ms): 3613.6632029999996
    Test #4 LLAP_STATUS: true (9/10) | Execution time (ms): 3626.09118
    Test #4 LLAP_STATUS: true (8/10) | Execution time (ms): 3673.1485519999997
    Test #4 LLAP_STATUS: true (7/10) | Execution time (ms): 3994.839239
    Test #4 LLAP_STATUS: true (6/10) | Execution time (ms): 3477.8690659999997
    Test #4 LLAP_STATUS: true (5/10) | Execution time (ms): 3959.7906159999998
    Test #4 LLAP_STATUS: true (4/10) | Execution time (ms): 3643.372845
    Test #4 LLAP_STATUS: true (3/10) | Execution time (ms): 3682.904682
    Test #4 LLAP_STATUS: true (2/10) | Execution time (ms): 3685.2258229999998
    Test #4 LLAP_STATUS: true (1/10) | Execution time (ms): 3719.056764
    RESULT #4 LLAP_STATUS: true: Records [73049] Measures: [10] First: [3613.6632029999996] Last: [3719.056764] Min: [3477.8690659999997] Avg: [3707.596197] Max: [3994.839239]

    START TEST #5: Optimized aggregation on a partitioned table (Hive native table located in s3).
    ITERATIONS: 10 LLAP_STATUS: false: Query (select sum(ss_ext_list_price) as sold_value, sum(ss_quantity) as sold_qty from store_sales WHERE ss_sold_date_sk = 2450816)
    Test #5 LLAP_STATUS: false (10/10) | Execution time (ms): 16095.501726999999
    Test #5 LLAP_STATUS: false (9/10) | Execution time (ms): 6742.833059
    Test #5 LLAP_STATUS: false (8/10) | Execution time (ms): 6564.725726
    Test #5 LLAP_STATUS: false (7/10) | Execution time (ms): 6609.856615
    Test #5 LLAP_STATUS: false (6/10) | Execution time (ms): 6609.35891
    Test #5 LLAP_STATUS: false (5/10) | Execution time (ms): 7179.008495
    Test #5 LLAP_STATUS: false (4/10) | Execution time (ms): 6519.674692
    Test #5 LLAP_STATUS: false (3/10) | Execution time (ms): 6500.298554
    Test #5 LLAP_STATUS: false (2/10) | Execution time (ms): 6632.384682
    Test #5 LLAP_STATUS: false (1/10) | Execution time (ms): 6750.7160269999995
    RESULT #5 LLAP_STATUS: false: Records [261356] Measures: [10] First: [16095.501726999999] Last: [6750.7160269999995] Min: [6500.298554] Avg: [7620.435848700001] Max: [16095.501726999999]

    START TEST #6: Optimized aggregation on a partitioned table (Hive native table located in s3).
    ITERATIONS: 10 LLAP_STATUS: true: Query (select sum(ss_ext_list_price) as sold_value, sum(ss_quantity) as sold_qty from store_sales WHERE ss_sold_date_sk = 2450816)
    Test #6 LLAP_STATUS: true (10/10) | Execution time (ms): 1868.9531049999998
    Test #6 LLAP_STATUS: true (9/10) | Execution time (ms): 1628.848065
    Test #6 LLAP_STATUS: true (8/10) | Execution time (ms): 2009.5364049999998
    Test #6 LLAP_STATUS: true (7/10) | Execution time (ms): 1687.612021
    Test #6 LLAP_STATUS: true (6/10) | Execution time (ms): 1584.992503
    Test #6 LLAP_STATUS: true (5/10) | Execution time (ms): 1611.69676
    Test #6 LLAP_STATUS: true (4/10) | Execution time (ms): 1602.97068
    Test #6 LLAP_STATUS: true (3/10) | Execution time (ms): 1542.703511
    Test #6 LLAP_STATUS: true (2/10) | Execution time (ms): 2011.0151569999998
    Test #6 LLAP_STATUS: true (1/10) | Execution time (ms): 1603.559737
    RESULT #6 LLAP_STATUS: true: Records [261356] Measures: [10] First: [1868.9531049999998] Last: [1603.559737] Min: [1542.703511] Avg: [1715.1887944000002] Max: [2011.0151569999998]

