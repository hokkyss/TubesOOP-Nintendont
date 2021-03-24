#include "Inventory.hpp"
#include <iostream>

using namespace std;

int FullInventory::currentCapacity = 0;

template <class T>
Inventory<T>::Inventory(){
    maxCapacity = MAX_CAPACITY;
}

template <class T>
void Inventory<T>::remove(T el){
    inventoryList.erase(find(inventoryList.begin(),inventoryList.end(),el));
}

template <class T>
bool Inventory<T>::isExist(T el){
    return currentCapacity<maxCapacity;
}

template <class T>
bool Inventory<T>::isExist(T el){
    T elTemp = find(inventoryList.begin(),inventoryList.end(),el);
    if (elTemp != inventoryList.end()) return true;
    return false;
}

template <class T>
void Inventory<T>::insert(T in){
    if (currentCapacity < maxCapacity){ 
        if ((is_same<SkillItem,T>::value&&!isExist(in)) || is_same<Engimon,T>::value){
            inventoryList.push_back(in);
            currentCapacity++;
        }else throw ItemAlreadyExistedException();
    }
    else throw InventoryFullException();
}

template <class T>
void Inventory<T>::showInventory(){
    cout << "Inventory ";
    if (is_same<Engimon,T>::value) cout << "Engimon ";
    else if (is_same<SkillItem,T>::value) cout << "Skill Item ";
    cout << ":" << endl;

    for(int i=0;i<inventoryList.size();i++){
        cout << i+1 << ". ";
        if (is_same<SkillItem,T>::value) cout << inventoryList[i].getItemName() << endl;
        else if (is_same<Engimon,T>::value) cout << inventoryList[i].getName() << endl;
    }
}
