#ifndef INVENTORY_HPP
#define INVENTORY_HPP

#include "Engimon.hpp"
#include "SkillItem.hpp"

class Inventory{
    private:
        Engimon* lEngimons;
        SkillItem* lSkillItems;
        int maxCapacity;

    public:
        Inventory();
        Inventory(Engimon* listE, SkillItem* listS, int max);
        ~Inventory();

        Engimon* getEngimonList();
        SkillItem getSkillItemList();
        void insertEngimon(Engimon e);
        void insertSkillItem(SkillItem si);
        void showInventory();
};

#endif