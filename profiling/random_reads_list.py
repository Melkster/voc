import random
import sys
import time

l = list()
LIST_SIZE = 100000
MININT = -sys.maxsize - 1

for i in range(LIST_SIZE):
    l.append(random.randint(MININT, sys.maxsize))

AMOUNT_OF_READS = 1000000

start_time = time.time()

for i in range(AMOUNT_OF_READS):
    tmp = l[random.randint(0, LIST_SIZE - 1)]

print("Time taken: ", time.time() - start_time)
