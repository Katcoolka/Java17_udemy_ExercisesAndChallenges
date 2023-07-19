package dev.external;

import dev.external.domain.LivingPerson;
import dev.hacker.PersonOfInterest;
import dev.lpa.PersonImmutable;

public class MainImmutable {
    public static void main(String[] args) {
        PersonImmutable jane = new PersonImmutable("Jane", "01/01/1930");
        PersonImmutable jim = new PersonImmutable("Jim", "02/02/1932");
        PersonImmutable joe = new PersonImmutable("Joe", "03/03/1934");

        PersonImmutable[] johnsKids = {jane, jim, joe};
        PersonImmutable john = new PersonImmutable("John", "05/05/1900", johnsKids);
        System.out.println(john);

        //we can populate the new array
        PersonImmutable[] kids = john.getKids();
        kids[0] = jim;
        kids[1] = new PersonImmutable("Ann", "04/04/1936");
        System.out.println(john);

        johnsKids[0] = new PersonImmutable("Ann", "04/04/1936");
        System.out.println(john);
        //no changes - console output: John, dob = 05/05/1900, kids = Jane, Jim, Joe

        //using subclass
        LivingPerson johnLiving = new LivingPerson(john.getName(), john.getKids());
        System.out.println(johnLiving);
        //adding new kid
        LivingPerson ann = new LivingPerson("Ann", null);
        johnLiving.addKid(ann);
        System.out.println(johnLiving);

        //creating copy
        PersonOfInterest johnCopy = new PersonOfInterest(john);
        System.out.println(johnCopy);

        kids = johnCopy.getKids();
        kids[1] = ann;
        System.out.println(johnCopy);
        System.out.println(john);
        //- console output: John, dob = 05/05/1900, kids = Jane, Ann, Joe
        //John, dob = 05/05/1900, kids = Jane, Ann, Joe
        // not OK
        //after updating constructor in PersonImmutable point 7.)
        //- console output: John, dob = 05/05/1900, kids = Jane, Ann, Joe
        //John, dob = 05/05/1900, kids = Jane, Jim, Joe
    }
}
