package org.tuberlin.de.common.test;

import org.junit.Before;
import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.common.model.interfaces.JobComponent;
import org.tuberlin.de.common.model.interfaces.datasources.file.DataSourceFile;
import org.tuberlin.de.common.model.interfaces.datasources.file.read.DataSourceFileReadCsvFile;
import org.tuberlin.de.common.model.interfaces.transorfmation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oxid on 1/12/16.
 */



public class PageRankTest {


    /*    IterativeDataSet<Tuple2<Long, Double>> iteration = pagesWithRanks.iterate(maxIterations);

    JoinOperator.DefaultJoin<Tuple2<Long, Double>, Tuple2<Long, Long[]>> iterationtmp = iteration.join(adjacencyListInput).where(0).equalTo(0);

    DataSet<Tuple2<Long, Double>> iterationtmp2 = iterationtmp.flatMap(new JoinVertexWithEdgesMatch());

    UnsortedGrouping<Tuple2<Long, Double>> iterationtmp3 = iterationtmp2.groupBy(0);

    DataSet<Tuple2<Long, Double>> iterationtmp4 = iterationtmp3.aggregate(SUM, 1);

    DataSet<Tuple2<Long, Double>> newRanks = iterationtmp4.map(new Dampener(DAMPENING_FACTOR, numPages));


	*//*
	DataSet<Tuple2<Long, Double>> finalPageRanks = iteration.closeWith(newRanks, newRanks.join(iteration).where(0).equalTo(0).filter(new EpsilonFilter()));
	*//*

    JoinOperator.DefaultJoin<Tuple2<Long, Double>, Tuple2<Long, Double>> newRankstmp = newRanks.join(iteration).where(0).equalTo(0);

    FilterOperator<Tuple2<Tuple2<Long, Double>, Tuple2<Long, Double>>> newRankstmp2 = newRankstmp.filter(new EpsilonFilter());

    DataSet<Tuple2<Long, Double>> finalPageRanks = iteration.closeWith(newRanks, newRankstmp2);*/



