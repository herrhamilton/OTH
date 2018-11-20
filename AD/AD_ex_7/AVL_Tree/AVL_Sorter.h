#ifndef AVL_SORTER_H
#define AVL_SORTER_H

#include "AVL_Tree.h"
using namespace std;
typedef int object;

class avlsorter1 {
public:
    avlsorter1();
    ~avlsorter1();
    void Sort(object* arr, int len);
private:
    int Sort(object* arr, int pos, avlelem1* tree);

};

#endif // AVL_SORTER_H
