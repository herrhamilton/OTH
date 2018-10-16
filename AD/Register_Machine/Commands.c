#include "Commands.h"

void print(char *comms, int *data, int commandCount)
{
    printf("Commands to execute:\n------------------\n\n");
    for(int i=0; i<commandCount; i++)
    {
        printf("%c%c%c %d\n", comms[4*i+0], comms[4*i+1], comms[4*i+2], data[i]);
    }
    printf("\n------------------\n\n");
}

void add(int commData, int *data){
    data[0] = data[0] + data[commData];}

void sub(int commData, int *data){
    data[0] = data[0] - data[commData];}

void mul(int commData, int *data){
    data[0] = data[0] * data[commData];}

void _div(int commData, int *data){
    data[0] = data[0] / data[commData];}

void lda(int commData, int *data){
    data[0] = data[commData];}

void ldk(int commData, int *data){
    data[0] = commData;}

void sta(int commData, int *data){
    data[commData] = data[0];}

void inp(int commData, int *data){
    scanf("%d", &data[commData]);}

void out(int commData, int *data){
    printf("%d", data[commData]);}

int hlt(int commData, int *data){
    return commData;}

void jmp(int commData, int *lineCount){
    *lineCount = commData-2;} // lineCount starts at 1 and gets incremented in runMachine

void jez(int commData, int *data, int *lineCount){
    if(data[0] == 0) jmp(commData, lineCount);}

void jne(int commData, int *data, int *lineCount){
    if(data[0] != 0) jmp(commData, lineCount);}

void jlz(int commData, int *data, int *lineCount){
    if(data[0] < 0) jmp(commData, lineCount);}

void jle(int commData, int *data, int *lineCount){
    if(data[0] <= 0) jmp(commData, lineCount);}

void jgz(int commData, int *data, int *lineCount){
    if(data[0] > 0) jmp(commData, lineCount);}

void jge(int commData, int *data, int *lineCount){
    if(data[0] >= 0) jmp(commData, lineCount);}
