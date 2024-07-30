The programme tackles the 8 queens puzzle, which dates back to 1848. The objective is to place 8 queens on an 8x8 chessboard so that no two queens can attack each other horizontally, vertically, or diagonally. Despite the plethora of arrangements (4,426,165,368), only 92 of them are valid


### Board Representation
- The board uses two symbols

    - "Q" to represent a queen
    - "." to represent an empty space

### Fitness Function

- The fitness function evaluates the configuration of a board. A perfect solution has a fitness value of 56, indicating no clashes between the queens. Fitness is calculated as follows 



![Image](https://github.com/user-attachments/assets/2794d042-0649-4910-a6a0-c349f68432aa)


Binary Representation 
- Correct boards have a fitness of 56

- Binary strings are 24 bits and each row is represent by 3 bits

- The binary string "100010111011110000101001" represents a correct solution to the problem
