Howto run and install plib-query project:

First you need the oracle jdbc driver. Unfortunately these are not available in public maven repositories. 

So download it or take the 10.2 version from resources directory delivered here.Check /lib/ojdbc6.jar

Then install it into your local maven repository: 
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.2.0 -Dpackaging=jar -Dfile=lib/ojdbc6.jar

NOTE: It is important that the -Dfile point to the exact file, 
otherwise the jar will not be correctly installed into the maven repository. Maven will not tell you that it is not correctly installed!!!
