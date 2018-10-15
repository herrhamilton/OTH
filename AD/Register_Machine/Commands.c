#include "Commands.h"

void print(struct command **comms, int commandCount)
{
    for(int i=0; i<commandCount; i++)
    {
        printf("%c%c%c %d\n", (*comms)[i].command[0], (*comms)[i].command[1], (*comms)[i].command[2], (*comms)[i].adrOrVal);
    }
}
/*
// a = a + *adr
void add(int *accu, int *address)
{
    *accu = *accu + address;
}
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
