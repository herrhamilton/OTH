# Random Access Machine

*Tired of programming in all those boring and bloaty high level languages? Why not using a good ol' random access machine instead?*

## About

* This console program interprets an input.txt file written in random access machine (RAM) commands.
* To make it short, a RAM consists of command memory, data memory, a program counter and an accumulator, which stores the last computed value.
* Each line has a command and a value, which looks a little bit like Assembler Language.

## How To

* **Step 1:** Write your RAM file.
* **Step 2:** Provide it to the program.
* **Step 3:** ???
* **Step 4:** Profit!

Jokes aside, an **example file** is provided, which waits for an integer input, let's call it n, and then prints the sum i^i, i=1 to n.
Please use a similar syntax and do not write anything else or the program won't work.

## List of Commands

| command   | explanation		   |
|-----------|----------------------|
| ADD adr   | accu += data[adr]    |
| SUB adr   | accu -= data[adr]    |
| MUL adr   | accu *= data[adr]    |
| DIV adr   | accu /= data[adr]    |
| LDA adr   | accu = data[adr]     |
| LDK value | accu = value         |
| STA adr   | data[adr] = accu     |
| INP adr   | data[adr] = [INPUT]  |
| OUT adr   | [OUTPUT] = data[adr] |
| HLT value | [END]                |
| JMP value | count = value        |
| JEZ value | JMP if accu=0        |
| JNE value | JMP if accu!=0       |
| JLZ value | JMP if accu<0        |
| JLE value | JMP if accu<=0       |
| JGZ value | JMP if accu>0        |
| JGE value | JMP if accu>=0       |

(list of commands taken from our Algorithms & Data Structures course)