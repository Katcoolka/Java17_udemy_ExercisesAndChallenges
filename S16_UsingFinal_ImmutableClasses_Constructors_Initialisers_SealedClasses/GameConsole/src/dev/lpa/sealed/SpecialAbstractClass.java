package dev.lpa.sealed;

public sealed class SpecialAbstractClass permits FinalKid, NonSealedKid, SealedKid, SpecialAbstractClass.Kid {

    //there's a circular relationship between a sealed class, and its subclasses.
    //A sealed class has to have knowledge of its subclasses, which an unsealed class doesn't.
    //An unsealed class can have many unknown subclasses.
    final class Kid extends SpecialAbstractClass{

    }

}
