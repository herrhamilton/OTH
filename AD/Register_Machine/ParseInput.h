#ifndef PARSE_INPUT_H
#define PARSE_INPUT_H

#include <stdio.h>
#include <stdlib.h>
#include "Commands.h"
void parseInput(char *fileName, struct command** command_ptr, int* commandCount, const int MAX_COMMANDS);
#endif // PARSE_INPUT_H