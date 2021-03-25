/* Libraries */
#include <iostream>
#include <algorithm>
#include <time.h>

/* Classes */
#include "Element.hpp"
#include "Engimon.hpp"
#include "Player.hpp"
#include "Skill.hpp"
#include "SkillItem.hpp"
#include "Inventory.hpp"
#include "Species.hpp"
#include "Direction.hpp"

/* Utilities */
#include "Utilities.hpp"

using namespace std;

#define RESPAWN_TURN 10
#define MAX_WILD_ENGIMON 10
#define LEVEL_BIG_WILD_POKEMON 15

/* Static Data */
vector<vector<char>> tabPeta;
// Matriks Peta
// Format Input in file.txt (no space):
// -----ooo
// --oooo--
// ---oo---
// --oo----
// --oooo--
// ---oo---
// ---o----
// --------
// Access: tabPeta[i][j] if (0 < i < tabPeta.size() and 0 < j < tabPeta[i].size() )

/* Dynamic Data */
EngimonLiar* wildEngimons[MAX_WILD_ENGIMON];
// Array of Engimon Liar

void initPeta(string filePath) {
    ifstream petaFile(filePath);
    string textLine;

    while (getline(petaFile, textLine))
    {
        vector<char> rowPeta;

        for (char c : textLine)
        {
            if (c == '-' || c == 'o') rowPeta.push_back(c);
        }

        tabPeta.push_back(rowPeta);
    }
}

bool isCellOccupied(Position p, Player player, Position prev) {
    if (player.getPosition() == p)
        return true;

    if (prev == p)
        return true;

    for (EngimonLiar* e : wildEngimons)
    {
        if (e != NULL) {
            if (e->getPosition() == p)
                return true;
        }
    }

    return false;
}

void spawnWildEngimons(Player player, Position prev) {
    srand(time(NULL));
    for (int i = 0; i < MAX_WILD_ENGIMON; i++) {
        Species s = listOfSpecies[rand() % listOfSpecies.size()];
        vector<Element> elements = s.getElements();
        Position p;

        bool seaEngimon = (find(elements.begin(), elements.end(), Water) != elements.end() || find(elements.begin(), elements.end(), Ice) != elements.end() );
        bool groundEngimon = (find(elements.begin(), elements.end(), Ground) != elements.end() ||
            find(elements.begin(), elements.end(), Electric) != elements.end() ||
            find(elements.begin(), elements.end(), Fire) != elements.end());
        
        p = Position(rand() % tabPeta[0].size(), rand() % tabPeta.size());

        if (!isCellOccupied(p, player, prev) && 
            (seaEngimon && tabPeta[p.getY()][p.getX()] == 'o') || 
            (groundEngimon && tabPeta[p.getY()][p.getX()] == '-'))
            wildEngimons[i] = new EngimonLiar(s, p, (rand() % player.getActiveEngimon().getLevel() * 2) + 1);
    }
}

EngimonLiar* getEngimonInCell(Position p) {
    for (EngimonLiar* e : wildEngimons)
    {
        if (e != NULL) {
            if (e->getPosition() == p)
                return e;
        }
    }

    return NULL;
}

void printEngimonInPeta(EngimonLiar e) {
    vector<Element> elmts = e.getElements();
    if (elmts.size() >= 2) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "S";
        else
            cout << "s";
    } else if (find(elmts.begin(), elmts.end(), Water) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "W";
        else
            cout << "w";
    } else if (find(elmts.begin(), elmts.end(), Fire) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "F";
        else
            cout << "f";
    } else if (find(elmts.begin(), elmts.end(), Ice) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "I";
        else
            cout << "i";
    } else if (find(elmts.begin(), elmts.end(), Ground) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "G";
        else
            cout << "g";
    } else if (find(elmts.begin(), elmts.end(), Electric) != elmts.end()) {
        if (e.getLevel() >= LEVEL_BIG_WILD_POKEMON)
            cout << "E";
        else
            cout << "e";
    }
}

void removeWildEngimon(EngimonLiar* engi){
    for (int i = 0; i < MAX_WILD_ENGIMON; i++){
        if (engi == wildEngimons[i]) wildEngimons[i] = NULL;  
    }
}

void printPeta(Player player, Position prev) {
    for (int i = 0; i < tabPeta[0].size()+2; i++)
        cout << '#';

    cout << endl;
    for (int i = 0; i < tabPeta.size(); i++)
    {
        cout << '#';
        for (int j = 0; j < tabPeta[i].size(); j++)
        {
            EngimonLiar *e = getEngimonInCell(Position(j, i));

            if (player.getPosition() == Position(j, i))
                cout << "P";
            else if (prev == Position(j, i))
                cout << "X";
            else if (e != NULL)
                printEngimonInPeta(*e);
            else
                cout << tabPeta[i][j];
        }

        cout << '#' << endl;
    }

    for (int i = 0; i < tabPeta[0].size()+2; i++)
        cout << '#';

    cout << endl;
}

