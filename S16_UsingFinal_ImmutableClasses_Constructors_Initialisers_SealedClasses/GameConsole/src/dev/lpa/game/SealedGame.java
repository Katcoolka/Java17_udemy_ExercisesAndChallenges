//package dev.lpa.game;
//
//import dev.lpa.pirate.PirateGame;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
////Sealed Class (from Java 17)
////Using the sealed keyword, requires the parent class to declare it's subclasses, using a permits clause.
////This means the parent class has to know about every direct subclass, and these have to exist,
////in the same package in this case. In addition, the sealed keyword puts a requirement
////on all the subclasses that were declared in the permits clause.
////It requires each subclass to declare one of the three valid modifiers for a class extending a sealed class.
////These are final, sealed or non-sealed.

//public sealed abstract class SealedGame<T extends Player> permits ShooterGame{
//    private final String gameName;
//    private final List<T> players = new ArrayList<>();
//    private Map<Character, GameAction> standardActions = null;
//
//    public SealedGame(String gameName) {
//        this.gameName = gameName;
//    }
//
//    public String getGameName() {
//        return gameName;
//    }
//
//    public Map<Character, GameAction> getStandardActions() {
//        if (standardActions == null) {
//            standardActions = new LinkedHashMap<>(Map.of(
//                    'I', new GameAction('I', "Print Player Info", i -> this.printPlayer(i)),
//                    'Q', new GameAction('Q', "Quit game", this::quitGame)));
//        }
//        return standardActions;
//    }
//
//    public abstract T createNewPlayer(String name);
//
//    public abstract Map<Character, GameAction> getGameActions(int playerIndex);
//
//    final int addPlayer(String name) {
//        T player = createNewPlayer(name);
//        if (player != null) {
//            players.add(player);
//            return players.size() - 1;
//        }
//        return -1;
//    }
//
//    protected final T getPlayer(int playerIndex) {
//        return players.get(playerIndex);
//    }
//
//    public boolean executeGameAction(int player, GameAction action) {
//        return action.action().test(player);
//    }
//
//    public boolean printPlayer(int playerIndex) {
//        Player player = players.get(playerIndex);
//        System.out.println(player);
//        return false;
//    }
//
//    public boolean quitGame(int playerIndex) {
//        Player player = players.get(playerIndex);
//        System.out.println("Sorry to see you go, " + player.name());
//        return true;
//    }
//}
