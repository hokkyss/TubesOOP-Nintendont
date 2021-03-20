#ifndef INVENTORY_HPP
#define INVENTORY_HPP

#include <iostream>
#include "Engimon.hpp"
#include "SkillItem.hpp"
#include "Skill.hpp"

using namespace std;

class FullInventory{
    public:
        static int currentCapacity;
        int maxCapacity = 15;

        FullInventory(){}
        ~FullInventory(){}
};

template <class T>
class Inventory : public FullInventory{
    public:
        vector<T> inventoryList;
        //ctor
        Inventory();

        //method
        void remove(T el);
        int find(T el);
        bool isExist(T el);
        void insert(T in);
        void showInventory();
};

#endif