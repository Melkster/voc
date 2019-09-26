import time

N_LISTS = 75
N_ITEMS = 1000000

lists = []
for i in range(N_LISTS):
    l = list(range(N_ITEMS))
    
    lists.append(l)

start = time.time()

concatenated_list = []
for l in lists:
    concatenated_list += l

end = time.time()


print("It took %f seconds to concatenate %d lists with %d items" %(end - start, N_LISTS, N_ITEMS))
