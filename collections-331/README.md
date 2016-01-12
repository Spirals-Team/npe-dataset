CollatingIterator
-----------------

This project requires Java 7

    export JAVA_HOME=/home/martin/martin-no-backup/opt/jdk1.7.0_10/jre
    
    $ mvn --version
    Apache Maven 3.3.9
    Maven home: /usr/share/maven
    Java version: 1.8.0_40, vendor: Oracle Corporation
    Java home: /home/martin/martin-no-backup/jdk1.8.0_40/jre
    Default locale: en_US, platform encoding: UTF-8
    OS name: "linux", version: "4.3.0-1-amd64", arch: "amd64", family: "unix"

Exception:

    testNullComparator(org.apache.commons.collections.iterators.TestCollatingIterator)  Time elapsed: 0.004 sec  <<< ERROR!
    java.lang.NullPointerException: null
            at org.apache.commons.collections.iterators.TestCollatingIterator.testNullComparator(TestCollatingIterator.java:250)

The NPE happens in test code, because `NPE.getMessage()` returns null. The fix is to throw an carefully crafted NPE with a message (instead of the original vanilla NPE).
            
Fix Commit: [SVN Revision 1079587](http://svn.apache.org/viewvc?view=revision&revision=1079587)

Patch: <http://svn.apache.org/viewvc/commons/proper/collections/trunk/src/java/org/apache/commons/collections/iterators/ollatingIterator.java?r1=1079587&r2=1079586&pathrev=1079587&diff_format=l>

Fix in CollatingIterator.least:

        if (comparator == null) {
            throw new NullPointerException("You must invoke setComparator() to set a compator first.");
        }

