import time

N_ITEMS = 60000

l = []

start = time.time()

for item in range(N_ITEMS):
    l.insert(0, item)


end = time.time()

print("It took %f seconds to insert %d items into the beginning of a list" %(end - start, N_ITEMS))

