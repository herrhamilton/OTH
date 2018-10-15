#include "Commands.h"

void print(struct command **comms, int commandCount)
{
    for(int i=0; i<commandCount; i++)
    {
        printf("%c%c%c %d\n", (*comms)[i].command[0], (*comms)[i].command[1], (*comms)[i].command[2], (*comms)[i].adrOrVal);
    }

}
