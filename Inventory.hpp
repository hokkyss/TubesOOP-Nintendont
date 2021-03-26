#ifndef INVENTORY_HPP
#define INVENTORY_HPP

#include <exception>
#include <iostream>
#include <vector>

#define MAX_CAPACITY 15

using namespace std;

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
        const int maxCapacity = MAX_CAPACITY;

        FullInventory(){}
        ~FullInventory(){}
};

template <class T>
class Inventory : public FullInventory{
    public:
        vector<T> inventoryList;
        vector<int> countSkillItem;

        //ctor
        Inventory();
        ~Inventory();

        //method
        bool isFull();
        void remove(T el);
        int findItem(T el);
        bool isExist(T el);
        void insert(T in);
        void showInventory();
};

#endif