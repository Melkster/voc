import random
import sys
import time

l = list()
LIST_SIZE = 2500000
MININT = -sys.maxsize - 1

for i in range(LIST_SIZE):
    l.append(random.randint(MININT, sys.maxsize))

start_time = time.time()

l.sort()

print("Time taken: ", time.time() - start_time)
