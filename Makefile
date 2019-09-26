.PHONY: test compile coverage

SUPPORT_LIBRARY = support_libraries
JAVA_ROOT = build/java

compile:
	ant java

test: compile
	java -cp ".:${SUPPORT_LIBRARY}/hamcrest-core-1.3.jar:${SUPPORT_LIBRARY}/junit-4.13-beta-3.jar:${JAVA_ROOT}" org.junit.runner.JUnitCore org.python.types.ListTest

coverage: compile
	java -javaagent:${SUPPORT_LIBRARY}/jacocoagent.jar -cp ".:${SUPPORT_LIBRARY}/hamcrest-core-1.3.jar:${SUPPORT_LIBRARY}/junit-4.13-beta-3.jar:${JAVA_ROOT}" org.junit.runner.JUnitCore org.python.types.ListTest
	java -jar ${SUPPORT_LIBRARY}/jacococli.jar report jacoco.exec --classfiles build/java --sourcefiles python/common --html report

profile_sum_numbers: compile profiling/SumNumbers.java
	javac -cp "profiling:build/java" profiling/SumNumbers.java
	java -cp "profiling:build/java" SumNumbers

profile_insert_many: compile profiling/InsertMany.java
	javac -cp "profiling:build/java" profiling/InsertMany.java
	java -cp "profiling:build/java" InsertMany

profile_random_removes: compile profiling/RandomRemovesList.java
	javac -cp "profiling:build/java" profiling/RandomRemovesList.java
	java -cp "profiling:build/java" RandomRemovesList