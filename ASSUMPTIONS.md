# Assumptions

- Directed triangular graph with weighted nodes by problem description
- Minimal path is sum of weights of its nodes
- Can be computed moving from top to bottom level. The 
  minimal path of a level n+1 depends on the minimal path of level n and 
 the weights of level n. The minimal path of top level is the node weight.  
  