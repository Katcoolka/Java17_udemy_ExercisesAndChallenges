package dev.lpa;

import dev.lpa.game.GameConsole;
import dev.lpa.pirate.Pirate;
import dev.lpa.pirate.PirateGame;
import dev.lpa.pirate.Weapon;

public class Main {
    public static void main(String[] args) {

        //ShooterGame test code
//        var console = new GameConsole<>(new ShooterGame("the Shootout Game"));
//
//        int playerIndex = console.addPlayer();
//        console.playGame(playerIndex);

//        //PirateGame
//        Weapon weapon = Weapon.getWeaponByChar('P');
//        System.out.println("weapon = " + weapon + ", hitPoints=" + weapon.getHitPoints() +
//                ", minLevel = " + weapon.getMinLevel());
//
//        var list = Weapon.getWeaponByLevel(1);
//        list.forEach(System.out::println);
//
//        //Pirate
//        Pirate tim = new Pirate("Tim");
//        System.out.println(tim);
//
////        //Towns
////        PirateGame.getTowns(0).forEach(System.out::println);
////        System.out.println("-------------------------------");
////        PirateGame.getTowns(1).forEach(System.out::println);
//
//        //Towns final challenge
//        PirateGame.getTowns(0).forEach(t -> System.out.println(t.information()));
//        System.out.println("-------------------------------");
//        PirateGame.getTowns(1).forEach(t -> System.out.println(t.information()));

//        //test of Town record
//        Town bridgetown =  new Town("Bridgetown", "Barbados", 0);
//        System.out.println(bridgetown);
//        System.out.println(bridgetown.information());

        //Console
        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}