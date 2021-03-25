#include "Species.hpp"

using namespace std;

Species::Species(string species, vector<Element> elements, const Skill& uniqueSkill): uniqueSkill(uniqueSkill) {
    this->species = species;
    this->elements = elements;
}

Species::Species(const Species& s) : uniqueSkill(s.uniqueSkill) {
    this->species = s.species;
    this->elements = s.elements;
}

string Species::getName() const {
    return this->species;
}

vector<Element> Species::getElements() const {
    return this->elements;
}

Skill Species::getUniqueSkill() const {
    return this->uniqueSkill;
}

Species& getSpeciesByName(string species)
{
    for(int i = 0; i < listOfSpecies.size(); i++) {
        if (listOfSpecies[i].getName() == species) return listOfSpecies[i];
    }
    return Emberon;
    throw ItemNotFoundException();
}

Species& getSpeciesByElement(vector<Element> elements){
    for(int i = 0; i<listOfSpecies.size(); i++){
        if(isElementsSame(elements, listOfSpecies[i].getElements())){
            return listOfSpecies[i];
        }
    }

    return Emberon;
}

bool isElementsSame(vector<Element> e1, vector<Element> e2){
    if(e1.size()==e2.size()){
        sort(e1.begin(), e1.end());
        sort(e2.begin(), e2.end());
        for(int i = 0; i<e1.size();i++){
            if(e1[i]!=e2[i]){
                return false;
            }
        }
        return true;
    }
    return false;
}

void Species :: operator=(const Species& s)
{
	this->species = s.species;
    this->elements = s.elements;
    this->uniqueSkill = s.uniqueSkill;
}

//single element
Species Emberon("Emberon", {Element::Fire},FlameThrower);
Species Hailon("Hailon", {Element::Ice},IceBeam);
Species Soliust("Soliust", {Element::Ground},DoubleEdge);
Species Bulbmon("Bulbmon", {Element::Electric},VoltTackle);
Species Aquaron("Aquaron", {Element::Water},HydroPump);

Species Sparkymon("Sparkymon", {Element::Fire},FireBlast);
Species Icypicy("Icypicy", {Element::Ice},IceHammer);
Species CacingAlaska("CacingAlaska", {Element::Ground},BullDoze);
Species Chupika("Chupika", {Element::Electric},ShockWave);
Species Flooduf("Flooduf", {Element::Water},Liquidation);
//double element
Species Coldhell("Coldhell", {Element::Fire, Element::Ice},FreezingFlame);
Species Magmatuar("Magmatuar", {Element::Fire, Element::Ground},FireBlast);
Species Electrosion("Electrosion", {Element::Fire, Element::Electric},ThunderBlast);
Species Sealame("Sealame", {Element::Fire, Element::Water},DoubleEdge);

Species Antartic("Antartic", {Element::Ice, Element::Ground},Avalanche);
Species Culcas("Culcas", {Element::Ice, Element::Electric},ColdRefrigerator);
Species Labile("Labile", {Element::Ice, Element::Water},IceBeam);

Species Gustmon("Gustmon", {Element::Ground,Element::Electric},TectonicRage);
Species Trenchmon("Trenchmon", {Element::Ground,Element::Water},GigaImpact);

Species Voltense("Voltense", {Element::Electric, Element::Water},Korslet);

//triple element
Species Ficigmon("Ficigmon", {Element::Fire, Element::Ice, Element::Ground},FreezingFlame);
Species Circogord("Circogord", {Element::Fire, Element::Ice, Element::Electric},ThunderBlast);
Species Icify("Icify", {Element::Fire, Element::Ice, Element::Water},BurningChill);
Species LovaMagama("LovaMagama", {Element::Fire, Element::Ground, Element::Electric},SteamBomb);
Species Tetonicy("Tetonicy", {Element::Fire, Element::Ground, Element::Water},Mudflood);
Species PerfectDisaster("PerfectDisaster", {Element::Fire, Element::Electric, Element::Water},ShockAndBurn);

Species Iglosify("Iglosify", {Element::Ice, Element::Ground, Element::Electric},WeatherBall);
Species Trofii("Trofii", {Element::Ice, Element::Ground, Element::Water},MuddyWater);
Species Syverter("Syverter", {Element::Ice, Element::Electric, Element::Water},Storm);

Species Oonga("Oonga", {Element::Ground,Element::Electric,Element::Water},Eruption);

//4 elements
Species Hokkien("Hokkien", {Element::Fire, Element::Ice, Element::Ground, Element::Electric},EarthPower);
Species Arleen("Arleen", {Element::Fire, Element::Ice, Element::Ground, Element::Water},MeltTheGround);
Species Maxeew("Maxeew", {Element::Fire, Element::Ice, Element::Electric, Element::Water},FreezingFlame);
Species Shijeew("Shijeew", {Element::Fire, Element::Ground, Element::Electric, Element::Water},LavaPlume);
Species Pegow("Pegow", {Element::Fire, Element::Ice, Element::Ground, Element::Water},FreezeDry);

Species Frederon("Frederon", {Element::Fire, Element::Ice, Element::Ground, Element::Electric, Element::Water},GodlyFart);

vector<Species> listOfSpecies = {
    Emberon, Hailon, Soliust, Voltense, Aquaron,
    //double element
    Coldhell, Magmatuar, Electrosion, Sealame, Antartic, Culcas, Labile, Gustmon, Trenchmon, Voltense,
    //triple element
    Ficigmon, Circogord, Icify, LovaMagama, Tetonicy, PerfectDisaster, Iglosify, Trofii, Oonga,
    //4 element
    Hokkien, Arleen, Maxeew, Shijeew, Pegow,
    //5 element
    Frederon
};