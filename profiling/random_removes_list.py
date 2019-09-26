import random
import sys
import time

l = list()
LIST_SIZE = 200000
MININT = -sys.maxsize - 1

for i in range(LIST_SIZE):
    l.append(random.randint(MININT, sys.maxsize))

AMOUNT_OF_REMOVES = 50000

START_TIME = time.time()

for i in range(AMOUNT_OF_REMOVES):
    l.pop(random.randint(0, LIST_SIZE - 1))
    LIST_SIZE -= 1

print("Time taken: ", time.time() - START_TIME)
