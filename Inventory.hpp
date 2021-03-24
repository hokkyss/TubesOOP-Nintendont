#ifndef INVENTORY_HPP
#define INVENTORY_HPP

#include <iostream>
#include <exception>
#include "Engimon.hpp"
#include "SkillItem.hpp"
#include "Skill.hpp"

using namespace std;

#define MAX_CAPACITY 15

class ItemAlreadyExistedException : exception
{
	const char* what() const throw()
	{
		return "Item already owned!";
	}
};

class InventoryFullException : exception
{
	const char* what() const throw()
	{
		return "Inventory is full!";
	}
};

class EngimonNotFoundException : exception
{
	const char* what() const throw()
	{
		return "Engimon not found!";
	}
};

class FullInventory{
    public:
        static int currentCapacity;
        int maxCapacity;

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
        bool isFull();
        void remove(T el);
        bool isExist(T el);
        void insert(T in);
        void showInventory();
};

#endif