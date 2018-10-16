#include "ParseInput.h"
#include "Commands.h"

void parseInput(char *fileName, char **commands, int **commData, int *commandCount, const int MAX_COMMANDS) {
    FILE *file;
	char command[3];
	int commVal;

	int i = 0;

	file = fopen(fileName, "r");
	if (!file)
    {
        printf("Cannot open file %s!", fileName);
        return;
    }
    for(i=0; i<MAX_COMMANDS; i++)
    {
        (*commands)[i] = '\0';
        (*commData)[i] = -1;
    }
    (*commData)[0] = 0;

    i=0;
    do
    {
        // this line is needed to make fscanf work - But WHY?? ... A buffer problem? If you have a fix, tell me!
        int useless = 42;
        if(fscanf(file, "%s %d", command, &commVal)==2)
        {
            (*commData)[*commandCount] = commVal;
            (*commands)[i++] = command[0];
            (*commands)[i++] = command[1];
            (*commands)[i++] = command[2];

            (*commands)[i++] = '\0';
            (*commandCount)++;
        }
        else break;
    } while ((*commandCount) <= MAX_COMMANDS);

    if ( (*commandCount) > MAX_COMMANDS)
    {
        printf("Too many commands in file %s! MAX_COMMANDS is %d", fileName, MAX_COMMANDS);
    }
    fclose(file);
    return;
}
