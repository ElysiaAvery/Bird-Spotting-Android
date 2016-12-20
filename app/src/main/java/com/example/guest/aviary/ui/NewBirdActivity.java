package com.example.guest.aviary.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.aviary.R;
import com.google.firebase.auth.api.model.StringList;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewBirdActivity extends AppCompatActivity {
    @Bind(R.id.header) TextView mHeader;
    @Bind(R.id.birdNameSpinner) Spinner mBirdNameSpinner;
    @Bind(R.id.genderSpinner) Spinner mGenderSpinner;
    @Bind(R.id.birdFamilySpinner) Spinner mFamilySpinner;
    @Bind(R.id.cityEditText) EditText mCityText;
    @Bind(R.id.stateEditText) EditText mStateText;
    @Bind(R.id.zipEditText) EditText mZipText;
    @Bind(R.id.addPhotoButton) Button mPhoto;
    @Bind(R.id.addAudioButton) Button mAudio;
    @Bind(R.id.submitButton) Button mSubmit;
    final String[] genderArray = {"Male", "Female", "Other", "Unknown"};
    final String[] familyArray = {"Not Sure", "Accipitridae", "Alcedinidae", "Alcidae", "Anatidae", "Ardeidae", "Bombycillidae", "Cardinalidae",
            "Cathartidae", "Caprimulgidae", "Certhiidae", "Charadriidae", "Cinclidae", "Columbidae", "Corvidae",
            "Cracidae", "Cuculidae", "Emberizidae", "Falconidae", "Fringillidae",
            "Gaviidae", "Haematopodidae", "Hirundinidae", "Icteridae", "Laniidae", "Mimidae",
            "Laridae", "Odontophoridae", "Paridae", "Parulidae", "Passeridae", "Pelecanidae", "Phalacrocoracidae", "Phasianidae",
            "Picidae", "Podicipedidae", "Psittacidae", "Ptilogonatidae",
            "Rallidae", "Ramphastidae", "Recurvirostridae", "Regulidae", "Remizidae", "Scolopacidae", "Sittidae", "Strigidae", "Sulidae", "Sturnidae", "Sylviidae",
            "Thraupidae", "Threskiornithidae", "Trochilidae", "Troglodytidae", "Trogonidae", "Turdidae", "Tyrannidae"};
    final String[] anatidaeArray = {"American Wigeon", "Barrow's Goldeneye", "Black-bellied Whistling-Duck", "Blue-winged Teal", "Brant", "Bufflehead",
    "Canada Goose", "Canvasback", "Cinnamon Teal", "Common Goldeneye", "Common Merganser", "Eurasian Wigeon", "Gadwall", "Greater Scaup", "Green-winged Teal",
    "Harlequin Duck", "Hooded Merganser", "Lesser Scaup", "Mallard", "Mute Swan", "Northern Pintail", "Northern Shoveler", "Red-breasted Merganser",
    "Redhead", "Ring-necked Duck", "Ross's Goose", "Ruddy Duck", "Snow Goose", "Surf Scoter", "Trumpeter Swan", "Tufted Duck", "Wood Duck"};

    final String[] trochilidaeArray = {"Anna's Hummingbird", "Black-chinned Hummingbird", "Black-crested Coquette", "Blue-capped Hummingbird",
    "Broad-billed Hummingbird", "Broad-tailed Hummingbird", "Calliope Hummingbird", "Cinnamon Hummingbird", "Costa's Hummingbird", "Green Violetear",
    "Magnificent Hummingbird", "Rufous Hummingbird", "Rufous-tailed Hummingbird", "Scintillant Hummingbird", "Stripe-tailed Hummingbird",
    "Volcano Hummingbird", "White-necked Jacobin"};

    final String[] caprimulgidaeArray = {"Common Nighthawk"};

    final String[] alcidaeArray = {"Common Murre", "Crested Auklet", "Horned Puffin", "Least Auklet", "Parakeet Auklet", "Thick-billed Murre", "Tufted Puffin"};

    final String[] charadriidaeArray = {"Black-bellied Plover", "Killdeer"};

    final String[] haematopodidaeArray = {"American Oystercatcher", "Black Oystercatcher"};

    final String[] laridaeArray = {"Black Skimmer", "Black Tern", "Black-headed Gull", "Bonaparte's Gull", "California Gull", "Forster's Tern",
    "Franklin's Gull", "Heermann's Gull", "Herring Gull", "Laughing Gull", "Least Tern", "Mew Gull", "Ring-billed Gull", "Royal Tern", "Western Gull"};

    final String[] recurvirostridaeArray = {"American Avocet", "Black-necked Stilt"};

    final String[] scolopacidaeArray = {"Black Turnstone", "Dunlin", "Least Sandpiper", "Lesser Yellowlegs", "Long-billed Curlew",
    "Long-billed Dowitcher", "Marbled Godwit", "Pectoral Sandpiper", "Red-necked Phalarope", "Rock Sandpiper", "Ruddy Turnstone", "Sanderling",
    "Spotted Sandpiper", "Surfbird", "Whimbrel", "Willet", "Wilson's Phalarope", "Wilson's Snipe"};

    final String[] ardeidaeArray = {"Black-crowned Night-Heron", "Boat-billed Heron", "Cattle Egret", "Great Blue Heron", "Great Egret", "Green Heron",
    "Least Bittern", "Little Blue Heron", "Reddish Egret", "Rufescent Tiger-Heron", "Snowy Egret", "Tricolored Heron"};

    final String[] cathartidaeArray = {"Black Vulture", "California Condor", "King Vulture", "Turkey Vulture"};

    final String[] threskiornithidaeArray = {"Glossy Ibis", "Roseate Spoonbill", "White Ibis"};

    final String[] columbidaeArray = {"Band-tailed Pigeon", "Inca Dove", "Mourning Dove", "Rock Pigeon", "White-winged Dove"};

    final String[] alcedinidaeArray = {"Belted Kingfisher"};

    final String[] cuculidaeArray = {"Greater Roadrunner"};

    final String[] accipitridaeArray = {"Bald Eagle", "Black Hawk-Eagle", "Black-and-white Hawk-Eagle", "Black-collared Hawk", "Common Black-Hawk",
    "Crane Hawk", "Ferruginous Hawk", "Golden Eagle", "Gray Hawk", "Great Black-Hawk", "Harpy Eagle", "Harris's Hawk", "Mississippi Kite",
    "Northern Goshawk", "Northern Harrier", "Ornate Hawk-Eagle", "Osprey", "Plumbeous Kite", "Red-shouldered Hawk", "Red-tailed Hawk", "Rough-legged Hawk",
    "Short-tailed Hawk", "Steller's Sea-Eagle", "Swainson's Hawk", "Swallow-tailed Kite", "White Hawk", "White-tailed Eagle", "White-tailed Hawk",
    "White-tailed Kite"};

    final String[] falconidaeArray = {"American Kestrel", "Aplomado Falcon", "Crested Caracara", "Eurasian Kestrel", "Laughing Falcon", "Peregrine Falcon",
    "Prairie Falcon"};

    final String[] cracidaeArray = {"Plain Chachalaca"};

    final String[] odontophoridaeArray = {"Gambel's Quail", "Northern Bobwhite"};

    final String[] phasiandaeArray = {"Greater Sage-Grouse", "Ring-necked Pheasant", "Ruffed Grouse", "Wild Turkey"};

    final String[] gaviidaeArray = {"Common Loon", "Pacific Loon", "Red-throated Loon"};

    final String[] gruidaeArray = {"Sandhill Crane", "Whooping Crane"};

    final String[] rallidaeArray = {"American Coot", "Clapper Rail", "Common Moorhen"};

    final String[] bombycillidaeArray = {"Cedar Waxwing"};

    final String[] cardinalidaeArray = {"Black-headed Grosbeak", "Lazuli Bunting", "Northern Cardinal", "Pyrrhuloxia", "Rose-breasted Grosbeak"};

    final String[] certhiidaeArray = {"Brown Creeper"};

    final String[] cinclidaeArray = {"American Dipper"};

    final String[] corvidaeArray = {"Black-billed Magpie", "Blue Jay", "Clark's Nutcracker", "Common Raven", "Florida Scrub-Jay", "Gray Jay", "Green Jay",
    "Mexican Jay", "Northwestern Crow", "Pinyon Jay", "Purplish-backed Jay", "Steller's Jay", "Western Scrub-Jay"};

    final String[] emberizidaeArray = {"Abert's Towhee", "Black-throated Sparrow", "Brewer's Sparrow", "Canyon Towhee", "Chipping Sparrow",
    "Dark-eyed Junco", "Fox Sparrow", "Golden-crowned Sparrow", "Green-tailed Towhee", "Lark Sparrow", "Lincoln's Sparrow", "Olive Sparrow",
    "Savannah Sparrow", "Song Sparrow", "Spotted Towhee", "White-crowned Sparrow"};

    final String[] fringillidaeArray = {"American Goldfinch", "Cassin's Finch", "Gray-crowned Rosy-Finch", "House Finch", "Lesser Goldfinch",
    "Purple Finch", "Red Crossbill"};

    final String[] hirundinidaeArray = {"Barn Swallow", "Violet-green Swallow"};

    final String[] icteridaeArray = {"Bobolink", "Brewer's Blackbird", "Brown-headed Cowbird", "Bullock's Oriole", "Common Grackle", "Eastern Meadowlark",
    "Great-tailed Grackle", "Hooded Oriole", "Red-winged Blackbird", "Western Meadowlark", "Yellow-headed Blackbird"};

    final String[] laniidaeArray = {"Northern Shrike"};

    final String[] mimidaeArray = {"Curve-billed Thrasher", "Gray Catbird", "Northern Mockingbird", "Sage Thrasher"};

    final String[] paridaeArray = {"Black-capped Chickadee", "Black-crested Titmouse", "Bridled Titmouse", "Chestnut-backed Chickadee", "Mountain Chickadee"};

    final String[] parulidaeArray = {"Yellow-rumped Warbler"};

    final String[] passeridaeArray = {"House Sparrow"};

    final String[] ptilogonatidaeArray = {"Phainopepla"};

    final String[] regulidaeArray = {"Ruby-crowned Kinglet"};

    final String[] remizidaeArray = {"Verdin"};

    final String[] sittidaeArray = {"Pygmy Nuthatch", "Red-breasted Nuthatch", "White-breasted Nuthatch"};

    final String[] sturnidaeArray = {"European Starling"};

    final String[] sylviidaeArray = {"Black-tailed Gnatcatcher"};

    final String[] thraupidaeArray = {"Flame-colored Tanager", "Golden-hooded Tanager", "Hepatic Tanager", "Scarlet Tanager", "Western Tanager"};

    final String[] troglodytidaeArray = {"Bewick's Wren", "Cactus Wren", "House Wren", "Marsh Wren", "Rock Wren"};

    final String[] turdidaeArray = {"American Robin", "Eastern Bluebird", "Mountain Bluebird", "Townsend's Solitaire", "Western Bluebird"};

    final String[] tyrannidaeArray = {"Black Phoebe", "Eastern Kingbird", "Great Kiskadee", "Western Kingbird", "Western Wood-Pewee", "Yellow-bellied Flycatcher"};

    final String[] pelecanidaeArray = {"American White Pelican", "Brown Pelican"};

    final String[] phalacrocoracidaeArray = {"Double-crested Cormorant"};

    final String[] sulidaeArray = {"Northern Gannet"};

    final String[] picidaeArray = {"Acorn Woodpecker", "Arizona Woodpecker", "Black-cheeked Woodpecker", "Downy Woodpecker", "Gila Woodpecker",
    "Golden-cheeked Woodpecker", "Golden-fronted Woodpecker", "Hairy Woodpecker", "Ladder-backed Woodpecker", "Lewis's Woodpecker", "Lineated Woodpecker",
    "Northern Flicker", "Pale-billed Woodpecker", "Pileated Woodpecker", "Red-bellied Woodpecker", "Red-breasted Sapsucker", "Red-headed Woodpecker",
    "Red-naped Sapsucker", "White-headed Woodpecker", "Yellow-bellied Sapsucker"};

    final String[] ramphastidaeArray = {"Collared Aracari", "Emerald Toucanet", "Keel-billed Toucan"};

    final String[] podicipedidaeArray = {"Eared Grebe", "Horned Grebe", "Least Grebe", "Pied-billed Grebe", "Western Grebe"};

    final String[] psittacidaeArray = {"Barred Parakeet", "Lilac-crowned Parrot", "Mealy Parrot", "Military Macaw", "Orange-chinned Parakeet", "Orange-fronted Parakeet",
    "Red-crowned Parrot", "Red-lored Parrot", "Scarlet Macaw", "Thick-billed Parrot", "White-crowned Parrot", "White-fronted Parrot", "Yellow-crowned Parrot",
    "Yellow-headed Parrot", "Yellow-lored Parrot", "Yellow-naped Parrot"};

    final String[] strigidaeArray = {"Barred Owl", "Burrowing Owl", "Great Horned Owl", "Long-eared Owl", "Snowy Owl"};

    final String[] trogonidaeArray = {"Black-headed Trogon", "Elegant Trogon", "Resplendent Quetzal", "Slaty-tailed Trogon", "Violaceous Trogon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bird);
        ButterKnife.bind(this);


        Typeface elegantFont = Typeface.createFromAsset(getAssets(), "fonts/AquilineTwo.ttf");
        mHeader.setTypeface(elegantFont);

        final ArrayAdapter<String> familyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, familyArray);
        mFamilySpinner.setAdapter(familyAdapter);

        final ArrayAdapter<String> anatidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, anatidaeArray);

        final ArrayAdapter<String> trochilidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, trochilidaeArray);

        final ArrayAdapter<String> caprimulgidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, caprimulgidaeArray);

        final ArrayAdapter<String> alcidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, alcidaeArray);

        final ArrayAdapter<String> charadriidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, charadriidaeArray);

        final ArrayAdapter<String> haematopodidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, haematopodidaeArray);

        final ArrayAdapter<String> laridaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, laridaeArray);

        final ArrayAdapter<String> recurvirostridaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, recurvirostridaeArray);

        final ArrayAdapter<String> scolopacidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, scolopacidaeArray);

        final ArrayAdapter<String> ardeidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ardeidaeArray);

        final ArrayAdapter<String> cathartidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cathartidaeArray);

        final ArrayAdapter<String> threskiornithidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, threskiornithidaeArray);

        final ArrayAdapter<String> columbidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, columbidaeArray);

        final ArrayAdapter<String> alcedinidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, alcedinidaeArray);

        final ArrayAdapter<String> cuculidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cuculidaeArray);

        final ArrayAdapter<String> accipitridaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, accipitridaeArray);

        final ArrayAdapter<String> falconidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, falconidaeArray);

        final ArrayAdapter<String> cracidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cracidaeArray);

        final ArrayAdapter<String> odontophoridaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, odontophoridaeArray);

        final ArrayAdapter<String> phasiandaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, phasiandaeArray);

        final ArrayAdapter<String> gaviidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gaviidaeArray);

        final ArrayAdapter<String> gruidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gruidaeArray);

        final ArrayAdapter<String> rallidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, rallidaeArray);

        final ArrayAdapter<String> bombycillidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bombycillidaeArray);

        final ArrayAdapter<String> cardinalidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cardinalidaeArray);

        final ArrayAdapter<String> certhiidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, certhiidaeArray);

        final ArrayAdapter<String> cinclidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cinclidaeArray);

        final ArrayAdapter<String> corvidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, corvidaeArray);

        final ArrayAdapter<String> emberizidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, emberizidaeArray);

        final ArrayAdapter<String> fringillidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, fringillidaeArray);

        final ArrayAdapter<String> hirundinidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, hirundinidaeArray);

        final ArrayAdapter<String> icteridaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, icteridaeArray);

        final ArrayAdapter<String> laniidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, laniidaeArray);

        final ArrayAdapter<String> mimidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mimidaeArray);

        final ArrayAdapter<String> paridaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paridaeArray);

        final ArrayAdapter<String> parulidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, parulidaeArray);

        final ArrayAdapter<String> passeridaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, passeridaeArray);

        final ArrayAdapter<String> ptilogonatidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ptilogonatidaeArray);

        final ArrayAdapter<String> regulidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, regulidaeArray);

        final ArrayAdapter<String> remizidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, remizidaeArray);

        final ArrayAdapter<String> sittidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sittidaeArray);

        final ArrayAdapter<String> sylviidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sylviidaeArray);

        final ArrayAdapter<String> thraupidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, thraupidaeArray);

        final ArrayAdapter<String> troglodytidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, troglodytidaeArray);

        final ArrayAdapter<String> turdidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, turdidaeArray);

        final ArrayAdapter<String> tyrannidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tyrannidaeArray);

        final ArrayAdapter<String> pelecanidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, pelecanidaeArray);

        final ArrayAdapter<String> phalacrocoracidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, phalacrocoracidaeArray);

        final ArrayAdapter<String> sulidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sulidaeArray);

        final ArrayAdapter<String> picidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, picidaeArray);

        final ArrayAdapter<String> ramphastidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ramphastidaeArray);

        final ArrayAdapter<String> podicipedidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, podicipedidaeArray);

        final ArrayAdapter<String> psittacidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, psittacidaeArray);

        final ArrayAdapter<String> strigidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, strigidaeArray);

        final ArrayAdapter<String> trogonidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, trogonidaeArray);

        final ArrayAdapter<String> sturnidaeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sturnidaeArray);


        mFamilySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(NewBirdActivity.this, selected,Toast.LENGTH_SHORT).show();
                if(selected.equals("Anatidae")){
                    mBirdNameSpinner.setAdapter(anatidaeAdapter);
                } else if(selected.equals("Trochilidae")) {
                    mBirdNameSpinner.setAdapter(trochilidaeAdapter);
                } else if(selected.equals("Accipitridae")) {
                    mBirdNameSpinner.setAdapter(accipitridaeAdapter);
                } else if(selected.equals("Alcedinidae")) {
                    mBirdNameSpinner.setAdapter(alcedinidaeAdapter);
                } else if(selected.equals("Alcidae")) {
                    mBirdNameSpinner.setAdapter(alcidaeAdapter);
                } else if(selected.equals("Ardeidae")) {
                    mBirdNameSpinner.setAdapter(ardeidaeAdapter);
                } else if(selected.equals("Bombycillidae")) {
                    mBirdNameSpinner.setAdapter(bombycillidaeAdapter);
                } else if(selected.equals("Cardinalidae")) {
                    mBirdNameSpinner.setAdapter(cardinalidaeAdapter);
                } else if(selected.equals("Cathartidae")) {
                    mBirdNameSpinner.setAdapter(cathartidaeAdapter);
                } else if(selected.equals("Caprimulgidae")) {
                    mBirdNameSpinner.setAdapter(caprimulgidaeAdapter);
                } else if(selected.equals("Certhiidae")) {
                    mBirdNameSpinner.setAdapter(certhiidaeAdapter);
                } else if(selected.equals("Charadriidae")) {
                    mBirdNameSpinner.setAdapter(charadriidaeAdapter);
                } else if(selected.equals("Cinclidae")) {
                    mBirdNameSpinner.setAdapter(cinclidaeAdapter);
                } else if(selected.equals("Columbidae")) {
                    mBirdNameSpinner.setAdapter(columbidaeAdapter);
                } else if(selected.equals("Corvidae")) {
                    mBirdNameSpinner.setAdapter(corvidaeAdapter);
                } else if(selected.equals("Cracidae")) {
                    mBirdNameSpinner.setAdapter(cracidaeAdapter);
                } else if(selected.equals("Cuculidae")) {
                    mBirdNameSpinner.setAdapter(cuculidaeAdapter);
                } else if(selected.equals("Emberizidae")) {
                    mBirdNameSpinner.setAdapter(emberizidaeAdapter);
                } else if(selected.equals("Falconidae")) {
                    mBirdNameSpinner.setAdapter(falconidaeAdapter);
                } else if(selected.equals("Fringillidae")) {
                    mBirdNameSpinner.setAdapter(fringillidaeAdapter);
                } else if(selected.equals("Gaviidae")) {
                    mBirdNameSpinner.setAdapter(gaviidaeAdapter);
                } else if(selected.equals("Haematopodidae")) {
                    mBirdNameSpinner.setAdapter(haematopodidaeAdapter);
                } else if(selected.equals("Hirundinidae")) {
                    mBirdNameSpinner.setAdapter(hirundinidaeAdapter);
                } else if(selected.equals("Icteridae")) {
                    mBirdNameSpinner.setAdapter(hirundinidaeAdapter);
                } else if(selected.equals("Laniidae")) {
                    mBirdNameSpinner.setAdapter(laniidaeAdapter);
                } else if(selected.equals("Mimidae")) {
                    mBirdNameSpinner.setAdapter(mimidaeAdapter);
                } else if(selected.equals("Laridae")) {
                    mBirdNameSpinner.setAdapter(laridaeAdapter);
                } else if(selected.equals("Odontophoridae")) {
                    mBirdNameSpinner.setAdapter(odontophoridaeAdapter);
                } else if(selected.equals("Paridae")) {
                    mBirdNameSpinner.setAdapter(paridaeAdapter);
                } else if(selected.equals("Parulidae")) {
                    mBirdNameSpinner.setAdapter(parulidaeAdapter);
                } else if(selected.equals("Passeridae")) {
                    mBirdNameSpinner.setAdapter(passeridaeAdapter);
                } else if(selected.equals("Pelecanidae")) {
                    mBirdNameSpinner.setAdapter(pelecanidaeAdapter);
                } else if(selected.equals("Phalacrocoracidae")) {
                    mBirdNameSpinner.setAdapter(phalacrocoracidaeAdapter);
                } else if(selected.equals("Phasianidae")) {
                    mBirdNameSpinner.setAdapter(phasiandaeAdapter);
                } else if(selected.equals("Picidae")) {
                    mBirdNameSpinner.setAdapter(picidaeAdapter);
                } else if(selected.equals("Podicipedidae")) {
                    mBirdNameSpinner.setAdapter(podicipedidaeAdapter);
                } else if(selected.equals("Psittacidae")) {
                    mBirdNameSpinner.setAdapter(psittacidaeAdapter);
                } else if(selected.equals("Ptilogonatidae")) {
                    mBirdNameSpinner.setAdapter(ptilogonatidaeAdapter);
                } else if(selected.equals("Rallidae")) {
                    mBirdNameSpinner.setAdapter(rallidaeAdapter);
                } else if(selected.equals("Ramphastidae")) {
                    mBirdNameSpinner.setAdapter(ramphastidaeAdapter);
                } else if(selected.equals("Recurvirostridae")) {
                    mBirdNameSpinner.setAdapter(recurvirostridaeAdapter);
                } else if(selected.equals("Regulidae")) {
                    mBirdNameSpinner.setAdapter(regulidaeAdapter);
                } else if(selected.equals("Remizidae")) {
                    mBirdNameSpinner.setAdapter(remizidaeAdapter);
                } else if(selected.equals("Scolopacidae")) {
                    mBirdNameSpinner.setAdapter(scolopacidaeAdapter);
                } else if(selected.equals("Sittidae")) {
                    mBirdNameSpinner.setAdapter(sittidaeAdapter);
                } else if(selected.equals("Strigidae")) {
                    mBirdNameSpinner.setAdapter(strigidaeAdapter);
                } else if(selected.equals("Sulidae")) {
                    mBirdNameSpinner.setAdapter(sulidaeAdapter);
                } else if(selected.equals("Sturnidae")) {
                    mBirdNameSpinner.setAdapter(sturnidaeAdapter);
                } else if(selected.equals("Sylviidae")) {
                    mBirdNameSpinner.setAdapter(sylviidaeAdapter);
                } else if(selected.equals("Thraupidae")) {
                    mBirdNameSpinner.setAdapter(thraupidaeAdapter);
                } else if(selected.equals("Threskiornithidae")) {
                    mBirdNameSpinner.setAdapter(threskiornithidaeAdapter);
                } else if(selected.equals("Troglodytidae")) {
                    mBirdNameSpinner.setAdapter(troglodytidaeAdapter);
                } else if(selected.equals("Trogonidae")) {
                    mBirdNameSpinner.setAdapter(trogonidaeAdapter);
                } else if(selected.equals("Turdidae")) {
                    mBirdNameSpinner.setAdapter(turdidaeAdapter);
                } else if(selected.equals("Tyrannidae")) {
                    mBirdNameSpinner.setAdapter(tyrannidaeAdapter);
                } else if(selected.equals("Icteridae")) {
                    mBirdNameSpinner.setAdapter(icteridaeAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mFamilySpinner.performClick();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, genderArray);
        mGenderSpinner.setAdapter(adapter);

    }

}
