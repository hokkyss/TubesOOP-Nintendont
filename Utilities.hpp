#ifndef UTILITIES_HPP
#define UTILITIES_HPP

#include <fstream>

#include "Element.hpp"
#include "Direction.hpp"
#include <vector>
#include <string.h>

using namespace std;

/* Static Data */
extern double elmtAdv[5][5];
// Element Advantages Table
// Format Input & Access: elmtAdv[Element Attacker][Element Defender]

/* Global Functions */
template <class T>
void printVector(vector<T> v, string delimiter, bool newLine, bool showIndex) {
    for(int i = 0; i < v.size(); i++) {
        if (showIndex) cout << i+1 << "." << endl;
        cout << v[i];
        if(i != v.size() - 1){
            cout << delimiter;
        }
        if (newLine) cout << endl;
    }
}

#endif