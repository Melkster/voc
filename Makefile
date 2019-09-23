.PHONY: test compile_test coverage

SUPPORT_LIBRARY = support_libraries
JAVA_ROOT = build/java

compile_test:
	ant java

test: compile_test
	java -cp ".:${SUPPORT_LIBRARY}/hamcrest-core-1.3.jar:${SUPPORT_LIBRARY}/junit-4.13-beta-3.jar:${JAVA_ROOT}" org.junit.runner.JUnitCore org.python.types.ListTest

coverage: compile_test
	java -javaagent:${SUPPORT_LIBRARY}/jacocoagent.jar -cp ".:${SUPPORT_LIBRARY}/hamcrest-core-1.3.jar:${SUPPORT_LIBRARY}/junit-4.13-beta-3.jar:${JAVA_ROOT}" org.junit.runner.JUnitCore org.python.types.ListTest
	java -jar ${SUPPORT_LIBRARY}/jacococli.jar report jacoco.exec --classfiles build/java --sourcefiles python/common --html report
