#include "Species.hpp"

using namespace std;

Species::Species(string species, vector<Element> elements, const Skill& uniqueSkill, vector<string> response): uniqueSkill(uniqueSkill) {
    this->species = species;
    this->elements = elements;
    this->response =response;
}

Species::Species(const Species& s) : uniqueSkill(s.uniqueSkill) {
    this->species = s.species;
    this->elements = s.elements;
    this->response = s.response;
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
    this->response = s.response;
}

void Species::interact(){
    int idx = rand() % this->response.size();
    cout<<this->response[idx]<<endl;
}

//single element
Species Emberon("Emberon", {Element::Fire},FirePledge,{"Brrr..","Ron! Ron!!"});
Species Hailon("Hailon", {Element::Ice},IceBeam,{"Hail! Hail!!", "Loon.."});
Species Soliust("Soliust", {Element::Ground},BodySlam,{"Grust..", "Louuust"});
Species Bulbmon("Bulbmon", {Element::Electric},Thunderbolt,{"Bulbo, bulbo!","I need electricity"});
Species Aquaron("Aquaron", {Element::Water},WaterPledge, {"Aqua qua qua", "Quda quda quda"});

Species Sparkymon("Sparkymon", {Element::Fire},FireBlast,{"Sparky parky!","Party parky!"});
Species Icypicy("Icypicy", {Element::Ice},IceHammer, {"cyp cyp", "icy icy"});
Species CacingAlaska("CacingAlaska", {Element::Ground},BullDoze,{"Dimana spongebob?","Saya perlu nanas laut"});
Species Chupika("Chupika", {Element::Electric},ShockWave,{"Chupi, chupii","Piii.."});
Species Flooduf("Flooduf", {Element::Water},Liquidation, {"loo looo", "looooduffff"});
//double element
Species Coldhell("Coldhell", {Element::Fire, Element::Ice},FreezingFlame,{"The hell is freezing..","There's global warming in hell, do you even know that?"});
Species Magmatuar("Magmatuar", {Element::Fire, Element::Ground},FireBlast,{"Magma is very hot, yet very relaxing", "Magmatuuuuaarrr!"});
Species Electrosion("Electrosion", {Element::Fire, Element::Electric},ThunderBlast,{"Trooooo...","PAR sangat susah"});
Species Sealame("Sealame", {Element::Fire, Element::Water},DoubleEdge,{"Siiiie..","Fire and water is not a good idea"});

Species Antartic("Antartic", {Element::Ice, Element::Ground},Avalanche,{"Tiiic, tiii..","My homeland.. is disappearing"});
Species Culcas("Culcas", {Element::Ice, Element::Electric},ColdRefrigerator,{"2 Pintu lebih baik dari 1 pintu","Casss... Cullcaasss!"});
Species Labile("Labile", {Element::Ice, Element::Water},IceBeam,{"Biiii..","Bilbilbiiiiiii.."});

Species Gustmon("Gustmon", {Element::Ground,Element::Electric},TectonicRage,{"Gusti apa ini","Gust guuust!"});
Species Trenchmon("Trenchmon", {Element::Ground,Element::Water},GigaImpact,{"Mariana trench is deeper than you think!", "Trenchy, trenchy!"});

Species Voltense("Voltense", {Element::Electric, Element::Water},Korslet,{"PLN belom bayar","Token listrik memunahkan saya"});

//triple element
Species Ficigmon("Ficigmon", {Element::Fire, Element::Ice, Element::Ground},FreezingFlame,{"Ficiii","Is this digimon??"});
Species Circogord("Circogord", {Element::Fire, Element::Ice, Element::Electric},ThunderBlast,{"Cirrr","Cooo"});
Species Icify("Icify", {Element::Fire, Element::Ice, Element::Water},BurningChill,{"Brrrrr","BRRRR"});
Species LovaMagama("LovaMagama", {Element::Fire, Element::Ground, Element::Electric},SteamBomb,{"Lovee some magama","who is magama"});
Species Tetonicy("Tetonicy", {Element::Fire, Element::Ground, Element::Water},Mudflood,{"Tekto, tektoooo!","I can make the whole ground shaking, that's called tectonic!"});
Species PerfectDisaster("PerfectDisaster", {Element::Fire, Element::Electric, Element::Water},ShockAndBurn, {"covid vid vid", "nubes tubes lagi tubes"});

Species Iglosify("Iglosify", {Element::Ice, Element::Ground, Element::Electric},WeatherBall, {"cuaca hari ini", "terang benderang"});
Species Trofii("Trofii", {Element::Ice, Element::Ground, Element::Water},MuddyWater, {"trolilili", "fuuuuuulingggg"});
Species Syverter("Syverter", {Element::Ice, Element::Electric, Element::Water},Storm, {"vert vertttt", "kentut bunyinya pret"});

Species Oonga("Oonga", {Element::Ground,Element::Electric,Element::Water},Eruption, {"oonga oonga", "ngaooo ngaoo"});

//4 elements
Species Hokkien("Hokkien", {Element::Fire, Element::Ice, Element::Ground, Element::Electric},EarthPower, {"hok hok hok", "ki ki ki >.<!"});
Species Arleen("Arleen", {Element::Fire, Element::Ice, Element::Ground, Element::Water},MeltTheGround, {"aldi is here", "arrrrrrrr...leennn"});
Species Maxeew("Maxeew", {Element::Fire, Element::Ice, Element::Electric, Element::Water},FreezingFlame, {"aduh aduh aduh", "ez ez ez"});
Species Shijeew("Shijeew", {Element::Fire, Element::Ground, Element::Electric, Element::Water},LavaPlume, {"jeew strongg!", "shishishi"});
Species Pegow("Pegow", {Element::Fire, Element::Ice, Element::Ground, Element::Water},FreezeDry, {"gope gope gope", "cepe cepe cepe"});

Species Frederon("Frederon", {Element::Fire, Element::Ice, Element::Ground, Element::Electric, Element::Water},GodlyFart, {"hehe haha", "hoho huhu"});

vector<Species> listOfSpecies = {
    Emberon, Hailon, Soliust, Voltense, Aquaron,
    Sparkymon, Icypicy, CacingAlaska, Chupika, Flooduf,
    //double element
    Coldhell, Magmatuar, Electrosion, Sealame, Antartic, Culcas, Labile, Gustmon, Trenchmon, Voltense,
    //triple element
    Ficigmon, Circogord, Icify, LovaMagama, Tetonicy, PerfectDisaster, Iglosify, Trofii, Oonga,
    //4 element
    Hokkien, Arleen, Maxeew, Shijeew, Pegow,
    //5 element
    Frederon
};