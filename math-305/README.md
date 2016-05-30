# Math 305

Exception:
```java
testPerformClusterAnalysisDegenerate(org.apache.commons.math.stat.clustering.KMeansPlusPlusClustererTest)  Time elapsed: 0.017 sec  <<< ERROR!
java.lang.NullPointerException: null
        at org.apache.commons.math.stat.clustering.KMeansPlusPlusClusterer.assignPointsToClusters(KMeansPlusPlusClusterer.java:91)
        at org.apache.commons.math.stat.clustering.KMeansPlusPlusClusterer.cluster(KMeansPlusPlusClusterer.java:57)
        at org.apache.commons.math.stat.clustering.KMeansPlusPlusClustererTest.testPerformClusterAnalysisDegenerate(KMeansPlusPlusClustererTest.java:43)
```

Commit: [SVN Revision 885027](http://svn.apache.org/viewvc?view=revision&revision=885027)

Commit message: "Fixed an overflow error in MathUtils.distance that was causing KMeansPlusPlusClusterer to fail with a NullPointerException when component distances between points exceeded Integer.MAXVALUE"

Fixed function: org.apache.commons.math.util.MathUtils.distance

Patch: <http://svn.apache.org/viewvc/commons/proper/math/trunk/src/main/java/org/apache/commons/math/util/MathUtils.java?r1=885027&r2=885026&pathrev=885027&diff_format=l>

```diff 
--- commons/proper/math/trunk/src/main/java/org/apache/commons/math/util/MathUtils.java	2009/11/27 21:42:25	885026
+++ commons/proper/math/trunk/src/main/java/org/apache/commons/math/util/MathUtils.java	2009/11/27 21:45:38	885027
@@ -1621,9 +1621,9 @@
      * @return the L<sub>2</sub> distance between the two points
      */
     public static double distance(int[] p1, int[] p2) {
-      int sum = 0;
+      double sum = 0;
       for (int i = 0; i < p1.length; i++) {
-          final int dp = p1[i] - p2[i];
+          final double dp = p1[i] - p2[i];
           sum += dp * dp;
       }
       return Math.sqrt(sum);
```