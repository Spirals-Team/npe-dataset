Exception:

    Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.088 sec <<< FAILURE! - in SimplexSolverTest
    testMath290(SimplexSolverTest)  Time elapsed: 0.047 sec  <<< ERROR!
    java.lang.NullPointerException: null
            at org.apache.commons.math.optimization.linear.SimplexTableau.initialize(SimplexTableau.java:249)
            at org.apache.commons.math.optimization.linear.SimplexTableau.<init>(SimplexTableau.java:115)
            at org.apache.commons.math.optimization.linear.SimplexSolver.doOptimize(SimplexSolver.java:186)
            at org.apache.commons.math.optimization.linear.AbstractLinearOptimizer.optimize(AbstractLinearOptimizer.java:106)
            at SimplexSolverTest.testMath290(SimplexSolverTest.java:24)
            
Patch: SVN revision 807923

<http://svn.apache.org/viewvc/commons/proper/math/trunk/src/main/java/org/apache/commons/math/optimization/linear/SimplexTableau.java?r1=807923&r2=807922&pathrev=807923&diff_format=l>

