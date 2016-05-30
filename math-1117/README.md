# Math 1117

Exception:
```java
testTooThinBox(org.apache.commons.math3.geometry.euclidean.twod.PolygonsSetTest)  Time elapsed: 0.038 sec  <<< ERROR!
java.lang.NullPointerException: null
	at org.apache.commons.math3.geometry.partitioning.BSPTree.fitToCell(BSPTree.java:301)
	at org.apache.commons.math3.geometry.partitioning.BSPTree.insertCut(BSPTree.java:159)
	at org.apache.commons.math3.geometry.partitioning.AbstractRegion.<init>(AbstractRegion.java:171)
	at org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.<init>(PolygonsSet.java:122)
	at org.apache.commons.math3.geometry.euclidean.twod.PolygonsSetTest.testTooThinBox(PolygonsSetTest.java:41)
```

Commit: [SVN Revision 885027](http://svn.apache.org/viewvc?view=revision&revision=1590251)

Fixed function: PolygonSet.boxBoundary

Patch: <http://svn.apache.org/viewvc/commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/twod/PolygonsSet.java?r1=1590251&r2=1590250&pathrev=1590251&diff_format=l>

```diff
--- commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/twod/PolygonsSet.java	2014/04/26 16:53:22	1590250
+++ commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/twod/PolygonsSet.java	2014/04/26 16:55:11	1590251
@@ -64,6 +64,16 @@
      * cells). In order to avoid building too many small objects, it is
      * recommended to use the predefined constants
      * {@code Boolean.TRUE} and {@code Boolean.FALSE}</p>
+     * <p>
+     * This constructor is aimed at expert use, as building the tree may
+     * be a difficult taks. It is not intended for general use and for
+     * performances reasons does not check thoroughly its input, as this would
+     * require walking the full tree each time. Failing to provide a tree with
+     * the proper attributes, <em>will</em> therefore generate problems like
+     * {@link NullPointerException} or {@link ClassCastException} only later on.
+     * This limitation is known and explains why this constructor is for expert
+     * use only. The caller does have the responsibility to provided correct arguments.
+     * </p>
      * @param tree inside/outside BSP tree representing the region
      * @param tolerance tolerance below which points are considered identical
      * @since 3.3
@@ -219,6 +229,10 @@
     private static Line[] boxBoundary(final double xMin, final double xMax,
                                       final double yMin, final double yMax,
                                       final double tolerance) {
+        if ((xMin >= xMax - tolerance) || (yMin >= yMax - tolerance)) {
+            // too thin box, build an empty polygons set
+            return null;
+        }
         final Vector2D minMin = new Vector2D(xMin, yMin);
         final Vector2D minMax = new Vector2D(xMin, yMax);
         final Vector2D maxMin = new Vector2D(xMax, yMin);
```