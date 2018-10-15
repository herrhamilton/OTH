#include "ParseInput.h"
#include "Commands.h"

void parseInput(char *fileName, struct command **commands, int *commandCount, const int MAX_COMMANDS) {
    FILE *file;
    char ffileName[30] = "input.txt";

	char command[3];
	int adrOrVal;

	int i,j = 0;

	file = fopen(fileName, "r");
	if (!file)
    {
        printf("Cannot open file %s!", fileName);
        return;
    }
    for(i=0; i<MAX_COMMANDS; i++)
    {
        (*commands)[i].command[0] = '\0';
        (*commands)[i].adrOrVal = -1;
    }

    i=0;
    do
    {
        j = fscanf(file, "%s %d", command, &adrOrVal);
        if(j==2)
        {
            (*commands)[i].command[0] = command[0];
            (*commands)[i].command[1] = command[1];
            (*commands)[i].command[2] = command[2];
            (*commands)[i].adrOrVal = adrOrVal;
            i++;
            (*commandCount)++;
            command[0] = '\0';
            adrOrVal = -1;
        }
        else break;
    } while ((*commandCount) <= 50);

    if ( (*commandCount) > 50)
    {
        printf("Too many commands in file %s! MAX_COMMANDS is %d", fileName, MAX_COMMANDS);
    }
    fclose(file);
    return;
}