    @Before
    public void setUp() throws  Exception{


        Map<String, Object> jobGraphParamters = new HashMap<String, Object>();


        String pagesInputCK = "pagesInput";
        String linksInputCK = "linksInput";
        String linksInputTmpCK = "linksInputTmp";
        String pagesWithRanksCK = "pagesWithRanks";
        String adjacencyListInputCK = "adjacencyListInput";
        String iterationCK = "iteration";
        String iterationTmpCK = "iterationTmp";
        String iterationTmp2CK = "iterationTmp2";
        String iterationTmp3CK = "iterationTmp3";
        String iterationTmp4CK = "iterationTmp4";
        String newRanksCK = "newRanksCK";
        String newRanksTmpCK = "newRanksTmpCK";
        String newRanksTmp2CK = "newRanksCK";
        String finalPageRanksCK = "finalPageRanksCK";

        /**
         * readCsvFile
         *
D        * DataSet<Long> pagesInput = getPagesDataSet(env);
         */
        Map<String, Object> pagesInputParameters = new HashMap<String, Object>();
        pagesInputParameters.put(DataSourceFileReadCsvFile.COMPONENT_KEY, pagesInputCK);

        pagesInputParameters.put(DataSourceFileReadCsvFile.CHILD, pagesWithRanksCK);
        pagesInputParameters.put(DataSourceFileReadCsvFile.PARENT, null);



        /**
         * readCsvFile
         *
         * DataSet<Tuple2<Long, Long>> linksInput = getLinksDataSet(env);
         */
        Map<String, Object> linksInputParameters = new HashMap<String, Object>();
        linksInputParameters.put(DataSourceFileReadCsvFile.COMPONENT_KEY, linksInputCK);

        linksInputParameters.put(DataSourceFileReadCsvFile.CHILD, linksInputTmpCK);
        linksInputParameters.put(DataSourceFileReadCsvFile.PARENT, null );


        /**
         * TransformationMap
         *
         * DataSet<Tuple2<Long, Double>> pagesWithRanks = pagesInput.map(new RankAssigner((1.0d / numPages)));
         */
        Map<String, Object> pagesWithRanksParameters = new HashMap<String, Object>();
        pagesWithRanksParameters.put(TransformationMap.COMPONENT_KEY, pagesWithRanksCK);

        pagesWithRanksParameters.put(TransformationMap.CHILD, iterationCK);
        pagesWithRanksParameters.put(TransformationMap.PARENT, pagesInputCK);


        /**
         * TransformationGroupBy
         *
         * UnsortedGrouping<Tuple2<Long, Long>> linksInputTmp = linksInput.groupBy(0);
         */
        Map<String, Object> linksInputTmpParameters = new HashMap<String, Object>();
        linksInputTmpParameters.put(TransformationGroupBy.COMPONENT_KEY, linksInputTmpCK);

        linksInputTmpParameters.put(TransformationGroupBy.CHILD, adjacencyListInputCK);
        linksInputTmpParameters.put(TransformationGroupBy.PARENT, linksInputCK);



        /**
         * TransformationReduceGroup
         *
         * DataSet<Tuple2<Long, Long[]>> adjacencyListInput = linksInputTmp.reduceGroup(new BuildOutgoingEdgeList());
         */
        Map<String, Object> adjacencyListInputParameters = new HashMap<String, Object>();
        adjacencyListInputParameters.put(TransformationReduceGroup.COMPONENT_KEY, adjacencyListInputCK);

        adjacencyListInputParameters.put(TransformationReduceGroup.CHILD,iterationTmpCK);
        adjacencyListInputParameters.put(TransformationReduceGroup.PARENT, linksInputTmpCK);


        /**
         * TODO iterate?
         *
         * IterativeDataSet<Tuple2<Long, Double>> iteration = pagesWithRanks.iterate(maxIterations);
         */
        Map<String, Object> iterationPrarameters = new HashMap<String, Object>();



        /**
         * TransformationJoin
         *
         * JoinOperator.DefaultJoin<Tuple2<Long, Double>, Tuple2<Long, Long[]>> iterationTmp = iteration.join(adjacencyListInput).where(0).equalTo(0);
         */
        Map<String, Object> iterationTmpPrarameters = new HashMap<String, Object>();
        iterationTmpPrarameters.put(TransformationJoin.COMPONENT_KEY, iterationTmpCK);

        iterationTmpPrarameters.put(TransformationJoin.CHILD, );
        //TODO think about parents for join! iteration.join(adjacencyListInput) == adjacencyListInput.join(iteration)
        iterationTmpPrarameters.put(TransformationJoin.PARENT, iterationTmp2CK);


        /**
         * TransformationFlatMap
         *
         * DataSet<Tuple2<Long, Double>> iterationtmp2 = iterationtmp.flatMap(new JoinVertexWithEdgesMatch());
         */
        Map<String, Object> iterationTmp2Prarameters = new HashMap<String, Object>();
        iterationTmp2Prarameters.put(TransformationJoin.COMPONENT_KEY, iterationTmp2CK);

        iterationTmp2Prarameters.put(TransformationFlatMap.CHILD, iterationTmp3CK);
        iterationTmp2Prarameters.put(TransformationJoin.PARENT, iterationTmpCK);

        /**
         * TransformationGroupBy
         *
         * UnsortedGrouping<Tuple2<Long, Double>> iterationtmp3 = iterationtmp2.groupBy(0);
         */
        Map<String, Object> iterationTmp3Prarameters = new HashMap<String, Object>();
        iterationTmp3Prarameters.put(TransformationGroupBy.COMPONENT_KEY, iterationTmp3CK);

        iterationTmp3Prarameters.put(TransformationGroupBy.CHILD, iterationTmp4CK);
        iterationTmp3Prarameters.put(TransformationGroupBy.PARENT, iterationTmp2CK);

        /**
         * TransformationAggregate
         *
         * DataSet<Tuple2<Long, Double>> iterationtmp4 = iterationtmp3.aggregate(SUM, 1);
         */
        Map<String, Object> iterationTmp4Prarameters = new HashMap<String, Object>();
        iterationTmp4Prarameters.put(TransformationAggregate.COMPONENT_KEY, iterationTmp4CK);

        iterationTmp4Prarameters.put(TransformationAggregate.CHILD, newRanksCK);
        iterationTmp4Prarameters.put(TransformationAggregate.PARENT, iterationTmp3CK);



        /**
         * TransformationMap
         *
         * DataSet<Tuple2<Long, Double>> newRanks = iterationtmp4.map(new Dampener(DAMPENING_FACTOR, numPages));
         */
        Map<String, Object> newRankParameters = new HashMap<String, Object>();
        newRankParameters.put(TransformationMap.COMPONENT_KEY, newRanksCK);

        newRankParameters.put(TransformationMap.CHILD, newRanksTmpCK);
        newRankParameters.put(TransformationMap.PARENT, iterationTmp4CK);


        /**
         * TransformationJoin
         *
         * JoinOperator.DefaultJoin<Tuple2<Long, Double>, Tuple2<Long, Double>> newRankstmp = newRanks.join(iteration).where(0).equalTo(0);
         */
        Map<String, Object> newRanksTmpParameters = new HashMap<String, Object>();
        newRanksTmpParameters.put(TransformationJoin.COMPONENT_KEY, newRanksTmpCK);

        newRanksTmpParameters.put(TransformationJoin.CHILD, newRanksTmpCK);
        newRanksTmpParameters.put(TransformationJoin.PARENT,  newRanksCK);


        /**
         * TransformationFilter
         *
         * FilterOperator<Tuple2<Tuple2<Long, Double>, Tuple2<Long, Double>>> newRankstmp2 = newRankstmp.filter(new EpsilonFilter());
         */
        Map<String, Object> newRanksTmp2Parameters = new HashMap<String, Object>();
        newRanksTmp2Parameters.put(TransformationFilter.COMPONENT_KEY, newRanksCK);

        newRanksTmp2Parameters.put(TransformationFilter.CHILD, finalPageRanksCK);
        newRanksTmp2Parameters.put(TransformationFilter.PARENT, newRanksTmpCK);





        /**
         * TODO ??? has 3 parents
         *
         * DataSet<Tuple2<Long, Double>> finalPageRanks = iteration.closeWith(newRanks, newRankstmp2);
         */
        Map<String, Object> finalPageRanksParameters = new HashMap<String, Object>();





















        /**
         * DataSet<Tuple2<Long, Long>> linksInput
         */
        Map<String, Object> linksInputReadCsvFileParameters = new HashMap<String, Object>();
        // groupBy(0): linksInput -> linksInput DataSet<Tuple2<Long, Long>>
        Map<String, Object> linksInputGroupByParameters = new HashMap<String, Object>();
        // reduceGroup(new BuildOutgoingEdgeList()): linksInput ->  DataSet<Tuple2<Long, Long[]>> adjacencyListInput
        Map<String, Object> linksInputReduceGroupPrarameters = new HashMap<String, Object>();


        /**
         * DataSet<Tuple2<Long, Double>> pagesWithRanks
         */
        //TODO IterativeDataSet<Tuple2<Long, Double>> iteration = pagesWithRanks.iterate(maxIterations);


        /**
         * IterativeDataSet<Tuple2<Long, Double>> iteration
         */
        // join(adjacencyListInput): iteration ->






        Map<String, Object> fMapPrarameters = new HashMap<String, Object>();
        Map<String, Object> gByParameters = new HashMap<String, Object>();
        Map<String, Object> aggParameters = new HashMap<String, Object>();
        Map<String, Object> dSinkParameters = new HashMap<String, Object>();
        String componentKeyDataSourceTest = "dataSourceTest";
        String componentKeyFlatMapTest = "flatMapTest";
        String componentKeyGroupBy = "groupByTest";
        String componentKeyAggregate = "aggregateTest";
        String componentKeyDataSink = "dataSinkTest";



    }



}

