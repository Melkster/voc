.PHONY: test

SUPPORT_LIBRARY = support_libraries
JAVA_ROOT = build/java

test:
	ant java
	java -cp ".:${SUPPORT_LIBRARY}/hamcrest-core-1.3.jar:${SUPPORT_LIBRARY}/junit-4.13-beta-3.jar:${JAVA_ROOT}" org.junit.runner.JUnitCore org.python.types.ListTest
