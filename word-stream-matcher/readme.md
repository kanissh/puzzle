A list of words are given as facts. Consider the words lion, cat, ant.

Then a stream of characters is given as the input. If any continuous chars in the stream matches with the
chars of any words in the fact then it should print true at the moment the character being evaluated is the last
char of the word in facts. In all other situation it should print false.

consider the char stream abclioncarantuio

the output should be  
a - false  
b - false  
c - false  
l - false  
i - false  
o - false  
n - true  
c - false  
a - false  
r - false  
a - false  
n - false  
t - true  
u - false  
i - false  
o - false  
