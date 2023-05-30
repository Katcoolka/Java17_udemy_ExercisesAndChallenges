public class NextMain {
    public static void main(String[] args) {
        Movie movie = Movie.getMovie("A", "Jaws");
        movie.watchMovie();

        Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        jaws.watchMovie();

        Object comedy = Movie.getMovie("C", "Airplane");
        Comedy comedyMovie = (Comedy) comedy;
        comedyMovie.watchComedy();

        //Local variable type Inference (LVTI)
        var airplane  = Movie.getMovie("C", "Airplane");
        airplane.watchMovie();
        //Local Variable Type Inference (LVTI)
        var plane = new Comedy("Airplane");
        plane.watchComedy();

        //instanceof
        //testing the runtime type using the instanceof operator
        //getSimpleName, is a method that returns the class name of our instance here
        Object unknownObject = Movie.getMovie("S", "Star Wars");
        if(unknownObject.getClass().getSimpleName() == "Comedy"){
            Comedy c = (Comedy) unknownObject;
            c.watchComedy();
            //instanceof operator - > to test the type of an object or instance
            //left operand is reference variable you are testing
            //right operand is the actual type you are testing for
        }else if(unknownObject instanceof Adventure){
            ((Adventure) unknownObject).watchAdventure();
            //Pattern matching for the instanceof Operator
            //the object can be assigned to a binding variable (syfy in this case) if the instance of method returns true
        } else if (unknownObject instanceof ScienceFiction syfy) {
            syfy.watchSciFi();
        }
    }
}
