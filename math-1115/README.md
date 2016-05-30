# Math 1115

Exception:
```java
testTooThinBox(org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSetTest)  Time elapsed: 0.032 sec  <<< ERROR!
java.lang.NullPointerException: null
	at org.apache.commons.math3.geometry.partitioning.BSPTree.fitToCell(BSPTree.java:301)
	at org.apache.commons.math3.geometry.partitioning.BSPTree.insertCut(BSPTree.java:159)
	at org.apache.commons.math3.geometry.partitioning.RegionFactory.buildConvex(RegionFactory.java:55)
	at org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.buildBoundary(PolyhedronsSet.java:215)
	at org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.<init>(PolyhedronsSet.java:120)
	at org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet.<init>(PolyhedronsSet.java:185)
	at org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSetTest.testTooThinBox(PolyhedronsSetTest.java:25)
```

Fix Commit: [SVN Revision 1590254](http://svn.apache.org/viewvc?view=revision&revision=1590254)

Patch: <http://svn.apache.org/viewvc/commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/threed/PolyhedronsSet.java?r1=1590254&r2=1590253&pathrev=1590254>

```diff
--- commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/threed/PolyhedronsSet.java	2014/04/26 17:31:21	1590253
+++ commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/threed/PolyhedronsSet.java	2014/04/26 17:36:34	1590254
@@ -59,6 +59,16 @@
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
@@ -190,6 +200,10 @@
                                                       final double yMin, final double yMax,
                                                       final double zMin, final double zMax,
                                                       final double tolerance) {
+        if ((xMin >= xMax - tolerance) || (yMin >= yMax - tolerance) || (zMin >= zMax - tolerance)) {
+            // too thin box, build an empty polygons set
+            return new BSPTree<Euclidean3D>(Boolean.FALSE);
+        }
         final Plane pxMin = new Plane(new Vector3D(xMin, 0,    0),   Vector3D.MINUS_I, tolerance);
         final Plane pxMax = new Plane(new Vector3D(xMax, 0,    0),   Vector3D.PLUS_I,  tolerance);
         final Plane pyMin = new Plane(new Vector3D(0,    yMin, 0),   Vector3D.MINUS_J, tolerance);
```