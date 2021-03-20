#include "Inventory.hpp"
#include <iostream>

using namespace std;

int FullInventory::currentCapacity = 0;

template <class T>
Inventory<T>::Inventory(){
    maxCapacity = 15;
}

template <class T>
void Inventory<T>::remove(T el){
    inventoryList.erase(find(el));
}

template <class T>
int Inventory<T>::find(T el){
    for(int i=0;i<inventoryList.size();i++){
        if(inventoryList[i] == el) return i;
    }
    throw el;
    return -1;
}

template <class T>
bool Inventory<T>::isExist(T el){
    for(auto i=inventoryList.begin();i!=inventoryList.end();++i){
        if(*i == el) return true;
    }
    return false;
}

template <class T>
void Inventory<T>::insert(T in){
    if (currentCapacity < maxCapacity){ 
        if ((is_same<SkillItem,T>::value&&!isExist(in)) || is_same<Engimon,T>::value){
            inventoryList.push_back(in);
            currentCapacity++;
        }else throw in;
    }
    else throw maxCapacity;
}

template <class T>
void Inventory<T>::showInventory(){
    cout << "Inventory ";
    if (is_same<Engimon,T>::value) cout << "Engimon ";
    else if (is_same<SkillItem,T>::value) cout << "Skill Item ";
    cout << ":" << endl;

    for(int i=0;i<inventoryList.size();i++){
        cout << i+1 << ". " << inventoryList[i].nama << endl;
    }
}

/* int main(){
    Engimon pikachu;
    Skill FlameThrower(90, Fire);
    SkillItem TM27(FlameThrower, "TM27");

    Inventory<Engimon> inven;
    inven.insert(pikachu);
    cout << inven.currentCapacity << endl;

    Inventory<SkillItem> inven2;
    inven2.insert(TM27);
    cout << inven2.currentCapacity << endl;

    inven.showInventory();
    inven2.showInventory();
} */