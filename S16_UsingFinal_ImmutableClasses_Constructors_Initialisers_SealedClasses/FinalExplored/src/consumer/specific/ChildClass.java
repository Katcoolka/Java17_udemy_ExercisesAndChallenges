package consumer.specific;

import dev.lpa.generic.BaseClass;

public class ChildClass extends BaseClass {

    @Override
    protected void optionalMethod() {
        System.out.println("[Child:optionalMethod] EXTRA Stuff Here");
        super.optionalMethod();
    }

    //The subclass, ChildClass, doesn't compile now. When you make a method final, a subclass can't override it.
//    @Override
//    public void recommendedMethod() {
//        System.out.println("[Child:recommendedMethod]: I'll do things my way");
//        optionalMethod();
//    }

    //The modifiers private and final are somewhat redundant.
    //When a method is private, no other class, including subclasses, has access to it,
    //and therefore any code that calls it, will only execute the code on the class where it's declared.
    //Using the private modifier means this method will never be polymorphic.
    //That means if I run my code now, even though I have my own private method called mandatoryMethod, on the child, it's never going to be the method that's executed from
    //the recommendedMethod, on BaseClass.
    private void mandatoryMethod() {
        System.out.println("[Child:mandatoryMethod]: My own important stuff");
    }


    //hiding a static method -copy from parent class
    public static void recommendedStatic(){
        System.out.println("[Child.recommendedStatic] BEST Way to Do it");
        optionalStatic();
        //mandatoryStatic();
    }

}
