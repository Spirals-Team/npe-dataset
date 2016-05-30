# Math 988b

Exception:
```java
testIntersectionNotIntersecting(org.apache.commons.math3.geometry.euclidean.threed.SubLineTest)  Time elapsed: 0.058 sec  <<< ERROR!
java.lang.NullPointerException: null
	at org.apache.commons.math3.geometry.euclidean.threed.Line.getAbscissa(Line.java:114)
	at org.apache.commons.math3.geometry.euclidean.threed.Line.toSubSpace(Line.java:129)
	at org.apache.commons.math3.geometry.euclidean.threed.SubLine.intersection(SubLine.java:120)
	at org.apache.commons.math3.geometry.euclidean.threed.SubLineTest.testIntersectionNotIntersecting(SubLineTest.java:160)
```

Fix Commit: [SVN Revision 1488866](http://svn.apache.org/viewvc?view=revision&revision=1488866)

Patch: <http://svn.apache.org/viewvc/commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/threed/SubLine.java?r1=1488866&r2=1488865&pathrev=1488866>

```diff
--- commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/threed/SubLine.java	2013/06/03 07:12:06	1488865
+++ commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/threed/SubLine.java	2013/06/03 07:15:00	1488866
@@ -111,6 +111,9 @@
 
         // compute the intersection on infinite line
         Vector3D v1D = line.intersection(subLine.line);
+        if (v1D == null) {
+            return null;
+        }
 
         // check location of point with respect to first sub-line
         Location loc1 = remainingRegion.checkPoint(line.toSubSpace(v1D));
```