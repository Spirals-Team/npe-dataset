PolygonSet
-------

Exception:

testTooThinBox(org.apache.commons.math3.geometry.euclidean.twod.PolygonsSetTest)  Time elapsed: 0.038 sec  <<< ERROR!
java.lang.NullPointerException: null
	at org.apache.commons.math3.geometry.partitioning.BSPTree.fitToCell(BSPTree.java:301)
	at org.apache.commons.math3.geometry.partitioning.BSPTree.insertCut(BSPTree.java:159)
	at org.apache.commons.math3.geometry.partitioning.AbstractRegion.<init>(AbstractRegion.java:171)
	at org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.<init>(PolygonsSet.java:122)
	at org.apache.commons.math3.geometry.euclidean.twod.PolygonsSetTest.testTooThinBox(PolygonsSetTest.java:41)

Commit: [SVN Revision 885027](http://svn.apache.org/viewvc?view=revision&revision=1590251)

Fixed function: PolygonSet.boxBoundary

Patch: <http://svn.apache.org/viewvc/commons/proper/math/trunk/src/main/java/org/apache/commons/math3/geometry/euclidean/twod/PolygonsSet.java?r1=1590251&r2=1590250&pathrev=1590251&diff_format=l>

