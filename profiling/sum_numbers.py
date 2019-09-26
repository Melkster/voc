import time

LIST_SIZE = 20000000
l = list(range(LIST_SIZE))

start = time.time()

sum = 0
for value in l:
    sum += value

end = time.time()

print("It took %f seconds to sum all numbers in a list with %d elements" %(end - start, LIST_SIZE))