void printHelp(){
    cout << "Comman    | Usage" << endl;
    cout << "w,a,s,d   | Movement" << endl;
    cout << "show      | Show Active Engimon Stats" << endl;
    cout << "change    | Change Active Engimon" << endl;
    cout << "inventory | Access Inventory" << endl;
    cout << "use       | Use Skill Item To Teach Engimon" << endl;
    cout << "battle    | Battle Nearby Wild Engimon" << endl;
}

template <class T>
Position move(T& obj, Direction dir, Player player, Position prev) {
    int x = obj.getPosition().getX();
    int y = obj.getPosition().getY();
    Position p(x, y);

    if (dir == Up) {
        y--;
    } else if (dir == Down) {
        y++;
    } else if (dir == Left) {
        x--;
    } else if (dir == Right){
        x++;
    }

    if ((y>=tabPeta.size()||y<0) || (x>=tabPeta[0].size()||x<0) || isCellOccupied(Position(x, y), player, prev)) {
        throw dir;
    } else{
        obj.setPosition(Position(x, y));
    }

    return p;
}

void moveWildEngimon(Player player, Position prev){
    srand(time(NULL));
    for (EngimonLiar* eL : wildEngimons) {
        if (eL != NULL) {
            vector<Element> elements = eL->getElements();
            Position p = eL->getPosition();
            Direction dir;
            Position dummy = p;

            int tempX=p.getX(),tempY=p.getY();
            int randomizer = rand() % 4;
            if (randomizer == 0) {
                dir = Up;
                tempY--;
            }else if(randomizer == 1) {
                dir = Down;
                tempY++;
            }else if(randomizer == 2){
                dir = Left;
                tempX--;
            } else {
                dir = Right;
                tempX++;
            }
            
            bool seaEngimon = (find(elements.begin(), elements.end(), Water) != elements.end() || find(elements.begin(), elements.end(), Ice) != elements.end() );
            bool groundEngimon = (find(elements.begin(), elements.end(), Ground) != elements.end() ||
                find(elements.begin(), elements.end(), Electric) != elements.end() ||
                find(elements.begin(), elements.end(), Fire) != elements.end());


            try {
                if (tempY > 0 && tempX > 0 && tempY < tabPeta.size() && tempX < tabPeta[0].size()) {
                    if (tabPeta[tempY][tempX] == 'o' && seaEngimon) move(*eL, dir, player, prev);
                    else if (tabPeta[tempY][tempX] == '-' && groundEngimon) move(*eL, dir, player, prev);
                }
            } catch (Direction errDir) {}
        }
    }
}

Engimon* pickStarterEngimon(){
    string command;
    string starterName;
    cout << "<<< Welcome to the World of Engimon! Ready to Catch 'em All? >>>" << endl;
    cout << "Here's a list for your starter: " << endl;
    cout << "1. Emberon (Fire)" << endl;
    cout << "2. Hailon (Ice)" << endl;
    cout << "3. Soliust (Ground)" << endl;
    cout << "4. Bulbmon (Electric)" << endl;
    cout << "5. Aquaron (Water)" << endl;
    cout << "Choose a number (1-5) for your starter: ";

    cin >> command;
    cout << "Give it a name: ";
    cin >> starterName;

    if (command == "1") {
        return new Engimon(starterName, "Emberon");
    } else if (command == "2") {
        return new Engimon(starterName, "Hailon");
    } else if (command == "3") {
        return new Engimon(starterName, "Soliust");
    } else if (command == "4") {
        return new Engimon(starterName, "Bulbmon");
    } else if (command == "5") {
        return new Engimon(starterName, "Aquamon");
    } 
    return new Engimon(starterName, "Emberon");
}

int pickEngimon(Player player){
    int opt;
    player.showAllEngimon();
    cout << "Choose Engimon(1-" << player.engimonList.inventoryList.size() << ") $";
    cin >> opt;

    return opt;
}

void cheatEngimon(Player& player){
    Engimon cheat1("CheatOne",Emberon,31);
    Engimon cheat2("CheatTwo",Bulbmon,31);

    player.engimonList.insert(cheat1);
    player.engimonList.insert(cheat2);
}

