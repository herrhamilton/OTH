#ifndef COMMANDS_H
#define COMMANDS_H

#include <stdio.h>
#include <stdlib.h>

void print(char *commands, int *data, int commandCount);
void add(int commData, int *data);
void sub(int commData, int *data);
void mul(int commData, int *data);
void _div(int commData, int *data);
void lda(int commData, int *data);
void ldk(int commData, int *data);
void sta(int commData, int *data);
void inp(int commData, int *data);
void out(int commData, int *data);
int hlt(int commData, int *data);

void jmp(int commData, int *lineCount);
void jez(int commData, int *data, int *lineCount);
void jne(int commData, int *data, int *lineCount);
void jlz(int commData, int *data, int *lineCount);
void jle(int commData, int *data, int *lineCount);
void jgz(int commData, int *data, int *lineCount);
void jge(int commData, int *data, int *lineCount);

#endif // COMMANDS_H
