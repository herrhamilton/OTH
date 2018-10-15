#ifndef COMMANDS_H
#define COMMANDS_H

#include <stdio.h>
#include <stdlib.h>
// e.g. ADD 4
struct command
{
    char command[3];
    int adrOrVal;
};

void print(struct command **commands, int commandCount);
int add(int address);
int mul;
#endif // COMMANDS_H
