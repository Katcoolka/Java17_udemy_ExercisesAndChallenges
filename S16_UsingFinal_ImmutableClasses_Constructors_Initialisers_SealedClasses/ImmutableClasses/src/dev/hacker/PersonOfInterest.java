package dev.hacker;

import dev.lpa.PersonImmutable;

public class PersonOfInterest extends PersonImmutable {

    public PersonOfInterest(PersonImmutable person) {
        super(person);
    }
    //8.) adding final in Person Immutable requires to remove this Override method
//    @Override
//    public PersonImmutable[] getKids() {
//        return super.kids;
//    }
}
