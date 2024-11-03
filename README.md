# NpeFix Dataset

The dataset contains 13 null pointer exception bugs from 6 Apache projects (previously 17, but some have rotted).

If you use this benchmark, please cite:

Thomas Durieux, Benoit Cornu, Lionel Seinturier and Martin Monperrus, "[Dynamic Patch Generation for Null Pointer Exceptions Using Metaprogramming](https://hal.archives-ouvertes.fr/hal-01419861/document)", In IEEE International Conference on Software Analysis, Evolution and Reengineering, 2017.
Bibtex Entry:

    @inproceedings{durieuxNpeFix,
        title = {{Dynamic Patch Generation for Null Pointer Exceptions Using Metaprogramming}},
        author = {Durieux, Thomas and Cornu, Benoit and Seinturier, Lionel and Monperrus, Martin},
        url = {https://hal.archives-ouvertes.fr/hal-01419861/document},
        booktitle = {{IEEE International Conference on Software Analysis, Evolution and Reengineering}},
        year = {2017},
    }

## Sources

Projects:
* [Apache Commons Collections](https://commons.apache.org/proper/commons-collections/)
* [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/)
* [Apache Commons Math](https://commons.apache.org/proper/commons-math/) 
* [Apache Felix](http://felix.apache.org/)
* [Apache PDFBox](https://pdfbox.apache.org/)
* [Apache Sling](https://sling.apache.org/)

Commits:
| Project                    | Bug ID          | SVN ID  |
|----------------------------|-----------------|---------|
| Apache Commons Collections | [COLLECTIONS-331](https://issues.apache.org/jira/browse/COLLECTIONS-331) | [1079587](http://svn.apache.org/viewvc?view=revision&revision=1079587) |
| Apache Commons Collections | [COLLECTIONS-360](https://issues.apache.org/jira/browse/COLLECTIONS-360) | [1076034](http://svn.apache.org/viewvc?view=revision&revision=1076034) |
| Apache Commons Lang        | [LANG-304](https://issues.apache.org/jira/browse/LANG-304)        | [489749](http://svn.apache.org/viewvc?view=revision&revision=489749)   |
| Apache Commons Lang        | [LANG-587](https://issues.apache.org/jira/browse/LANG-587)        | [907102](http://svn.apache.org/viewvc?view=revision&revision=907102)   |
| Apache Commons Lang        | [LANG-703](https://issues.apache.org/jira/browse/LANG-703)        | [1142381](http://svn.apache.org/viewvc?view=revision&revision=1142381) |
| Apache Commons Math        | [MATH-290](https://issues.apache.org/jira/browse/MATH-290)        | [807923](http://svn.apache.org/viewvc?view=revision&revision=807923)   |
| Apache Commons Math        | [MATH-305](https://issues.apache.org/jira/browse/MATH-305)        | [885027](http://svn.apache.org/viewvc?view=revision&revision=885027)   |
| Apache Commons Math        | [MATH-369](https://issues.apache.org/jira/browse/)        | [940565](http://svn.apache.org/viewvc?view=revision&revision=940565)   |
| Apache Commons Math        | [MATH-988a](https://issues.apache.org/jira/browse/MATH-988)       | [1488866](http://svn.apache.org/viewvc?view=revision&revision=1488866) |
| Apache Commons Math        | [MATH-988b](https://issues.apache.org/jira/browse/MATH-988)       | [1488866](http://svn.apache.org/viewvc?view=revision&revision=1488866) |
| Apache Commons Math        | [MATH-1115](https://issues.apache.org/jira/browse/MATH-1115)       | [1590254](http://svn.apache.org/viewvc?view=revision&revision=1590254) |
| Apache Commons Math        | [MATH-1117](https://issues.apache.org/jira/browse/MATH-1117)       | [1590251](http://svn.apache.org/viewvc?view=revision&revision=1590251) |
| Apache Felix               | [FELIX-4960](https://issues.apache.org/jira/browse/FELIX-4960)      | [1691137](http://svn.apache.org/viewvc?view=revision&revision=1691137) |
| Apache PDFBox              | [PDFBOX-2812](https://issues.apache.org/jira/browse/PDFBOX-2812)     | [1681643](http://svn.apache.org/viewvc?view=revision&revision=1681643) |
| Apache PDFBox              | [PDFBOX-2965](https://issues.apache.org/jira/browse/PDFBOX-2965)     | [1701905](http://svn.apache.org/viewvc?view=revision&revision=1701905) |
| Apache PDFBox              | [PDFBOX-2995](https://issues.apache.org/jira/browse/PDFBOX-2995)     | [1705415](http://svn.apache.org/viewvc?view=revision&revision=1705415) |
| Apache Sling               | [SLING-4982](https://issues.apache.org/jira/browse/SLING-4982)      | [1700424](http://svn.apache.org/viewvc?view=revision&revision=1700424) |

## Compatibility

Some dependencies, on Nov 2024, 13/17 bugs can still be executed with Java 8:

```sh
$ export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
$  mvn -version
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: /usr/share/maven
Java version: 1.8.0_382, vendor: Private Build, runtime: /usr/lib/jvm/java-8-openjdk-amd64/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "5.4.0-163-generic", arch: "amd64", family: "unix"
$  for i in `find . -type d -mindepth 1 -maxdepth 1`; do cd $i; mvn test 2>&1 | tee ../$i-java8.log; cd ..; done
$ $ grep -l NullPointerException *.log
lang-304-java8.log
lang-587-java8.log
lang-703-java8.log
math-1115-java8.log
math-1117-java8.log
math-290-java8.log
math-305-java8.log
math-369-java8.log
math-988a-java8.log
math-988b-java8.log
pdfbox_2965-java8.log
pdfbox_2995-java8.log
sling_4982-java8.log
```
