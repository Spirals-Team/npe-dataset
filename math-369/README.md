# Math 369

Exception:
```java
testMath369(org.apache.commons.math.analysis.solvers.BisectionSolverTest)  Time elapsed: 0.048 sec  <<< ERROR!
java.lang.NullPointerException: null
  at org.apache.commons.math.analysis.solvers.BisectionSolver.solve(BisectionSolver.java:88)
  at org.apache.commons.math.analysis.solvers.BisectionSolver.solve(BisectionSolver.java:66)
  at org.apache.commons.math.analysis.solvers.BisectionSolver.solve(BisectionSolver.java:72)
  at org.apache.commons.math.analysis.solvers.BisectionSolverTest.testMath369(BisectionSolverTest.java:40)
```

Fix Commit: [SVN Revision 940565](http://svn.apache.org/viewvc?view=revision&revision=940565)

Patch: <http://svn.apache.org/viewvc/commons/proper/math/trunk/src/main/java/org/apache/commons/math/analysis/solvers/BisectionSolver.java?r1=940565&r2=940564&pathrev=940565>

```diff
--- commons/proper/math/trunk/src/main/java/org/apache/commons/math/analysis/solvers/BisectionSolver.java 2010/05/03 18:40:53 940564
+++ commons/proper/math/trunk/src/main/java/org/apache/commons/math/analysis/solvers/BisectionSolver.java 2010/05/03 18:42:27 940565
@@ -69,7 +69,7 @@
     /** {@inheritDoc} */
     public double solve(final UnivariateRealFunction f, double min, double max, double initial)
         throws MaxIterationsExceededException, FunctionEvaluationException {
-        return solve(min, max);
+        return solve(f, min, max);
     }
 
     /** {@inheritDoc} */
```