/*
package org.apache.flink.examples.java.graph;

        import static org.apache.flink.api.java.aggregation.Aggregations.SUM;

        import java.util.ArrayList;

        import org.apache.flink.api.common.functions.FilterFunction;
        import org.apache.flink.api.common.functions.FlatMapFunction;
        import org.apache.flink.api.common.functions.GroupReduceFunction;
        import org.apache.flink.api.common.functions.MapFunction;
        import org.apache.flink.api.java.functions.FunctionAnnotation.ForwardedFields;
        import org.apache.flink.api.java.tuple.Tuple1;
        import org.apache.flink.api.java.tuple.Tuple2;
        import org.apache.flink.util.Collector;
        import org.apache.flink.api.java.DataSet;
        import org.apache.flink.api.java.ExecutionEnvironment;
        import org.apache.flink.api.java.operators.IterativeDataSet;
        import org.apache.flink.examples.java.graph.util.PageRankData;
*/

/*
@SuppressWarnings("serial")
public class PageRankBasic {

    private static final double DAMPENING_FACTOR = 0.85;
    private static final double EPSILON = 0.0001;

    // *************************************************************************
    //     PROGRAM
    // *************************************************************************

    public static void main(String[] args) throws Exception {

        if(!parseParameters(args)) {
            return;
        }

        // set up execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // get input data
        DataSet<Long> pagesInput = getPagesDataSet(env);
        DataSet<Tuple2<Long, Long>> linksInput = getLinksDataSet(env);

        // assign initial rank to pages
        DataSet<Tuple2<Long, Double>> pagesWithRanks = pagesInput.
                map(new RankAssigner((1.0d / numPages)));

        // build adjacency list from link input
        DataSet<Tuple2<Long, Long[]>> adjacencyListInput =
                linksInput.groupBy(0).reduceGroup(new BuildOutgoingEdgeList());

        // set iterative data set
        IterativeDataSet<Tuple2<Long, Double>> iteration = pagesWithRanks.iterate(maxIterations);

        DataSet<Tuple2<Long, Double>> newRanks = iteration
                // join pages with outgoing edges and distribute rank
                .join(adjacencyListInput).where(0).equalTo(0).flatMap(new JoinVertexWithEdgesMatch())
                // collect and sum ranks
                .groupBy(0).aggregate(SUM, 1)
                // apply dampening factor
                .map(new Dampener(DAMPENING_FACTOR, numPages));

        DataSet<Tuple2<Long, Double>> finalPageRanks = iteration.closeWith(
                newRanks,
                newRanks.join(iteration).where(0).equalTo(0)
                        // termination condition
                        .filter(new EpsilonFilter()));

        // emit result
        if(fileOutput) {
            finalPageRanks.writeAsCsv(outputPath, "\n", " ");
            // execute program
            env.execute("Basic Page Rank Example");
        } else {
            finalPageRanks.print();
        }


    }

    // *************************************************************************
    //     USER FUNCTIONS
    // *************************************************************************

    *//**
     * A map function that assigns an initial rank to all pages.
     *//*
    public static final class RankAssigner implements MapFunction<Long, Tuple2<Long, Double>> {
        Tuple2<Long, Double> outPageWithRank;

        public RankAssigner(double rank) {
            this.outPageWithRank = new Tuple2<Long, Double>(-1l, rank);
        }

        @Override
        public Tuple2<Long, Double> map(Long page) {
            outPageWithRank.f0 = page;
            return outPageWithRank;
        }
    }

    *//**
     * A reduce function that takes a sequence of edges and builds the adjacency list for the vertex where the edges
     * originate. Run as a pre-processing step.
     *//*
    @ForwardedFields("0")
    public static final class BuildOutgoingEdgeList implements GroupReduceFunction<Tuple2<Long, Long>, Tuple2<Long, Long[]>> {

        private final ArrayList<Long> neighbors = new ArrayList<Long>();

        @Override
        public void reduce(Iterable<Tuple2<Long, Long>> values, Collector<Tuple2<Long, Long[]>> out) {
            neighbors.clear();
            Long id = 0L;

            for (Tuple2<Long, Long> n : values) {
                id = n.f0;
                neighbors.add(n.f1);
            }
            out.collect(new Tuple2<Long, Long[]>(id, neighbors.toArray(new Long[neighbors.size()])));
        }
    }

    *//**
     * Join function that distributes a fraction of a vertex's rank to all neighbors.
     *//*
    public static final class JoinVertexWithEdgesMatch implements FlatMapFunction<Tuple2<Tuple2<Long, Double>, Tuple2<Long, Long[]>>, Tuple2<Long, Double>> {

        @Override
        public void flatMap(Tuple2<Tuple2<Long, Double>, Tuple2<Long, Long[]>> value, Collector<Tuple2<Long, Double>> out){
            Long[] neigbors = value.f1.f1;
            double rank = value.f0.f1;
            double rankToDistribute = rank / ((double) neigbors.length);

            for (int i = 0; i < neigbors.length; i++) {
                out.collect(new Tuple2<Long, Double>(neigbors[i], rankToDistribute));
            }
        }
    }

    *//**
     * The function that applies the page rank dampening formula
     *//*
    @ForwardedFields("0")
    public static final class Dampener implements MapFunction<Tuple2<Long,Double>, Tuple2<Long,Double>> {

        private final double dampening;
        private final double randomJump;

        public Dampener(double dampening, double numVertices) {
            this.dampening = dampening;
            this.randomJump = (1 - dampening) / numVertices;
        }

        @Override
        public Tuple2<Long, Double> map(Tuple2<Long, Double> value) {
            value.f1 = (value.f1 * dampening) + randomJump;
            return value;
        }
    }

    *//**
     * Filter that filters vertices where the rank difference is below a threshold.
     *//*
    public static final class EpsilonFilter implements FilterFunction<Tuple2<Tuple2<Long, Double>, Tuple2<Long, Double>>> {

        @Override
        public boolean filter(Tuple2<Tuple2<Long, Double>, Tuple2<Long, Double>> value) {
            return Math.abs(value.f0.f1 - value.f1.f1) > EPSILON;
        }
    }

    // *************************************************************************
    //     UTIL METHODS
    // *************************************************************************

    private static boolean fileOutput = false;
    private static String pagesInputPath = null;
    private static String linksInputPath = null;
    private static String outputPath = null;
    private static long numPages = 0;
    private static int maxIterations = 10;

    private static boolean parseParameters(String[] args) {

        if(args.length > 0) {
            if(args.length == 5) {
                fileOutput = true;
                pagesInputPath = args[0];
                linksInputPath = args[1];
                outputPath = args[2];
                numPages = Integer.parseInt(args[3]);
                maxIterations = Integer.parseInt(args[4]);
            } else {
                System.err.println("Usage: PageRankBasic <pages path> <links path> <output path> <num pages> <num iterations>");
                return false;
            }
        } else {
            System.out.println("Executing PageRank Basic example with default parameters and built-in default data.");
            System.out.println("  Provide parameters to read input data from files.");
            System.out.println("  See the documentation for the correct format of input files.");
            System.out.println("  Usage: PageRankBasic <pages path> <links path> <output path> <num pages> <num iterations>");

            numPages = PageRankData.getNumberOfPages();
        }
        return true;
    }

    private static DataSet<Long> getPagesDataSet(ExecutionEnvironment env) {
        if(fileOutput) {
            return env
                    .readCsvFile(pagesInputPath)
                    .fieldDelimiter(" ")
                    .lineDelimiter("\n")
                    .types(Long.class)
                    .map(new MapFunction<Tuple1<Long>, Long>() {
                        @Override
                        public Long map(Tuple1<Long> v) { return v.f0; }
                    });
        } else {
            return PageRankData.getDefaultPagesDataSet(env);
        }
    }

    private static DataSet<Tuple2<Long, Long>> getLinksDataSet(ExecutionEnvironment env) {
        if(fileOutput) {
            return env.readCsvFile(linksInputPath)
                    .fieldDelimiter(" ")
                    .lineDelimiter("\n")
                    .types(Long.class, Long.class);
        } else {
            return PageRankData.getDefaultEdgeDataSet(env);
        }
    }
}*/


