$ mvn versions:display-dependency-updates

[INFO]
[INFO] -----------------------< org.example:SauceDemo >------------------------
[INFO] Building SauceDemo 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- versions-maven-plugin:2.8.1:display-dependency-updates (default-cli) @ SauceDemo
 ---
........
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-la
ng3/3.8.1/commons-lang3-3.8.1.jar (502 kB at 150 kB/s)
[INFO] artifact org.seleniumhq.selenium:selenium-java: checking for updates from central
[INFO] artifact org.testng:testng: checking for updates from central
[INFO] artifact io.github.bonigarcia:webdrivermanager: checking for updates from central
[INFO] artifact org.seleniumhq.selenium:selenium-api: checking for updates from central
[INFO] The following dependencies in Dependencies have newer versions:
[INFO]   io.github.bonigarcia:webdrivermanager ................. 4.2.2 -> 4.3.1
[INFO]   org.seleniumhq.selenium:selenium-api ....... 3.141.59 -> 4.0.0-alpha-7
[INFO]   org.seleniumhq.selenium:selenium-java ...... 3.141.59 -> 4.0.0-alpha-7
[INFO]   org.testng:testng ..................................... 7.1.0 -> 7.3.0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  15.288 s
[INFO] Finished at: 2021-01-20T19:52:47+03:00
[INFO] ------------------------------------------------------------------------


Tests run: 18, Failures: 2, Errors: 0, Skipped: 0


$ mvn -Dtest=MenuTest#openMenuTest test
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  13.713 s
[INFO] Finished at: 2021-01-24T19:16:09+03:00
[INFO] --------------------------------------------

$ mvn -Dtest=MenuTest#openMenuTest+closeMenuTest test
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  23.026 s
[INFO] Finished at: 2021-01-24T19:17:52+03:00
[INFO] ------------------------------------------------------------------------
