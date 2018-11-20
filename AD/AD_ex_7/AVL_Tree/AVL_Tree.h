#ifndef AVL_TREE_H
#define AVL_TREE_H

#include <iostream>

using namespace std;
typedef int object;

class avlelem1 {
    public: int height; object val;
    avlelem1 *left, *right;
};
class avltree1 {
        private: avlelem1 *root;
        int Max(int a, int b);
        int GetHeight(avlelem1 *elem);
        void UpdateHeight(avlelem1 *elem);
        void Insert(avlelem1* &elem,object o);
        void DeleteTree(avlelem1 *root);
        void CheckRotationRight(avlelem1* &elem);
        void CheckRotationLeft(avlelem1* &elem);
        void Print(avlelem1 *curr);
        void RotateLeft(avlelem1* &a);
        void DoubleRotationLeft(avlelem1* &a);
        void RotateRight(avlelem1* &a);
        void DoubleRotationRight(avlelem1* &a);
        public: avltree1(); ~avltree1();
        avlelem1* GetRoot();
        void Insert(object o);
        void Delete(object o);
        void Delete(avlelem1* &a, object o);
        void Print();
        avlelem1* SwitchElems(avlelem1* &elem);
        avlelem1* FindElemToSwitch(avlelem1* &prev, avlelem1* &elem);
};

#endif // AVL_TREE_H

