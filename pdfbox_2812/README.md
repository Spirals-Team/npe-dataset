# PDFBox 2812

Exception:
```java
testConstructor(org.apache.pdfbox.pdmodel.graphics.color.PDICCBasedTest)  Time elapsed: 0.069 sec  <<< ERROR!
java.lang.NullPointerException: null
	at org.apache.pdfbox.pdmodel.graphics.color.PDColorSpaceFactory.createColorSpace(PDColorSpaceFactory.java:258)
	at org.apache.pdfbox.pdmodel.graphics.color.PDICCBasedTest.testConstructor(PDICCBasedTest.java:47)
```

Fix Commit: [SVN Revision 1681643](http://svn.apache.org/viewvc?view=revision&revision=1681643)

Patch: <http://svn.apache.org/viewvc/pdfbox/branches/1.8/pdfbox/src/main/java/org/apache/pdfbox/pdmodel/graphics/color/PDICCBased.java?r1=1681643&r2=1681642&pathrev=1681643>

```diff
--- pdfbox/branches/1.8/pdfbox/src/main/java/org/apache/pdfbox/pdmodel/graphics/color/PDICCBased.java	2015/05/25 19:33:06	1681642
+++ pdfbox/branches/1.8/pdfbox/src/main/java/org/apache/pdfbox/pdmodel/graphics/color/PDICCBased.java	2015/05/25 19:34:06	1681643
@@ -83,7 +83,8 @@
     {
         array = new COSArray();
         array.add( COSName.ICCBASED );
-        array.add( new PDStream( doc ) );
+        stream = new PDStream(doc);
+        array.add(stream);
     }
```