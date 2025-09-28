# Assignment1

#I implementec 4 algorithms and they all have these similarities:
#Recursion:
  #QuickSort always recurses on the smaller side of partition, leaving the larger into a loop. This ensures that the depth is around O(log n).
  #Determenistic Sort also prefers the smaller side and recurses only on the side where we look for our element.
  #MergeSort switches to insertion sort for smaller tasks and doesn't touch the half that is already sorted.
  #Closest Pair brute-forces and scans the pieces once they're small enough.

#Memory:
  #MergeSort reuses one buffer array instead of creating new ones at each step.
  #QuickSort and Determenistic Sorts' use in one place.
  #Closes Pair makes several lists, but each point only gets copied O(log n) times.

#Testing approach:
  #I tried to test on various values and sometimes even duplicates.


#RECURRENCE ANALYSES
#MergeSort
#Recurrence: T(n) = 2T(n/2) + O(n) because each level merges in linear time.
#Reasoning: Using the Master Theorem, Case 2, the solution is O(n log n).
#Takeaway: depth -> log, thanks to buffers memory is predictable.

#QuickSort
#Recurrence: T(n) = T(k) + T(n-k-1) + O(n).
#Reasoning: Using the Master Theorem, Case 2, the solution is O(n log n).
#Takeaway: stack depth is connected to choosing the smaller side for recursion, hence being efficient.

#Deterministic Select
#Recurrence: T(n) = T(n/5) + T(7n/10) + O(n).
#Reasoning: Every level -> linear. By Akra-Bazzi intuition -> O(n).
#Takeaway: Worst-case linear time is guaranteed.

#Closest Pair of Points
#Recurrence: T(n) = 2T(n/2) + O(n). Sort by x, split, and strip check.
#Reasoning: Master Theorem, Case 2 -> O(n log n).
#Takeaway: Recursion depth -> logarithmic.

#Conclusion
#QuickSort was usually the fastest in practice because it works in place, while MergeSort is A BIT slower. 
#Deterministic Select achieved linear time but had noticable overhead, and lastly Closest Pair matched its n log n
#curve through with higher constants from extra data handling.