int main() {
    /* Const Declaration */
    const string filePath = "./input/contoh.txt";

    /* Var Declaration */
    string command;
    int turn = 0;

    /* Initialization Phase */
    initPeta(filePath);

    Engimon *starter = pickStarterEngimon();

    Player player(*starter);
    player.skillItemList.insert(TM02);
    cheatEngimon(player);
    /* In Game Phase */
    do {
        Position prev(player.getPosition().getX(),player.getPosition().getY());
        
        try{
            if (command == "help"){
                printHelp();
            } else if (command == "W" || command == "w") {
                prev = move(player, Up, player, prev); 
            } else if (command == "A" || command == "a") {
                prev = move(player, Left, player, prev);
            } else if (command == "S" || command == "s") {
                prev = move(player, Down, player, prev);
            } else if (command == "D" || command == "d") {
                prev = move(player, Right, player, prev);
            } else if (command == "show"){ 
                player.showEngimon(player.getActiveEngimon());
            } else if (command == "inventory") {
                string opt;

                cout << "1. Engimon" << endl;
                cout << "2. Skill Item" << endl;
                cout << "Choose a number (1-2) $ "; 
                cin >> opt;
                
                cout << endl;
                if(opt=="1") player.showAllEngimon();
                else player.showSkillItems();
            } else if (command == "use"){
                int opt;
                player.showSkillItems();

                cout << "Choose an item(1-" << player.skillItemList.inventoryList.size() << ") $";
                cin >> opt;

                if(opt<1 || opt>player.skillItemList.inventoryList.size()){
                    throw opt;
                } 
                SkillItem& skillChosen = player.skillItemList.inventoryList[opt-1]; 

                cout << "Choose Engimon to give the " << skillChosen.getName()<< endl;
                cout << "Make sure the skills elements are compatible." << endl;
                opt = pickEngimon(player);

                if(opt<1 || opt>player.engimonList.inventoryList.size()){
                    throw opt;
                }
                Engimon& engiChosen = player.engimonList.inventoryList[opt-1];
                engiChosen.showDetails();
                player.engimonList.inventoryList[opt-1].showDetails();
                player.useItems(skillChosen, engiChosen);
                engiChosen.showDetails();
                player.engimonList.inventoryList[opt-1].showDetails();
            } else if (command == "change") {
                int opt;
                cout << "Choose new Active Engimon " << endl;

                opt = pickEngimon(player);

                if(opt<1 || opt>player.engimonList.inventoryList.size()){
                    throw opt;
                }

                player.switchActiveEngimon(player.engimonList.inventoryList[opt-1]);
                cout << "Successfully switched " << player.getActiveEngimon().getName() << " into active!" << endl;
            } else if (command == "battle") {
                //buat ganti initial posisi player ada di player.h nya kok
                int x = player.getPosition().getX();
                int y = player.getPosition().getY();
                

                EngimonLiar* enemy = NULL;
                bool found = false;
                for (int i = y - 1; i <= y + 1 && !found; i++) {
                    for (int j = x - 1; j <= x + 1 && !found; j++) {
                        if ((i == y || j == x) && !(i == y && j == x)) {
                            enemy = getEngimonInCell(Position(j, i));
                            if (enemy != NULL){
                                found = true;
                            }
                        }
                    }
                }

                if (enemy == NULL)
                    throw "enemy does not exist";

                enemy->printSummary();

                removeWildEngimon(enemy);

                player.battle(*enemy);
            } else if (command == "breed") {
                int opt1,opt2;
                player.showAllEngimon();
                cout << "Choose 2 Engimon to breed" << endl;
                cout << "First Engimon(1-" << player.engimonList.inventoryList.size() << ")$ ";
                cin >> opt1;
                cout << "Second Engimon(1-" << player.engimonList.inventoryList.size() << ")$ ";
                cin >> opt2;
                if(opt1==opt2 || opt1<1 || opt1>player.engimonList.inventoryList.size() || opt2<1 || opt2>player.engimonList.inventoryList.size()){
                    throw opt1;
                } else {
                    player.breed(player.engimonList.inventoryList[opt1-1],player.engimonList.inventoryList[opt2-1]);
                }
            }
        }catch (Direction errDir){
            cout << "\nMovement not valid! Your Engimon just hit you, enter a valid movement!" << endl;
        }catch (LevelNotEnoughException errBreed){
            cout << "\nParent level not high enough to breed!" << endl;
        }catch (InventoryFullException errInven){
            cout << "\nEmpty more spaces!" << endl;
        }catch (int errOpt){
            cout << "\nOption invalid!" << endl;
        }catch (RunOutOfEngimonException errBattle){
            cout << "\nYou have run out of Engimon!" << endl;
            cout << "\n***********\n GAME OVER\n***********" << endl;
            break;
        }catch (ItemAlreadyExistedException errExisted){
            cout << "\nYou have this Skill Item already!" << endl;
        }catch (string errBattle){
            cout << "\nYou don't have enemy nearby!" << endl;
        }catch (SkillNotCompatibleException errUseSkill){
            cout << "\nEngimon element not compatible!" << endl;
        }

        if (turn % RESPAWN_TURN == 0) {
            cout << "The Wild Engimons have respawned!" << endl;
            spawnWildEngimons(player, prev);
        }
        else if (turn % 5 == 0) {
            cout << "The Wild Engimons have moved!" << endl;
            moveWildEngimon(player,prev);
        }

        cout << endl;
        printPeta(player, prev);

        turn++;
        cout << "\n>>type \'help\' to show available commands<<\n$";
    } while (cin >> command); 

    return 0;
}