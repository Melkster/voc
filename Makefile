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

profile_sort_large_list: compile profiling/SortLargeList.java
	javac -cp "profiling:build/java" profiling/SortLargeList.java
	java -cp "profiling:build/java" SortLargeList
