#include "Commands.h"

void print(char *comms, int *data, int commandCount)
{
    for(int i=0; i<commandCount; i++)
    {
        printf("%c%c%c %d\n", comms[4*i+0], comms[4*i+1], comms[4*i+2], data[4*i]);
    }
}

// a = a + *adr
void add(int *data, int *lineCount)
{
    data[0] = data[0] + data[*lineCount];
    *lineCount++;
}
/*
sub // a = a - *adr
mul // a = a * *adr
div // a = a / *adr
lda // a = *adr
ldk // a = nbr
sta // *adr = a
inp // *a = input
out // out = *a
hlp // ende

jmp // PC = adr
jez // a=0? pc = adr
jne // a!=0? pc = adr
jlz // a<0? pc = adr
jle // a<=0? pc = adr
jgz // a>0? pc = adr
jge // f>=0? pc = adr
*/
