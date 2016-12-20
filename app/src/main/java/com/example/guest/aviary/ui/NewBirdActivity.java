package com.example.guest.aviary.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

    final String[] sittidaeArray = {"Pygmy Nuthatch", "Red-breasted Nuthatch", "White-breasted Nuthatch", "European Starling"};

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

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, genderArray);
        mGenderSpinner.setAdapter(adapter);

        mBirdNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String familyName = familyArray.get(i).toString();
                setFamily(familyName);
            }

            private void setFamily(String familyName) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        }
    }
}
