#include "ParseInput.h"
#include "Commands.h"

void parseInput(char *fileName, char **commands, int **data, int *commandCount, const int MAX_COMMANDS) {
    FILE *file;
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
        (*commands)[i] = '\0';
        (*data)[i] = -1;
    }

    i=0;
    do
    {
        // this line is needed to make fscanf work WTF
        char *buf = malloc(sizeof(char) * 10);
        j = fscanf(file, "%s %d", command, &adrOrVal);
        //fgets(command, 4, file);
        //fgets(buf, 2, file);
        //fgets(buf, 10, file);
        //printf("%s %d\n", command, adrOrVal);
        if(j==2)
        {
            (*data)[i] = adrOrVal;
            (*commands)[i++] = command[0];
            (*commands)[i++] = command[1];
            (*commands)[i++] = command[2];
            (*commands)[i++] = '\0';
            (*commandCount)++;
            command[0] = '\0';
            adrOrVal = -1;
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
