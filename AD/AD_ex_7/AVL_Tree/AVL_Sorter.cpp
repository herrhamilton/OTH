#include "AVL_Sorter.h"

avlsorter1::avlsorter1() {
}

avlsorter1::~avlsorter1() {
}

void avlsorter1::Sort(object* arr, int len) {
    avltree1* tree = new avltree1();
    for(int i=0; i<len; i++) {
        tree->Insert(arr[i]);
    }
    Sort(arr, 0, tree->GetRoot());
}


int avlsorter1::Sort(object*arr, int pos, avlelem1* curr) {
    if (curr!=NULL) {
        pos = Sort(arr, pos, curr->left);
        arr[pos] = curr->val;
        Sort(arr, pos+1, curr->right);
    }
    else {
        return pos;
    }
}

