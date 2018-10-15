#include <stdio.h>
#include <stdlib.h>

#include "Commands.h"
#include "ParseInput.h"

#define MAX_COMMANDS 50
#define MAX_FILENAME_LENGTH 10

int main(int argc, char *argv[])
{
    char *fileName = malloc(sizeof(char) * MAX_FILENAME_LENGTH);
    char *comms = malloc(sizeof(char) * 4 * MAX_COMMANDS); // e.g. "ADD\0"
    int *data = malloc(sizeof(int) * MAX_COMMANDS);
    int commandCount = 0;
    int currentCommand = 0;

    switch(argc)
    {
        case 2: fileName = argv[1];
            break;
        case 1: printf("Please supply a .txt file to run!\n");
            return 0;
        default: printf("Please suppy only one .txt file to run!\n");
            return 0;
    }
    parseInput(fileName, &comms, &data, &commandCount, MAX_COMMANDS);
    print(comms, data, commandCount);
}
