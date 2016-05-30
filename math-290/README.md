# Math 209

Exception:
```java
testMath290(SimplexSolverTest)  Time elapsed: 0.047 sec  <<< ERROR!
java.lang.NullPointerException: null
        at org.apache.commons.math.optimization.linear.SimplexTableau.initialize(SimplexTableau.java:249)
        at org.apache.commons.math.optimization.linear.SimplexTableau.<init>(SimplexTableau.java:115)
        at org.apache.commons.math.optimization.linear.SimplexSolver.doOptimize(SimplexSolver.java:186)
        at org.apache.commons.math.optimization.linear.AbstractLinearOptimizer.optimize(AbstractLinearOptimizer.java:106)
        at SimplexSolverTest.testMath290(SimplexSolverTest.java:24)
```

Fix Commit: [SVN Revision 807923](http://svn.apache.org/viewvc?view=revision&revision=807923)

Patch: <http://svn.apache.org/viewvc/commons/proper/math/trunk/src/main/java/org/apache/commons/math/optimization/linear/SimplexTableau.java?r1=807923&r2=807922&pathrev=807923&diff_format=l>

```diff
--- commons/proper/math/trunk/src/main/java/org/apache/commons/math/optimization/linear/SimplexTableau.java	2009/08/26 08:25:46	807922
+++ commons/proper/math/trunk/src/main/java/org/apache/commons/math/optimization/linear/SimplexTableau.java	2009/08/26 08:43:27	807923
@@ -69,7 +69,7 @@
     private final LinearObjectiveFunction f;
 
     /** Linear constraints. */
-    private final Collection<LinearConstraint> constraints;
+    private final List<LinearConstraint> constraints;
 
     /** Whether to restrict the variables to non-negative values. */
     private final boolean restrictToNonNegative;
@@ -103,7 +103,7 @@
                    final GoalType goalType, final boolean restrictToNonNegative,
                    final double epsilon) {
         this.f                      = f;
-        this.constraints            = constraints;
+        this.constraints            = normalizeConstraints(constraints);
         this.restrictToNonNegative  = restrictToNonNegative;
         this.epsilon                = epsilon;
         this.numDecisionVariables   = getNumVariables() + (restrictToNonNegative ? 0 : 1);
@@ -123,7 +123,6 @@
     protected double[][] createTableau(final boolean maximize) {
 
         // create a matrix of the correct size
-        List<LinearConstraint> constraints = getNormalizedConstraints();
         int width = numDecisionVariables + numSlackVariables +
         numArtificialVariables + getNumObjectiveFunctions() + 1; // + 1 is for RHS
         int height = constraints.size() + getNumObjectiveFunctions();
@@ -192,9 +191,10 @@
 
     /**
      * Get new versions of the constraints which have positive right hand sides.
+     * @param constraints original (not normalized) constraints
      * @return new versions of the constraints
      */
-    public List<LinearConstraint> getNormalizedConstraints() {
+    public List<LinearConstraint> normalizeConstraints(Collection<LinearConstraint> constraints) {
         List<LinearConstraint> normalized = new ArrayList<LinearConstraint>();
         for (LinearConstraint constraint : constraints) {
             normalized.add(normalize(constraint));
```