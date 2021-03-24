#include<iostream>
#include<fstream>
#include<vector>
#include<map>
#include<string>
#include "Element.hpp"
using namespace std;

/* Static Data */
// map<Element, map<Element, double>> elmtAdv;
double elmtAdv[5][5];
// Element Advantages Table
// Format Input & Access: elmtAdv[Element Attacker][Element Defender]

vector <vector<char> > peta;
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
// Access: peta[i][j] if (0 < i < peta.size() and 0 < j < peta[i].size() )

/* Global Functions */
template <class T>
void printVector(vector<T> const& v, string delimiter, bool newLine, bool showIndex) {
    for(int i = 0; i < v.size(); i++) {
        if (showIndex) cout << i+1 << "." << endl;
        cout << v[i];
        if(i != v.size() - 1){
            cout << delimiter;
        }
        if (newLine) cout << endl;
    }
}

void initElmtAdv() {
    elmtAdv[Fire][Fire] = 1;
    elmtAdv[Fire][Water] = 0;
    elmtAdv[Fire][Electric] = 1;
    elmtAdv[Fire][Ground] = 0.5;
    elmtAdv[Fire][Ice] = 2;

    elmtAdv[Water][Fire] = 2;
    elmtAdv[Water][Water] = 1;
    elmtAdv[Water][Electric] = 0;
    elmtAdv[Water][Ground] = 1;
    elmtAdv[Water][Ice] = 1;

    elmtAdv[Electric][Fire] = 1;
    elmtAdv[Electric][Water] = 2;
    elmtAdv[Electric][Electric] = 1;
    elmtAdv[Electric][Ground] = 0;
    elmtAdv[Electric][Ice] = 1.5;

    elmtAdv[Ground][Fire] = 1.5;
    elmtAdv[Ground][Water] = 1;
    elmtAdv[Ground][Electric] = 2;
    elmtAdv[Ground][Ground] = 1;
    elmtAdv[Ground][Ice] = 0;

    elmtAdv[Ice][Fire] = 0;
    elmtAdv[Ice][Water] = 1;
    elmtAdv[Ice][Electric] = 0.5;
    elmtAdv[Ice][Ground] = 2;
    elmtAdv[Ice][Ice] = 1;
}

void initPeta(string filePath) {
    ifstream petaFile(filePath);
    string textLine;

    while (getline(petaFile, textLine))
    {
        vector<char> rowPeta;

        for (char c : textLine)
        {
            rowPeta.push_back(c);
        }

        peta.push_back(rowPeta);
    }
}

void printPeta() {
    for (int i = 0; i < peta.size(); i++) {
        for (int j = 0; j < peta[i].size(); j++) {
            cout << peta[i][j] << " ";
        }

        cout << endl;
    }
}
