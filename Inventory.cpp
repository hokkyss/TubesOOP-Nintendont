#include "Inventory.hpp"
#include "Engimon.hpp"
#include "SkillItem.hpp"

using namespace std;

template <class T>
Inventory<T>::Inventory() : FullInventory(){}

template <class T>
Inventory<T>::~Inventory(){}

template <class T>
void Inventory<T>::remove(T el){
    int idx = findItem(el);
    if(countSkillItem[idx]==1){
        inventoryList.erase(inventoryList.begin()+idx);
        countSkillItem.erase(countSkillItem.begin()+idx);
    }else {
        countSkillItem[idx]--;
    }
    currentCapacity--;
} 

template <class T>
bool Inventory<T>::isFull(){
    return currentCapacity>=maxCapacity;
}

template <class T>
int Inventory<T>::findItem(T el){
    for (int i=0; i<inventoryList.size(); i++){
        if (inventoryList[i]==el){
            return i;
        }
    }
    return -1;
}

template <class T>
bool Inventory<T>::isExist(T el){
    int idx = findItem(el);
    if (idx != -1) return true;
    return false;
}

template <class T>
void Inventory<T>::insert(T in){
    if (currentCapacity < maxCapacity){
        if ((is_same<SkillItem,T>::value&&!isExist(in)) || is_same<Engimon,T>::value){
            inventoryList.push_back(in);
            countSkillItem.push_back(1);
            currentCapacity++;
        } else if (is_same<SkillItem,T>::value&&isExist(in)){
            int idx = findItem(in);
            countSkillItem[idx]++;
        }
    }
    else{
        throw InventoryFullException();
    }
}

template <class T>
void Inventory<T>::showInventory(){
    cout << "Inventory ";
    if (is_same<Engimon,T>::value) cout << "Engimon ";
    else if (is_same<SkillItem,T>::value) cout << "Skill Item "; 
    cout << "(" << currentCapacity << "/" << maxCapacity << ")" << ":" << endl;

    for(int i=0;i<inventoryList.size();i++){
        cout << i+1 << ". ";
        cout << inventoryList[i].getName();
        if (is_same<SkillItem,T>::value) cout << " - " << countSkillItem[i] << endl;
        else cout << endl; 
    }
}

/* int main (){
    Inventory<Engimon> engimonList;
    Inventory<SkillItem> skillList;
    Engimon *e = new Engimon("Emberon","Emberon");

    engimonList.insert(*e);
    skillList.insert(TM01);

    engimonList.showInventory();
    skillList.showInventory();
} */
