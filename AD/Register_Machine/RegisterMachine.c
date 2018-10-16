#include <string.h>
#include "RegisterMachine.h"

int runMachine(char *commands, int *commData, int commandCount, int MAX_COMMANDS, char *fileName)
{
    int *data = malloc(sizeof(int) * MAX_COMMANDS);
    data[0] = 0;
    int lineCount=0;
    char command[4];
    command[3] = '\0';
    while(lineCount < commandCount)
    {

        command[0] = commands[lineCount*4];
        command[1] = commands[lineCount*4+1];
        command[2] = commands[lineCount*4+2];

        if      (strcmp(command, "ADD") == 0) add(commData[lineCount], data);
        else if (strcmp(command, "SUB") == 0) sub(commData[lineCount], data);
        else if (strcmp(command, "MUL") == 0) mul(commData[lineCount], data);
        else if (strcmp(command, "DIV") == 0) _div(commData[lineCount], data);
        else if (strcmp(command, "LDA") == 0) lda(commData[lineCount], data);
        else if (strcmp(command, "LDK") == 0) ldk(commData[lineCount], data);
        else if (strcmp(command, "STA") == 0) sta(commData[lineCount], data);
        else if (strcmp(command, "INP") == 0) inp(commData[lineCount], data);
        else if (strcmp(command, "OUT") == 0) out(commData[lineCount], data);
        else if (strcmp(command, "HLT") == 0) hlt(commData[lineCount], data);

        else if (strcmp(command, "JMP") == 0) jmp(commData[lineCount], &lineCount);
        else if (strcmp(command, "JEZ") == 0) jez(commData[lineCount], data, &lineCount);
        else if (strcmp(command, "JNE") == 0) jne(commData[lineCount], data, &lineCount);
        else if (strcmp(command, "JLZ") == 0) jlz(commData[lineCount], data, &lineCount);
        else if (strcmp(command, "JLE") == 0) jle(commData[lineCount], data, &lineCount);
        else if (strcmp(command, "JGZ") == 0) jgz(commData[lineCount], data, &lineCount);
        else if (strcmp(command, "JGE") == 0) jge(commData[lineCount], data, &lineCount);
        else
        {
            printf("Wrong Command Input at line %d! Please check your file.", lineCount+2);
            return -1;
        }
        lineCount++;
    }
    free(data);
    free(fileName);
    free(commands);
    free(commData);
    return 0;
}
