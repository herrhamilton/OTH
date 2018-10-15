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

void print(char *commands, int *data, int commandCount);
void add(int *data, int *lineCount);
int mul;
#endif // COMMANDS_H
