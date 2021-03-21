#include<iostream>
#include<vector>
using namespace std;

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