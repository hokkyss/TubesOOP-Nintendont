#include "Player.hpp"
#include "Inventory.hpp"
#include <iostream>

using namespace std;

Player::Player(Engimon starter){
    this->activeEngimon = starter;
}

void Player::showAllEngimon(){
    
}

Engimon Player::getActiveEngimon() const{
    return this->activeEngimon;
}

void Player::showEngimon(Engimon e){
    int i;
    cout << "Name: " << e.name << endl;
    cout << "Parent: " << e.parent << endl;
    cout << "Species: " << e.species << endl;
    cout << "Skill: " << endl;
    for(Skill s : e.skills){
        cout << i << "." << s.name << endl;
        i++;
    }
    cout << "Element: " << endl;
    for(string elmt : e.elements){
        cout << elmt << endl;
    }
    cout << "Level: " << e.level << endl;
    cout << "Experience: " << e.exp << "/100" << endl;
    cout << "Total Experience: " << e.totalexp << endl;
}

void Player::switchActiveEngimon(Engimon e){
    this->activeEngimon = e;
}

